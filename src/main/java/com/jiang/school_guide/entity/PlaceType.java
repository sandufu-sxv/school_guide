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
public class PlaceType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地点类型
     */
    @ApiModelProperty(value = "地点类型")
    private String name;

    /**
     * 地点状态：0-正常;1-删除
     */
    @ApiModelProperty(value = "地点状态：0-正常;1-删除")
    private Integer state;

}
