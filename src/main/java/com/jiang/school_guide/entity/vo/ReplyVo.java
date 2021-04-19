package com.jiang.school_guide.entity.vo;

import com.jiang.school_guide.entity.Reply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class ReplyVo extends Reply {

    @ApiModelProperty(value = "当前用户是否点赞（0-否；1-是），若是用户未登录，统一为0")
    private Integer flag;

    @ApiModelProperty(value = "当前评论是否时当前用户的评论（0-否；1-是），若是用户未登录，统一为0")
    private Integer current;

    @ApiModelProperty(value = "二级评论的数量")
    private Integer count;

    @ApiModelProperty(value = "当前评论的用户名")
    private String userName;

    @ApiModelProperty(value = "当前评论的用户头像")
    private String headPortrait;

    @ApiModelProperty(value = "一级评论下点赞最高的三条评论")
    private List<ReplyVo> replyList;

    public ReplyVo(Reply reply){
        BeanUtils.copyProperties(reply, this);
    }
}
