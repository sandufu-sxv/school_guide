package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Admin;
import com.jiang.school_guide.entity.User;
import com.jiang.school_guide.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/school_guide/user")
public class UserController {

    @Autowired
    private IUserService iuserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ServerResponse login(String code) {
        return iuserService.login(code);
    }

    @ApiOperation("获取个人信息（自己）")
    @GetMapping("/getInfo")
    @Permission(roles = {Const.USER})
    public ServerResponse getInfo() {
        return iuserService.getInfo();
    }

    @ApiOperation("获取个人信息（管理员依据Id）")
    @GetMapping("/get")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse get(Integer userId) {
        User user = iuserService.getById(userId);
        return ServerResponse.createBySuccess(user);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    @Permission(roles = {Const.USER,Const.ADMIN})
    public ServerResponse updateAdmin(@RequestBody @NonNull User user) {
        return iuserService.updateInfo(user);
    }

}
