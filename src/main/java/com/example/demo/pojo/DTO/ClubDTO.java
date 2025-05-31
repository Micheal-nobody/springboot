package com.example.demo.pojo.DTO;


import com.example.demo.pojo.ENUM.ClubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDTO {
    private Long id;
    private String name;
    private String description;
    private ClubCategory category;
    private Long leaderId;

    private List<FormDTO> formDTOList = new ArrayList<>();
}
