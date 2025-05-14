package com.example.demo.mapper;


import com.example.demo.pojo.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    //TODO:完善这段代码
    @Select("SELECT * FROM files WHERE id = #{id}")
    File getFileById(Long id);

    @Select("SELECT * FROM files WHERE related_id = #{relatedId} AND related_type = #{relatedType}")
    List<File> getFilesByRelatedAttr(Long relatedId, String relatedType);

    @Delete("DELETE FROM files WHERE id = #{id} ")
    void deleteFileById(Long id);

    @Update("UPDATE files SET sort_Order = #{sortOrder} WHERE id = #{id}")
    void updateFileSortOrder(File f);

    @Insert("")
    void insertFile(File myFile);
}
