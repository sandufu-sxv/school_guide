package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.PlaceType;
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
public interface IPlaceTypeService extends IService<PlaceType> {
    //添加地点类型
    ServerResponse addPlaceType(PlaceType placeType);

    //删除地点类型
    ServerResponse deletePlaceType(Integer placeTypeId);

    //获取所有地点类型
    ServerResponse getPlaceType(Pagination pagination);

    //更新地点类型
    ServerResponse updatePlaceType(PlaceType placeType);
}
