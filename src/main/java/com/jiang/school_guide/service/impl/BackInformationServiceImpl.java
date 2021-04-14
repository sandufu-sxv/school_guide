package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.BackInformation;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.dao.BackInformationMapper;
import com.jiang.school_guide.service.IBackInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-09
 */
@Service
public class BackInformationServiceImpl extends ServiceImpl<BackInformationMapper, BackInformation> implements IBackInformationService {

    @Autowired
    private BackInformationMapper backInformationMapper;

    @Override
    public ServerResponse addBackInformation(BackInformation backInformation) {
        if("user".equals(TokenUntil.getRoleByToken())){
            backInformation.setUserId(TokenUntil.getIdByToken());
        }
        backInformation.setCreateTime(LocalDateTime.now());
        backInformation.setUpdateTime(LocalDateTime.now());
        if(backInformationMapper.insert(backInformation) == 1){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getBackInformation(Pagination pagination) {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<BackInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<BackInformation> backInformationList = backInformationMapper.selectList(queryWrapper);
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        PageInfo<BackInformation> pageInfo = new PageInfo<>(backInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse updateBackInformation(BackInformation backInformation) {
        backInformation.setUpdateTime(LocalDateTime.now());
        UpdateWrapper<BackInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", backInformation.getId());
        if(backInformationMapper.update(backInformation,updateWrapper) ==1){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deleteBackInformation(Integer backInformationId) {
        if(backInformationMapper.deleteById(backInformationId) == 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
