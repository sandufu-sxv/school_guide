package com.jiang.school_guide.dao;

import com.jiang.school_guide.entity.ReplyLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evildoer
 * @since 2021-04-09
 */
@Mapper
@Repository
public interface ReplyLikeMapper extends BaseMapper<ReplyLike> {

}
