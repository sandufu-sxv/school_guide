package com.jiang.school_guide.entity;

import java.time.LocalDateTime;
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
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 小程序账号id
     */
    @ApiModelProperty(value = "小程序账号id")
    private String openId;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 头像path
     */
    @ApiModelProperty(value = "头像path")
    private String headPortrait;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "qq")
    private String qq;


    /**
     * 用户违规次数
     */
    @ApiModelProperty(value = "用户违规次数")
    private Integer violations;

    /**
     * -1-删除  0-是正常 1-是封禁 2-信息不完整 3-刚注册
     */
    @ApiModelProperty(value = "-1-删除  0-是正常 1-是封禁 2-信息不完整 3-刚注册")
    private Integer status;


}
