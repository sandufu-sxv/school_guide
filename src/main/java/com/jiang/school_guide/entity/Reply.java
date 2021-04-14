package com.jiang.school_guide.entity;

import com.jiang.school_guide.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class Reply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 地点id或者为一级评论id
     */
    @ApiModelProperty(value = "地点id或者为一级评论id")
    private Integer rootId;

    /**
     * 二级评论所回复的信息的id
     */
    @ApiModelProperty(value = "二级评论所回复的信息的id")
    private Integer replyId;

    /**
     * 所回复评论的用户名，若回复的是匿名消息，则为“匿名用户”
     */
    @ApiModelProperty(value = "所回复评论的用户名，若回复的是匿名消息，则为“匿名用户”")
    private String fatherName;

    /**
     * 0-回复帖子 1-回复非帖子的信息
     */
    @ApiModelProperty(value = "0-回复帖子 1-回复非帖子的信息")
    private Integer type;

    /**
     * 回复评论的内容
     */
    @ApiModelProperty(value = "回复评论的内容")
    private String content;

    /**
     * 0-匿名；1-不匿名
     */
    @ApiModelProperty(value = "0-匿名；1-不匿名")
    private Integer anonymous;

    /**
     * 0-正常；1-被举报
     */
    @ApiModelProperty(value = "0-正常；1-被举报")
    private Integer state;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    /**
     * 举报数
     */
    @ApiModelProperty(value = "举报数")
    private Integer reports;

}
