package com.xmh.controller;

import com.xmh.model.Student;
import com.xmh.service.IStudentService;
import com.xmh.util.JsonData;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    //1、查找全部学生+分页+条件
    @RequestMapping("/list")
    public JsonData findAll(@RequestParam(required = false,defaultValue = "1") int page,
                            @RequestParam(required = false,defaultValue = "5") int rows,
                            PageUtil pageUtil, Student student){
        pageUtil.setCurrent(page);
        pageUtil.setSize(rows);
        JsonData jsonData = new JsonData();
        if (student!=null){
            jsonData.setMessage("查询成功");
        }
        PageResult<Student> list = studentService.listAll(pageUtil, student);
        List<Student> studentList = list.getRows();
        pageUtil.setTotal(list.getTotal());
        System.out.println("页码："+pageUtil);
        jsonData.setPage(pageUtil.getCurrent());
        jsonData.setRows(pageUtil.getSize());
        jsonData.setTotal(pageUtil.getTotal());
        jsonData.setResult(studentList);
        jsonData.setCode(20000);
        return  jsonData;
    }
    //2、新增
    @RequestMapping("/add")
    public JsonData insert(Student student){
        studentService.save(student);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        return jsonData;
    }

    //3、修改
    @RequestMapping("/update")
    public JsonData update(Student student){
        studentService.updateById(student);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        return jsonData;
    }
    //4、删除当单个
    @RequestMapping("/delete")
    public JsonData delete(Student student) {
        studentService.removeById(student.getSid());
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        return jsonData;
    }
    //5、批量删除
    @RequestMapping("/delete/{ak}")
    public JsonData batchDel(@PathVariable String ak) {
        System.out.println("ak=" + ak);
        String[] split = ak.split(",");
        studentService.removeByIds(Arrays.asList(split));
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        return jsonData;
    }
}
