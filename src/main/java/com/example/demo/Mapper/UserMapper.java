package com.example.demo.Mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    public List<User> getAllUsers();

    //     将主键放入Emp的id属性      获取主键
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO users (username, student_id, cover, major, college, gender, account, password) " +
            "VALUES (#{username}, #{student_id}, #{cover}, #{major}, #{college}, #{gender}, #{account}, #{password})")
    public void insertUser(User user);

//     删除主键为id的记录
    @Delete("DELETE FROM users WHERE id = #{id}")
    public void deleteUserById(Integer id);

//    根据user对象删除记录
    @Delete("DELETE FROM users WHERE id=#{id}")
    public void deleteUserByUser(User user);

    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
    public User getByAccountAndPassword(User user);

    @Select("SELECT * FROM users WHERE account = #{account}")
    User getByAccount(String username);
}
