package com.example.demo.Service;


import com.example.demo.Mapper.ClubMapper;
import com.example.demo.Service.Form.FormService;
import com.example.demo.pojo.Club;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    private FormService formService;

    @Autowired
    private ClubMapper clubMapper;

    public List<Club> getAllClubs() {
        return clubMapper.getAllClubs();
    }

    public Club getById(Long id) {
        return clubMapper.getClubById(id);
    }

//    public Club getClubWithForms(Long clubId) {
//        Club club = clubMapper.getClubById(clubId);
//        club.setForms(formService.getFormsByClubId(clubId));
//        return club;
//    }

}
