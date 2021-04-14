package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.school_guide.common.authentication.JWTUtil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Admin;
import com.jiang.school_guide.dao.AdminMapper;
import com.jiang.school_guide.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ServerResponse login(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",admin.getUserName())
                .eq("password",admin.getPassword());
        admin = adminMapper.selectOne(queryWrapper);
        if(admin == null){
            return ServerResponse.createByErrorMessage("账号或密码错误");
        }
        String token = JWTUtil.encryptToken(JWTUtil.sign(admin.getId(), Const.ADMIN));
        return ServerResponse.createBySuccess(token);
    }


    @Override
    public ServerResponse addAdmin(Admin admin) {
        if(admin.getPhone() == null){
            return ServerResponse.createByErrorMessage("电话号码不能为空");
        }
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",admin.getPhone());
        if(adminMapper.selectList(queryWrapper) != null){
            return ServerResponse.createByErrorMessage("手机号码重复");
        }
        if(adminMapper.insert(admin) == 1){
            return ServerResponse.createBySuccessMessage("添加管理员成功");
        }
         return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deleteAdmin(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", admin.getId());
        if(adminMapper.delete(queryWrapper) == 1){
            return ServerResponse.createByErrorMessage("删除用户名为：" + admin.getUserName() + "的管理员成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse updateAdmin(Admin admin) {
        if(admin.getPhone() == null){
            return ServerResponse.createByErrorMessage("电话号码不能为空");
        }
        admin.setUpdateTime(LocalDateTime.now());
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",admin.getPhone());
        if(adminMapper.selectList(queryWrapper) != null){
            return ServerResponse.createByErrorMessage("手机号码重复");
        }
        QueryWrapper<Admin> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id",admin.getId());
        if(adminMapper.update(admin,queryWrapper) == 1){
            return ServerResponse.createBySuccessMessage("更新信息成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
