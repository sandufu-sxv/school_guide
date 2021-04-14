package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Reply;
import com.jiang.school_guide.service.IReplyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/school_guide/reply")
public class ReplyController {

    @Autowired
    private IReplyService iReplyService;

    //添加评论
    @ApiOperation("添加管理员")
    @PostMapping("/add")
    @Permission(roles = {Const.USER})
    public ServerResponse addAdmin(@RequestBody @NonNull Reply reply) {
        return iReplyService.addReply(reply);
    }

    //查询评论
    @ApiOperation("添加管理员")
    @PostMapping("/add")
    @Permission(roles = {Const.USER})
    public ServerResponse addAdmin(@RequestBody @NonNull Reply reply) {
        return iReplyService.addReply(reply);
    }

}
