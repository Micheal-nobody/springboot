package com.example.demo.pojo;


import com.example.demo.pojo.ENUM.TableType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Image {
    private Long id;
    private String name;
    private TableType type;
    private byte[] data;
    private String create_time;
}
