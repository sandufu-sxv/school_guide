package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.BackInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.school_guide.entity.form.Pagination;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-09
 */
public interface IBackInformationService extends IService<BackInformation> {
    //添加反馈信息
    ServerResponse addBackInformation(BackInformation backInformation);

    //获取单个用户的反馈信息
    ServerResponse getBackInformation(Pagination pagination);

    //更新
    ServerResponse updateBackInformation(BackInformation backInformation);

    //删除某个反馈信息
    ServerResponse deleteBackInformation(Integer backInformationId);


}
