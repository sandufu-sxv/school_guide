package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.BackInformation;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IBackInformationService;
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
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/school_guide/back-information")
public class BackInformationController {

    @Autowired
    private IBackInformationService iBackInformationService;

    @ApiOperation("添加反馈信息")
    @PostMapping("/add")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse addBackInformation(@RequestBody @NonNull BackInformation backInformation) {
        return iBackInformationService.addBackInformation(backInformation);
    }

    @ApiOperation("用户获取自己的反馈信息")
    @GetMapping("/get")
    @Permission(roles = {Const.USER})
    public ServerResponse getBackInformation(Pagination pagination) {
        return iBackInformationService.getBackInformation(pagination);
    }

    @ApiOperation("用户更新自己的反馈信息（未读到已读）")
    @PostMapping("/update")
    @Permission(roles = {Const.USER})
    public ServerResponse addAdmin(@RequestBody @NonNull BackInformation backInformation) {
        return iBackInformationService.updateBackInformation(backInformation);
    }

    @ApiOperation("用户删除自己的反馈信息")
    @DeleteMapping("/delete")
    @Permission(roles = {Const.USER})
    public ServerResponse deleteBackInformation(Integer id) {
        return iBackInformationService.deleteBackInformation(id);
    }

}

