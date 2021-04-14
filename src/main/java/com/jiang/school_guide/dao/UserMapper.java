package com.jiang.school_guide.dao;

import com.jiang.school_guide.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
