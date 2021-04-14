package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.PlacePicture;
import com.jiang.school_guide.service.IPlacePictureService;
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
@RequestMapping("/school_guide/place-picture")
public class PlacePictureController {

    @Autowired
    private IPlacePictureService iPlacePictureService;

    @ApiOperation("添加图片信息")
    @PostMapping("/add")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse addPlacePicture(@RequestBody @NonNull PlacePicture PlacePicture) {
        return iPlacePictureService.addPlacePicture(PlacePicture);
    }

    @ApiOperation("查询某个地点的图片")
    @GetMapping("/get")
    public ServerResponse getPlacePicture(Integer placeId) {
        return iPlacePictureService.getPictureByPlaceId(placeId);
    }

    @ApiOperation("删除一张图片信息")
    @DeleteMapping("/delete")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deleteOnePlacePicture(Integer id) {
        return iPlacePictureService.deleteOnePlacePicture(id);
    }

    @ApiOperation("删除某个地点所有的图片信息(依据placeId)")
    @DeleteMapping("/deleteAll")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse deletePlacePicture(Integer placeId) {
        return iPlacePictureService.deletePlacePicture(placeId);
    }
}
