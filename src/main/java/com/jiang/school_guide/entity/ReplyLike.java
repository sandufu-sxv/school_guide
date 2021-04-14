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
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class ReplyLike extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞人id
     */
    @ApiModelProperty(value = "点赞人id")
    private Integer userId;

    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer replyId;


}
