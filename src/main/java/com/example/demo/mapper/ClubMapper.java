package com.example.demo.mapper;

import com.example.demo.pojo.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubMapper {

    List<Long> getClubIdsByName(@Param("name") String clubName);

    @Select("SELECT * FROM clubs")
    List<Club> selectAll();

    @Select("SELECT * FROM clubs WHERE id = #{id}")
    Club selectById(Long id);
}