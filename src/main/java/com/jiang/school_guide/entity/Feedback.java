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
public class Feedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 意见标题
     */
    @ApiModelProperty(value = "意见标题")
    private String title;

    /**
     * 意见人id
     */
    @ApiModelProperty(value = "意见人id")
    private Integer userId;

    /**
     * 意见内容
     */
    @ApiModelProperty(value = "意见内容")
    private String content;

    /**
     * 0-未处理；1-已处理
     */
    @ApiModelProperty(value = "0-未处理；1-已处理")
    private Integer state;

    /**
     * 处理人id
     */
    @ApiModelProperty(value = "处理人id")
    private Integer adminId;

}
