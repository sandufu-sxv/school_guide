package com.jiang.school_guide.entity;

import java.time.LocalDateTime;
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
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 小程序账号id
     */
    private String openId;

    private String realName;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像path
     */
    private String headPortrait;

    /**
     * 用户手机号
     */
    private String phone;

    private String qq;

    /**
     * 用户最后在线时间
     */
    private LocalDateTime currentTime;

    /**
     * -1-删除  0-是正常 1-是封禁 2-信息不完整 3-刚注册
     */
    private Integer status;


}
