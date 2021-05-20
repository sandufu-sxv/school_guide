package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.dao.PlaceMapper;
import com.jiang.school_guide.entity.Place;
import com.jiang.school_guide.entity.PlaceType;
import com.jiang.school_guide.dao.PlaceTypeMapper;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IPlaceTypeService;
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
 * @since 2021-04-04
 */
@Service
public class PlaceTypeServiceImpl extends ServiceImpl<PlaceTypeMapper, PlaceType> implements IPlaceTypeService {

    @Autowired
    private PlaceTypeMapper placeTypeMapper;

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public ServerResponse addPlaceType(PlaceType placeType) {
        placeType.setState(0);
        placeType.setCreateTime(LocalDateTime.now());
        placeType.setUpdateTime(LocalDateTime.now());
        QueryWrapper<PlaceType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",placeType.getName());
        if(placeTypeMapper.selectCount(queryWrapper) > 0){
            return ServerResponse.createByErrorMessage("已存在此种类型名，请勿重复添加");
        }
        if(placeTypeMapper.insert(placeType) == 1){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deletePlaceType(Integer placeTypeId) {
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", placeTypeId);
        if(placeMapper.selectCount(queryWrapper) > 0){
            return ServerResponse.createByErrorMessage("请先删除该类型下的所有地点");
        }
        PlaceType placeType = placeTypeMapper.selectById(placeTypeId);
        placeType.setState(1);
        placeType.setUpdateTime(LocalDateTime.now());
        if(placeTypeMapper.updateById(placeType) == 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getPlaceType(Pagination pagination) {
        QueryWrapper<PlaceType> queryWrapper = new QueryWrapper<>();
        if(pagination.getStatus() != null){
            queryWrapper.eq("state",pagination.getStatus());
        }
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<PlaceType> placeTypeList = placeTypeMapper.selectList(queryWrapper);
        PageInfo<PlaceType> pageInfo = new PageInfo<>(placeTypeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse updatePlaceType(PlaceType placeType) {
        placeType.setUpdateTime(LocalDateTime.now());
        if(placeTypeMapper.updateById(placeType) == 1){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
