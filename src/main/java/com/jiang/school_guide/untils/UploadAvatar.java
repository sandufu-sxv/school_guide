package com.jiang.school_guide.untils;

import com.jiang.school_guide.common.exception.UploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Component
public class UploadAvatar {

    private static String uploadPicturePath = "/usr/local/share-image/image/";

    public  String uploadAvatar(MultipartFile image) throws IOException {
        String url="";
        //检查图片的大小
        boolean flag = FileUtil.checkFileSize(image.getSize(), 5, "M");


        if (!flag)
        {
            //图片大小超限
            throw new UploadException("token校验不通过");
        }
        if (!image.isEmpty())
        {
            String originalFilename = image.getOriginalFilename();//获取图片文件的名字
            String path = null;
            String type = null; //图片类型
            type = originalFilename.indexOf(".") != -1 ?
                    originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length())
                    : null;

            if (type != null)
            {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))
                {
                    // 新的图片的名称
                    String trueFileName = System.currentTimeMillis() + FileUtil.getRandomString(15)+'.'+type;
                    // 设置存放图片文件的路径
                    path = uploadPicturePath + trueFileName;
                    File file1 = new File(uploadPicturePath);
                    if (!file1.exists())
                    {
                        file1.mkdirs();
                    }
                    //把图片存储到服务器中
                    image.transferTo(new File(path));
                    url = trueFileName;
                    return  url;
                }
                throw new UploadException("上传图片失败, 文件类型错误");
            }
        }
        throw new UploadException("上传图片失败, 请重新上传");
    }
}
