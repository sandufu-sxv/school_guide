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
public class PlaceType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地点类型
     */
    private String type;

    /**
     * 地点状态：-1-删除，0-正常
     */
    private Integer state;

}
