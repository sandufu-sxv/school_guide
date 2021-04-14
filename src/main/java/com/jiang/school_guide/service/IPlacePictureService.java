package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.PlacePicture;
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
public interface IPlacePictureService extends IService<PlacePicture> {

    //添加图片
    ServerResponse addPlacePicture(PlacePicture placePicture);

    //删除一个地点的图片
    ServerResponse deletePlacePicture(Integer placeId);

    //删除某张图片
    ServerResponse deleteOnePlacePicture(Integer placePictureId);


    //查询某个地点的图片
    ServerResponse getPictureByPlaceId(Integer placeId);


}
