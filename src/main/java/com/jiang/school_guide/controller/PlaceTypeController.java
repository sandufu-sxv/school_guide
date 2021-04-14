package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.PlaceType;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IPlaceTypeService;
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
@RequestMapping("/school_guide/place-type")
public class PlaceTypeController {

    @Autowired
    private IPlaceTypeService iPlaceTypeService;

    @ApiOperation("添加地点类型信息")
    @PostMapping("/add")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse addPlaceType(@RequestBody @NonNull PlaceType PlaceType) {
        return iPlaceTypeService.addPlaceType(PlaceType);
    }

    @ApiOperation("查询所有地点类型")
    @GetMapping("/get")
    public ServerResponse getPlaceType(Pagination pagination) {
        return iPlaceTypeService.getPlaceType(pagination);
    }

    @ApiOperation("删除地点类型信息")
    @DeleteMapping("/delete")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deleteOnePlaceType(Integer placeTypeId) {
        return iPlaceTypeService.deletePlaceType(placeTypeId);
    }

    @ApiOperation("更新某个地点所有的地点类型信息")
    @PostMapping("/update")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deletePlaceType(@RequestBody @NonNull PlaceType PlaceType) {
        return iPlaceTypeService.updatePlaceType(PlaceType);
    }
}
