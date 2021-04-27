package com.jiang.school_guide.controller;
import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.untils.UploadAvatar;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/school_guide/upload")
public class FileController {

    @Autowired
    private UploadAvatar uploadAvatar;

    @ApiOperation("上传图片")
    @PostMapping("/uploadImage")
    public ServerResponse uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return ServerResponse.createBySuccess(uploadAvatar.uploadAvatar(file));
    }

    @ApiOperation("刷新token")
    @GetMapping("/refreshToken")
    @Permission(roles = {Const.ADMIN,Const.USER})
    public ServerResponse refreshToken() {
        return ServerResponse.createBySuccess(TokenUntil.refreshToken());
    }

}