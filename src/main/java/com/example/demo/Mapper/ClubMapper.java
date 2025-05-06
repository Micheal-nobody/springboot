package com.example.demo.Mapper;


import com.example.demo.pojo.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ClubMapper {

    @Select("SELECT * FROM clubs")
    List<Club> getAllClubs();

    @Select("SELECT * FROM clubs WHERE id = #{id}")
    Club getClubById(Long id);

    List<Long> getClubIdsByName(String clubName);
}
