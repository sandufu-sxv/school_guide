package com.jiang.school_guide.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Pagination {
    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty(value = "数据库信息的各种状态")
    private Integer status;
    @ApiModelProperty(value = "各种主键Id")
    private Integer Id;
    @ApiModelProperty(value = "搜索的内容")
    private String search;
}
