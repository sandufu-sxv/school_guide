package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Admin;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IAdminService;
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
@RequestMapping("/school_guide/admin")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody @NonNull Admin admin) {
        return iAdminService.login(admin);
    }

    @ApiOperation("添加管理员")
    @PostMapping("/add")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse addAdmin(@RequestBody @NonNull Admin admin) {
        return iAdminService.addAdmin(admin);
    }

    @ApiOperation("注销管理员(未真正删除，只是更新状态state)")
    @PostMapping("/delete")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deleteAdmin(@RequestBody @NonNull Admin admin) {
        admin.setState(1);
        return iAdminService.updateAdmin(admin);
    }

    @ApiOperation("获取所有管理员信息")
    @GetMapping("/get")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse getAdmin(Pagination pagination) {
        return iAdminService.getAdmin(pagination);
    }

    @ApiOperation("更新管理员信息")
    @PostMapping("/update")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse updateAdmin(@RequestBody @NonNull Admin admin) {
        return iAdminService.updateAdmin(admin);
    }

}
