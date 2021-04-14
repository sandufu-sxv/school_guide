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
    public ServerResponse getReplyByPlaceId(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_id",pagination.getId())
                .eq("type",0)
                .orderByDesc("likes")
                .orderByDesc("create_time");
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getReplyByOneId(Pagination pagination) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_id",pagination.getId());
        List<Reply> replyList  = replyMapper.selectList(queryWrapper);
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteReplyOne(Integer replyId) {
        //删除该评论的点赞数据
        iReplyLikeService.deleteReplyLikeByReply(replyId);
        //删除此评论的子评论
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id",replyId);
        replyMapper.delete(queryWrapper);
        if(replyMapper.deleteById(replyId) == 1){
            return ServerResponse.createBySuccessMessage("删除评论成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deleteReplyTwo(Integer replyId) {
        //删除该评论的点赞数据
        iReplyLikeService.deleteReplyLikeByReply(replyId);
        //删除此评论的子评论
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_id",replyId);
        replyMapper.delete(queryWrapper);
        //删除此评论的子评论
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


    private ReplyVo voReply(Reply reply){
        ReplyVo replyVo = (ReplyVo) reply;
        User user = userMapper.selectById(reply.getUserId());
        replyVo.setHeadPortrait(user.getHeadPortrait());
        replyVo.setUserName(user.getNickName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token)){
            //未登录
            replyVo.setFlag(0);
        }else {
            if(iReplyLikeService.getReplyLike(reply.getId())){
                replyVo.setFlag(1);
            }else replyVo.setFlag(0);
        }
        QueryWrapper<Reply>

    }

    @Override
    public ServerResponse deleteReplyByPlace(Integer placeId) {
        return null;
    }
}
