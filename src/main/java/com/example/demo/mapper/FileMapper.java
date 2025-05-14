package com.example.demo.mapper;


import com.example.demo.pojo.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    //TODO:完善这段代码
    @Select("SELECT * FROM files WHERE id = #{id}")
    File getFileById(Long id);

    @Select("SELECT * FROM files WHERE relared_id = #{relatedId} related_type = #{relatedType}")
    List<File> getFilesByRelatedAttr(Long relatedId, String relatedType);

    @Delete("DELETE ")
    void deleteFileById(Long id);

    @Update("UPDATE sort_Order ")
    void updateFileSortOrder(File f);

    @Insert("")
    void insertFile(File myFile);
}
