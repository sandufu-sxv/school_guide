package com.jiang.school_guide.entity.vo;

import com.jiang.school_guide.entity.Place;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class PlaceVo extends Place {
    //地点图片路径的url
    private List<String> pictureUrl;
}
