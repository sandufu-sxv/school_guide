package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Place;
import com.jiang.school_guide.dao.PlaceMapper;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IPlacePictureService;
import com.jiang.school_guide.service.IPlaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.school_guide.service.IReplyService;
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
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements IPlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private IReplyService iReplyService;

    @Autowired
    private IPlacePictureService iPlacePictureService;

    @Override
    public ServerResponse addPlace(Place place) {
        place.setCreateTime(LocalDateTime.now());
        place.setUpdateTime(LocalDateTime.now());
        place.setState(0);
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_name",place.getPlaceName());
        if(placeMapper.selectCount(queryWrapper) > 0){
            return ServerResponse.createByErrorMessage("已存在此地点，请勿重复添加");
        }
        if(placeMapper.insert(place) == 1){
            return ServerResponse.createBySuccessMessage("添加地点成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse updatePlace(Place place) {
        place.setUpdateTime(LocalDateTime.now());
        if(placeMapper.updateById(place) == 1){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getPlace(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Place> placeList = placeMapper.selectList(null);
        PageInfo<Place> pageInfo = new PageInfo<>(placeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getPlaceByType(Pagination pagination) {
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id",pagination.getId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Place> placeList = placeMapper.selectList(queryWrapper);
        PageInfo<Place> pageInfo = new PageInfo<>(placeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse searchPlace(Pagination pagination) {
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("place_name",pagination.getSearch());
        if(pagination.getId() != null){
            queryWrapper.eq("type_id",pagination.getId());
        }
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Place> placeList = placeMapper.selectList(queryWrapper);
        PageInfo<Place> pageInfo = new PageInfo<>(placeList);
        return ServerResponse.createBySuccess(pageInfo);
    }


    @Override
    public ServerResponse deletePlace(Integer placeId) {
        Place place = placeMapper.selectById(placeId);
        place.setState(1);
        place.setUpdateTime(LocalDateTime.now());
        if(placeMapper.updateById(place) == 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
