package com.jiang.school_guide;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.school_guide.common.authentication.JWTUtil;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.dao.AdminMapper;
import com.jiang.school_guide.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolGuideApplicationTests {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        String token = JWTUtil.encryptToken(JWTUtil.sign(1));
        System.out.println(token);
        token = JWTUtil.decryptToken(token);
        DecodedJWT jwt = JWT.decode(token);
        System.out.println(jwt.getClaim("id").asInt());
    }

    @Test
    void test2(){
        System.out.println(TokenUntil.getIdByToken("84d063ba2e2f73670f8ef26112d0f3c03411e9605210398f56f86ae90108f86185ab69e885532ac0ebd6e38c2df2ce5419812debe6186c81e7e9442b49f1433f421ec7c07f2e68ad7baf808d7c72bb2af5bd002b3cc2cbc5d6827e83806028e82d96b40f48d7175122d66ebcc7d8c6278fb4ae8bfad536fd").getData());
    }

    @Test
    void test3(){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",null);
        List<Admin> adminList = adminMapper.selectList(queryWrapper);
        System.out.println(adminList.size());
    }

}
