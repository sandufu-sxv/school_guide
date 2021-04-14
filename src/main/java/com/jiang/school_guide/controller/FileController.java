package com.jiang.school_guide.controller;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.untils.UploadAvatar;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}