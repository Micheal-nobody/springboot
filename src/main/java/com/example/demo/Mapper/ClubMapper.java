package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.DTO.ClubDTO;
import com.example.demo.pojo.Entity.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubMapper extends BaseMapper<Club> {

    List<Long> getClubIdsByName(@Param("name") String clubName);

    ClubDTO selectWithFormById(Long id);
}