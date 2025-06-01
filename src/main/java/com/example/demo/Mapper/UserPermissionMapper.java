package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserPermissionMapper {


    //TODO：是的，这就是一个权限表，没有用户表链接
    @Select("select permission from user_permission where user_id = #{id};")
    String getPermissionsByUserId(Long id);
}
