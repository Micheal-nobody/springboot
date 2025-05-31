package com.example.demo.Controller;


import com.example.demo.Service.ClubService;
import com.example.demo.pojo.Entity.Club;
import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/club")
@CrossOrigin(origins = "*")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Result getById(@PathVariable Long id) {
        Club club = clubService.getById(id);

        System.out.println("club: " + club);

        return Result.success(club);
    }

    @GetMapping("/getWithFormById/{id}")
    public Result getWithFormById(@PathVariable Long id) {
        return Result.success(clubService.getWithFormById(id));
    }
}
