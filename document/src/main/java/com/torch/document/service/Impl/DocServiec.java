package com.torch.document.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.torch.document.dao.BanDao;
import com.torch.document.dao.UserDao;
import com.torch.document.domain.BanInfo;
import com.torch.document.domain.UserInfo;
import com.torch.document.service.IDocService;
import com.torch.document.service.IUserService;
import com.torch.document.utils.FilesUtil;
import com.torch.document.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DocServiec implements IDocService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BanDao banDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserService userService;


    private String root= FileUtil.getUserHomePath();

    @Override
    public JSONObject getUserDocMsg(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        UserInfo user = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
        String documentation = user.getDocumentation();
        String userHomePath = FileUtil.getUserHomePath();
        File[] ls = FileUtil.ls(userHomePath + "\\" + documentation);
        JSONObject obj = JSONUtil.createObj();
        obj.set(user.getId().toString(), FilesUtil.fileToJson(ls));
        return obj;
    }

    @Override
    public Boolean addDir(String path) {
        return FileUtil.mkdir(root+"\\"+path)!=null;
    }

    @Override
    public Boolean addDoc(String path) {
        return FileUtil.touch(root+"\\"+path)!=null;
    }

    @Override
    public Boolean delFile(String path) {
        return FileUtil.del(root+"\\"+path);
    }

    @Override
    public Boolean setFileName(String path, String newName) {
        return FileUtil.rename(FileUtil.file(root+"\\"+path),newName,true)!=null;
    }

    @Override
    public Boolean setDoc(String path, File file) {
        return FileUtil.copy(file,FileUtil.file(path),true)!=null;
    }

    @Override
    public File selDoc(String path) {
        if(redisUtil.hasKey(root+"\\"+path)){
            return (File) redisUtil.get(root+"\\"+path);
        }else{
            File file = FileUtil.file(root + "\\" + path);
            redisUtil.set(root+"\\"+path,file,60*60*24);
            return file;
        }
    }

    @Override
    public String share(String path) {
        SymmetricCrypto aes;
        if (redisUtil.hasKey("key")){
             aes = (SymmetricCrypto) redisUtil.get("key");
        }else{
            //随机生成密钥
            byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
            aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            redisUtil.set("key",aes,60*60*24*7);
        }
        //构建

        //加密为16进制表示
        return aes.encryptHex(path);
    }

    @Override
    public Boolean saveShare(String username,String code) {
        if (redisUtil.hasKey("key")){
            //解密为字符串
            SymmetricCrypto aes = (SymmetricCrypto) redisUtil.get("key");
            String path = aes.decryptStr(code, CharsetUtil.CHARSET_UTF_8);
            UserInfo user = userService.getUser(username);
            File shareFile = FileUtil.file(root+"\\"+path);
            File file = FileUtil.file(root + "\\" + user.getDocumentation());
            FileUtil.copy(shareFile,file,false);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean ban(String username,String code,String content) {
        if (redisUtil.hasKey("key")){
            //解密为字符串
            SymmetricCrypto aes = (SymmetricCrypto) redisUtil.get("key");
            String path = aes.decryptStr(code, CharsetUtil.CHARSET_UTF_8);
            BanInfo banInfo = new BanInfo();
            banInfo.setWhistleblower(username);
            banInfo.setReportContent(content);
            banInfo.setWhistleblowered(code);
            banDao.insert(banInfo);
            return true;
        }else{
            return false;
        }
    }

}
