package com.example.demo.Controller.Form;

import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.Form.Form;
import com.example.demo.pojo.Result;
import com.example.demo.Service.Form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/form")
//允许所有网页访问
@CrossOrigin(origins = "*")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    //region 各种GET请求
    @GetMapping("/getClubs")
    public Result test() {
        System.out.println("test");
        return Result.success();
    }

//
    @GetMapping("/getByClubId/{clubId}")
    public Result getByClubId(@PathVariable Long clubId) {
        return Result.success(formService.getByClubId(clubId));
    }

//    返回所有表单
    @GetMapping("/getAll")
    public Result getAllForms() {
        System.out.println(" getAllForms ");
        return Result.success(formService.getAllForms());
    }

//    获得所有提交的表单
    @GetMapping("/getSubmitted")
    public Result getSubmittedForms() {
        return Result.success(formService.getSubmittedForms());
    }

//    根据status获取表单
    @GetMapping("/getByStatus/{status}")
    public Result getFormsByStatus(@PathVariable FormStatus status) {
        return Result.success(formService.getFormsByStatus(status));
    }

//    根据clubName获取表单
    @GetMapping("/getByClubName")
    public Result getFormsByClubName(String clubName) {
        return Result.success(formService.getFormsByClubName(clubName));
    }

//    根据name获取表单
    @GetMapping("/getByName")
    public Result getFormsByName(String name) {
        return Result.success(formService.getFormsByName(name));
    }
//endregion

    //region admin操作表单

    //更新全部表单
    @PutMapping("/update")
    public Result update(@RequestBody Form form) {
        formService.updateForm(form);
        return Result.success();
    }

    //创建表单，接收请求体参数name和clubId
    @PostMapping("/add")
    public Result addForm(@RequestBody Form form) {
        Form result = formService.createForm(form.getClubId(), form.getName());
        return Result.success(result);
    }

    //    删除表单
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    public Result deleteForm(@PathVariable Long id) {
        formService.deleteFormById(id);
        return Result.success();
    }


    //endregion


//    更新单个表单的状态
    @PostMapping("/updateStatus")
    public Result updateStatus(Long id,FormStatus status) {
        formService.updateStatus(id,status);
        return null;
    }

}
