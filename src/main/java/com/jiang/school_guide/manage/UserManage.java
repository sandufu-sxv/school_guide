package com.jiang.school_guide.manage;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jiang.school_guide.common.authentication.JWTUtil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.common.exception.AuthenticationException;
import com.jiang.school_guide.untils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * @ClassName UserManage
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/11/2516:22
 * @Version 1.0
 **/
@Component
public class UserManage {

  /* @Value("${upload.picture.path}")
    private String uploadPicturePath;

    public static String getRoleByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(token))
            throw new AuthenticationException("请登录后访问！");
        token = JWTUtil.decryptToken(token);            // 解密token
        Integer id = JWTUtil.getId(token);
        Integer role = JWTUtil.getRole(token);
        // 校验token是否合法
        if (role==null||!JWTUtil.verify(token,id,role))
            throw new AuthenticationException("token校验不通过");
        return role==1? Const.USER: Const.ADMIN;
    }


    public static Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token))
            throw new AuthenticationException("请登录后访问！");
        token = JWTUtil.decryptToken(token);            // 解密token
        Integer id = JWTUtil.getId(token);
        Integer role = JWTUtil.getRole(token);
        // 校验token是否合法
        if (role==null||!JWTUtil.verify(token,id,role))
            throw new AuthenticationException("token校验不通过");
        return id;
    }

    public ServerResponse uploadAvatar(MultipartFile image) throws IOException {
        String url="";
        //检查图片的大小
        boolean flag = FileUtil.checkFileSize(image.getSize(), 5, "M");
        if (!flag)
        {
            //图片大小超限
            return ServerResponse.createByErrorMessage("图片大小超过限制(5M), 请重新上传");
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
                    return ServerResponse.createBySuccessMessage("上传成功!",url);
                }
                return ServerResponse.createByErrorMessage("上传图片失败, 文件类型错误");
            }
        }
        return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
    }*/

}
