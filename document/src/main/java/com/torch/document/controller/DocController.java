package com.torch.document.controller;

import com.torch.document.service.IDocService;

import com.torch.document.utils.result.ResultUtils;
import com.torch.document.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private IDocService docService;

    //获取用户文件夹下的目录JOSN
    @GetMapping("/DocsPathsJson")
    public ResultVo getDocsPath(@RequestParam String username){
        return ResultUtils.success("获取成功！",docService.getUserDocMsg(username));
    }

    //查看文档
    @GetMapping("/getDoc")
    public ResultVo getDoc(@RequestParam String path){
        return ResultUtils.success("获取成功！",docService.selDoc(path));
    }


    //新增目录
    @PutMapping("/addDir")
    public ResultVo addDir(@RequestParam String path){
        docService.addDir(path);
        return ResultUtils.success();
    }

    //新增文件
    @PutMapping("/addDoc")
    public ResultVo addDoc(@RequestParam String path){
        docService.addDoc(path);
        return ResultUtils.success();
    }

    //删除文档/目录
    @DeleteMapping("/delFile")
    public ResultVo delFile(@RequestParam String path){
        docService.delFile(path);
        return ResultUtils.success();
    }

    //修改文档名/目录名
    @PutMapping("/setFileName")
    public ResultVo setFileName(@RequestParam String path,@RequestParam String newName){
        docService.setFileName(path,newName);
        return ResultUtils.success();
    }

    //修改文档
    @PutMapping("/serDoc")
    public ResultVo setDoc(@RequestParam String path,@RequestParam String file){
        docService.setDoc(path,file);
        return ResultUtils.success();
    }

    //分享文章
    @GetMapping("/share")
    public ResultVo share(@RequestParam String path){
        return ResultUtils.success(docService.share(path));
    }

    //保存分享文档
    public ResultVo saveShare(@RequestParam String username,@RequestParam String path){
        return docService.saveShare(username,path)?ResultUtils.success():ResultUtils.error();
    }

    //举报文档
    public ResultVo ban(@RequestParam String username,@RequestParam String code,@RequestParam String content){
        return docService.ban(username,code,content)?ResultUtils.success():ResultUtils.error();
    }

}
