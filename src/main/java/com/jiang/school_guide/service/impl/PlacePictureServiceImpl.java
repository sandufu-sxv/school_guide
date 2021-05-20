package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.PlacePicture;
import com.jiang.school_guide.dao.PlacePictureMapper;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IPlacePictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class PlacePictureServiceImpl extends ServiceImpl<PlacePictureMapper, PlacePicture> implements IPlacePictureService {

    @Autowired
    private PlacePictureMapper placePictureMapper;

    @Override
    public ServerResponse addPlacePicture(PlacePicture placePicture) {
        LocalDateTime now = LocalDateTime.now();
        placePicture.setCreateTime(now);
        placePicture.setUpdateTime(now);
        if(placePictureMapper.insert(placePicture) == 1){
            QueryWrapper<PlacePicture> queryWrapper = new QueryWrapper<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            queryWrapper.eq("image",placePicture.getImage())
                    .eq("place_id",placePicture.getPlaceId())
                    .eq("create_time",formattedDateTime);
            PlacePicture placePicture1 = placePictureMapper.selectOne(queryWrapper);
            return ServerResponse.createBySuccessMessage("添加成功",placePicture1);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deletePlacePicture(Integer placeId) {
        QueryWrapper<PlacePicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_id",placeId);
        int size = placePictureMapper.selectList(queryWrapper).size();
        if(placePictureMapper.delete(queryWrapper) == size){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse deleteOnePlacePicture(Integer placePictureId) {
        QueryWrapper<PlacePicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",placePictureId);
        if(placePictureMapper.delete(queryWrapper) == 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getPictureByPlaceId(Integer placeId) {
        QueryWrapper<PlacePicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_id",placeId);
        List<PlacePicture> placePictureList = placePictureMapper.selectList(queryWrapper);
        if(placePictureList != null){
            return ServerResponse.createBySuccess(placePictureList);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
