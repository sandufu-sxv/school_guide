package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.common.exception.AuthenticationException;
import com.jiang.school_guide.dao.UserMapper;
import com.jiang.school_guide.entity.Reply;
import com.jiang.school_guide.dao.ReplyMapper;
import com.jiang.school_guide.entity.User;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.entity.vo.ReplyVo;
import com.jiang.school_guide.service.IReplyLikeService;
import com.jiang.school_guide.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private IReplyLikeService iReplyLikeService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse addReply(Reply reply) {
        int id = TokenUntil.getIdByToken();
        reply.setUserId(id);
        reply.setCreateTime(LocalDateTime.now());
        reply.setUpdateTime(LocalDateTime.now());
        reply.setState(0);
        if(replyMapper.insert(reply) == 1){
            return ServerResponse.createBySuccessMessage("添加评论成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getReplyByPlaceIdAndLikes(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",pagination.getId())
                .eq("type",0)
                .orderByDesc("likes")
                .orderByDesc("create_time");
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        List<ReplyVo> replyVoList  = new ArrayList<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        boolean flag = StringUtils.isEmpty(token);
        for(Reply reply : replyList){
            replyVoList.add(voReplyOne(reply, flag));
        }
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<ReplyVo> pageInfo = new PageInfo<>(replyVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getReplyByPlaceIdAndTime(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",pagination.getId())
                .eq("type",0)
                .orderByDesc("create_time");
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        List<ReplyVo> replyVoList  = new ArrayList<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        boolean flag = StringUtils.isEmpty(token);
        for(Reply reply : replyList){
            replyVoList.add(voReplyOne(reply, flag));
        }
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<ReplyVo> pageInfo = new PageInfo<>(replyVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getReplyByOneId(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",pagination.getId())
                .eq("type",1)
                .orderByDesc("create_time");
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        List<ReplyVo> replyVoList  = new ArrayList<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        boolean flag = StringUtils.isEmpty(token);
        for(Reply reply : replyList){
            replyVoList.add(voReplyTwo(reply, flag));
        }
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<ReplyVo> pageInfo = new PageInfo<>(replyVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getReplyByReports(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",1).orderByDesc("reports").orderByDesc("create_time");
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteReplyOne(Integer replyId) {
        if("admin".equals(TokenUntil.getRoleByToken())){
            Reply reply = replyMapper.selectById(replyId);
            //给用户的违规次数加一
            User user = userMapper.selectById(reply.getUserId());
            if(user.getViolations() >= 9){
                user.setStatus(1);
            }
            user.setViolations(user.getViolations() + 1);
            userMapper.updateById(user);
        }
        //删除该评论的点赞数据
        iReplyLikeService.deleteReplyLikeByReply(replyId);
        //删除此评论的子评论
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",replyId);
        List<Reply> replyList = replyMapper.selectList(queryWrapper);
        for (Reply reply1 : replyList){
            iReplyLikeService.deleteReplyLikeByReply(reply1.getId());
        }
        replyMapper.delete(queryWrapper);
        if(replyMapper.deleteById(replyId) == 1){
            return ServerResponse.createBySuccessMessage("删除评论成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deleteReplyTwo(Integer replyId) {
        if("admin".equals(TokenUntil.getRoleByToken())){
            Reply reply = replyMapper.selectById(replyId);
            //给用户的违规次数加一
            User user = userMapper.selectById(reply.getUserId());
            if(user.getViolations() >= 9){
                user.setStatus(1);
            }
            user.setViolations(user.getViolations() + 1);
            userMapper.updateById(user);
        }
        //删除该评论的点赞数据
        iReplyLikeService.deleteReplyLikeByReply(replyId);
        if(replyMapper.deleteById(replyId) == 1){
            return ServerResponse.createBySuccessMessage("删除评论成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getReply(Integer replyId) {
        Reply reply = replyMapper.selectById(replyId);
        return ServerResponse.createBySuccess(reply);
    }

    @Override
    public ServerResponse updateReply(Reply reply) {
        if(replyMapper.updateById(reply) == 1){
            return ServerResponse.createBySuccessMessage("更新评论成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }


    private ReplyVo voReplyOne(Reply reply, boolean flag){
        ReplyVo replyVo = new ReplyVo(reply);
        User user = userMapper.selectById(reply.getUserId());
        replyVo.setHeadPortrait(user.getHeadPortrait());
        if(replyVo.getAnonymous() == 1){
            replyVo.setUserName(user.getNickName());
        }else replyVo.setUserName("匿名用户");
        if (flag){
            //未登录
            replyVo.setFlag(0);
            replyVo.setCurrent(0);
        }else {
            if(reply.getUserId() == user.getId()){
                replyVo.setCurrent(1);
            }else replyVo.setCurrent(0);
            if(iReplyLikeService.getReplyLike(reply.getId())){
                replyVo.setFlag(1);
            }else replyVo.setFlag(0);
        }
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",reply.getId())
                .orderByDesc("likes")
                .orderByDesc("create_time")
                .last("limit 3");
        List<Reply> replyList = replyMapper.selectList(queryWrapper);
        List<ReplyVo> replyVoList = new ArrayList<>();
        for(Reply reply1 : replyList){
            ReplyVo replyVo1 = new ReplyVo(reply1) ;
            //不匿名
            if(replyVo1.getAnonymous() == 1){
                User user1 = userMapper.selectById(reply1.getUserId());
                replyVo1.setUserName(user1.getNickName());
            }else replyVo1.setUserName("匿名用户");
            replyVoList.add(replyVo1);
        }
        replyVo.setReplyList(replyVoList);
        QueryWrapper<Reply> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("root_id",reply.getId());
        int count = replyMapper.selectCount(queryWrapper);
        replyVo.setCount(count);
        return replyVo;
    }

    private ReplyVo voReplyTwo(Reply reply, boolean flag){
        ReplyVo replyVo = new ReplyVo(reply);
        User user = userMapper.selectById(reply.getUserId());
        replyVo.setHeadPortrait(user.getHeadPortrait());
        if(replyVo.getAnonymous() == 1){
            replyVo.setUserName(user.getNickName());
        }else replyVo.setUserName("匿名用户");
        if (flag){
            //未登录
            replyVo.setFlag(0);
            replyVo.setCurrent(0);
        }else {
            if(reply.getUserId() == user.getId()){
                replyVo.setCurrent(1);
            }else replyVo.setCurrent(0);
            if(iReplyLikeService.getReplyLike(reply.getId())){
                replyVo.setFlag(1);
            }else replyVo.setFlag(0);
        }
        return replyVo;
    }

    @Override
    public ServerResponse deleteReplyByPlace(Integer placeId) {
        return null;
    }
}
