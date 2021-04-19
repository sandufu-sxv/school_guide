package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.school_guide.entity.form.Pagination;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
public interface IAdminService extends IService<Admin> {
    //登录
    ServerResponse login(Admin admin);

    //添加管理员(手机号码不可为空，且手机号码不可重复)
    ServerResponse addAdmin(Admin admin);

    ServerResponse getAdmin(Pagination pagination);

    //删除管理员
    ServerResponse deleteAdmin(Admin admin);

    //更新管理员信息
    ServerResponse updateAdmin(Admin admin);
}
