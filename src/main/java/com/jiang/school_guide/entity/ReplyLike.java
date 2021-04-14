package com.jiang.school_guide.entity;

import com.jiang.school_guide.common.domain.BaseEntity;
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
public class ReplyLike extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞人id
     */
    private Integer userId;

    /**
     * 评论id
     */
    private Integer replyId;


}
