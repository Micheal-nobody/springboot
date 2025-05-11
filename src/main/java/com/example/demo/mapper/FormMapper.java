package com.example.demo.mapper;

import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.Form.Form;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FormMapper{


//    按照clubId查询
    public List<Form> getByClubId(Long clubId);

//     按照status查询
    public List<Form> getFormByStatus(FormStatus status);

//     按照status查询已提交的表单
    public List<Form> getSubmittedForms(FormStatus status);

//     按照name查询
    public List<Form> getFormsByName(String name);


//    删除
    @Delete("DELETE FROM forms WHERE id = #{id}")
    public void deleteFormById(Long id);

//    插入数据并返回id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO forms(name,  club_id, created_time, updated_time) VALUES(#{name},#{clubId}, #{createdTime}, #{updatedTime})")
    public void insertForm(Form form);

//    更新数据
    @Update("UPDATE forms SET name = #{name}, club_id = #{clubId}, is_deleted = #{isDeleted}, created_time = #{createdTime}, updated_time = #{updatedTime} WHERE id = #{id}")
    public void updateForm(Form form);


    @Update("UPDATE forms SET status = #{status} WHERE id = #{id}")
    public void updateStatus(Long id, FormStatus status);

    Form getFormById(Long id);
}
