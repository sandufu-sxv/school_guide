package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
public interface IUserService extends IService<User> {
    //登录
    ServerResponse login(String code);

    //获取当前用户的所有信息
    ServerResponse getInfo();

    //更新用户信息
    ServerResponse updateInfo(User user);

    //删除用户 todo 未完成
    ServerResponse deleteUser();
}
