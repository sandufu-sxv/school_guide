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
public class Place extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地点名称
     */
    private String placeName;

    /**
     * 地点类型
     */
    private Integer typeId;

    /**
     * 地点首页图片
     */
    private String homePicture;

    /**
     * 地点经度
     */
    private Double longitude;

    /**
     * 地点纬度
     */
    private Double latitude;

    /**
     * 地点介绍
     */
    private String introduction;

    /**
     * 地点状态：-1-删除，0-正常
     */
    private Integer state;

}
