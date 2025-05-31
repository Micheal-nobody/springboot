package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.Entity.Form.Form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FormMapper extends BaseMapper<Form> {

    //    按照clubId查询
    List<Form> getByClubId(Long clubId);

    //     按照status查询
    List<Form> getFormByStatus(FormStatus status);

    //     按照status查询已提交的表单
    List<Form> getSubmittedForms(FormStatus status);

    //     按照name查询
    List<Form> getFormsByName(String name);

    //    删除
    @Update("UPDATE forms SET is_deleted WHERE id=#{id}")
    void softDeleteFormById(Long id);

    //    插入数据并返回id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO forms(name,  club_id, created_time, updated_time) VALUES(#{name},#{clubId}, #{createdTime}, #{updatedTime})")
    void insertForm(Form form);

    //    更新数据
    @Update("UPDATE forms SET name = #{name}, club_id = #{clubId}, is_deleted = #{isDeleted}, created_time = #{createdTime}, updated_time = #{updatedTime} WHERE id = #{id}")
    void updateForm(Form form);


    @Update("UPDATE forms SET status = #{status} WHERE id = #{id}")
    void updateStatus(Long id, FormStatus status);

    Form getFormById(Long id);
}
