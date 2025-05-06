package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPermissionMapper{


    @Select("select user_permission.permission from user_permission where user_id = #{id};")
    List<String> getPermissionsByUserId(Long id);
}
