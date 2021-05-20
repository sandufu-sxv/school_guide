package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Place;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IPlaceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/school_guide/place")
public class PlaceController {
    
    @Autowired
    private IPlaceService iPlaceService;
    
    @ApiOperation("添加地点信息")
    @PostMapping("/add")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse addPlace(@RequestBody @NonNull Place Place) {
        return iPlaceService.addPlace(Place);
    }

    @ApiOperation("获取所有地点（id为地点类型id）")
    @GetMapping("/getAll")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse getPlace(Pagination pagination) {
        return iPlaceService.getPlace(pagination);
    }

    @ApiOperation("依据所属分类查询地点（id为地点类型id）")
    @GetMapping("/getByType")
    public ServerResponse getPlaceByType(Pagination pagination) {
        return iPlaceService.getPlaceByType(pagination);
    }

    @ApiOperation("搜索地点信息（id为地点类型id也可不选，search为搜索内容）")
    @GetMapping("/getBySearch")
    public ServerResponse searchPlace(Pagination pagination) {
        return iPlaceService.searchPlace(pagination);
    }

    @ApiOperation("更新地点信息")
    @PostMapping("/update")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse updatePlace(@RequestBody @NonNull Place Place) {
        return iPlaceService.updatePlace(Place);
    }

    @ApiOperation("删除地点信息")
    @DeleteMapping("/delete")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deletePlace(Integer placeId) {
        return iPlaceService.deletePlace(placeId);
    }
}
