package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    String findOneByDepartmentAndType(@Param("department") String department,@Param("type")String type);
    String findOneByType(@Param("type")String type);
}
