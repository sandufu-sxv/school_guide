package com.jiang.school_guide.entity;

import com.jiang.school_guide.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
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
public class PlacePicture extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地点id
     */
    private Integer placeId;

    /**
     * 展示图片路径
     */
    private String image;


}
