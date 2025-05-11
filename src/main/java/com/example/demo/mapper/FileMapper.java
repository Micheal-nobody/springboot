package com.example.demo.mapper;

import com.example.demo.pojo.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper{

    @Select("SELECT * FROM files WHERE id = #{id} ORDER BY sort_order")
    File getFileById(Long id);

    @Select("SELECT * FROM files WHERE related_id = #{relatedId} AND related_type = #{relatedType} ORDER BY sort_order")
    List<File> getFilesByRelatedAttr(Long relatedId, String relatedType);

    @Insert("INSERT INTO files (related_type, related_id, file_name, file_data, mime_type, sort_order, create_time)" +
            "VALUES(#{relatedType}, #{relatedId}, #{fileName}, #{fileData}, #{mimeType}, #{sortOrder}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertFile(File file);

    @Delete("DELETE FROM files WHERE id = #{id}")
    void deleteFileById(Long id);


    @Update("UPDATE files SET sort_order = #{sortOrder} WHERE id = #{id}")
    void updateFileSortOrder (File f);
}
