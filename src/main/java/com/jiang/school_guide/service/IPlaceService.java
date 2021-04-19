package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Place;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.school_guide.entity.PlaceType;
import com.jiang.school_guide.entity.form.Pagination;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
public interface IPlaceService extends IService<Place> {
    //添加地点
    ServerResponse addPlace(Place place);

    //更新地点
    ServerResponse updatePlace(Place place);

    //依据所有地点
    ServerResponse getPlace(Pagination pagination);

    //依据所属分类查询地点
    ServerResponse getPlaceByType(Pagination pagination);

    //依据搜索条件查询地点
    ServerResponse searchPlace(Pagination pagination);

    //删除地点
    ServerResponse deletePlace(Integer placeId);
}
