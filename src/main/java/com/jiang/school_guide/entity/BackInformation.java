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
public class BackInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈给用户的信息
     */
    private String content;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     *0-未读；1-已读
     */
    private Integer state;

}
