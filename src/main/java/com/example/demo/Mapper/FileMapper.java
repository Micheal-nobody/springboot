package com.example.demo.Mapper;

import com.example.demo.pojo.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO images (name, type, data,create_time) " +
            "VALUES(#{name}, #{type}, #{data}, #{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertImage(Image file);

    @Select("SELECT * FROM images WHERE id = #{id}")
    Image getImageById(Long id);
}
