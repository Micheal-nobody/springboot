package com.example.demo.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    //TODO:完善这段代码

    @Select("SELECT * FROM files WHERE related_id = #{relatedId} AND related_type = #{relatedType}")
    List<File> getFilesByRelatedAttr(Long relatedId, String relatedType);

    @Update("UPDATE files SET sort_Order = #{sortOrder} WHERE id = #{id}")
    void updateFileSortOrder(File f);
}
