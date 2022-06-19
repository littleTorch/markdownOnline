package com.torch.document.controller;

import com.torch.document.service.IDocService;
import com.torch.document.utils.result.ResultUtils;
import com.torch.document.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private IDocService docService;

    @GetMapping("/DocsPathsJson")
    public ResultVo getDocsPath(String username){
        return ResultUtils.success("获取成功！",docService.getUserDocMsg(username));
    }


}
