package com.jiang.school_guide.common.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.common.exception.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUntil {

    public static int getIdByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token)){
            throw new AuthenticationException("请先登录");
        }
        // 解密token
        token = JWTUtil.decryptToken(token);
        Integer id = JWTUtil.getId(token);
        String role = JWTUtil.getRole(token);
        // 校验token是否合法
        if (!JWTUtil.verify(token, id, role)){
            throw new AuthenticationException("token校验不通过");
        }
        return  id;
    }

    public static String refreshToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token))
            throw new AuthenticationException("请先登录");
        token = JWTUtil.decryptToken(token);
        // 解密token
        Integer id = JWTUtil.getId(token);
        System.out.println(id);
        if (!JWTUtil.verify(token, id)){
            throw new AuthenticationException("token校验不通过");
        }
        token = JWTUtil.encryptToken(JWTUtil.sign(id));
        return  token;
    }

    public static String getRoleByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token)){
            throw new AuthenticationException("请先登录");
        }
        // 解密token
        token = JWTUtil.decryptToken(token);
        Integer id = JWTUtil.getId(token);
        String role = JWTUtil.getRole(token);
        // 校验token是否合法
        if (!JWTUtil.verify(token, id, role)){
            throw new AuthenticationException("token校验不通过");
        }
        return  role;
    }

    public static ServerResponse getIdByToken(String token) {
        if (StringUtils.isEmpty(token))
            return ServerResponse.createByErrorMessage("请先登录");
        // 解密token
        token = JWTUtil.decryptToken(token);
        Integer id = JWTUtil.getId(token);
        // 校验token是否合法
        if (!JWTUtil.verify(token, id)){
            return ServerResponse.createByErrorMessage("token不合法");
        }
        System.out.println("token有效");
        return  ServerResponse.createBySuccess(id);
    }

    public static boolean isLogin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        return !StringUtils.isEmpty(token);
    }
}
