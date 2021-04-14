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
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Feedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈人
     */
    private Integer userId;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 0-未处理；1-已处理
     */
    private Integer state;

    /**
     * 处理人
     */
    private Integer adminId;

}
