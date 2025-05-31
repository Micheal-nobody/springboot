package com.example.demo.Service;


import com.example.demo.Mapper.ClubMapper;
import com.example.demo.Service.Form.FormService;
import com.example.demo.pojo.DTO.ClubDTO;
import com.example.demo.pojo.Entity.Club;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClubService {
    @Autowired
    private FormService formService;

    @Autowired
    private ClubMapper clubMapper;

    public List<Club> getAllClubs() {
        return clubMapper.selectList(null);
    }

    public Club getById(Long id) {
        return clubMapper.selectById(id);
    }


    public ClubDTO getWithFormById(Long id) {
        return clubMapper.selectWithFormById(id);
    }
}
