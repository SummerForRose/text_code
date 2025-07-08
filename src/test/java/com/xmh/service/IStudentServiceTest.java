package com.xmh.service;


import com.xmh.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class IStudentServiceTest {

    @Autowired
    private IStudentService studentService;

    private Student student;

    @BeforeEach
    public void before(){
        student = new Student();
    }

    //1、添加学生
    @Test
    public void insert(){
        student.setSname("赵框");
        student.setAge(24);
        student.setSex("男");
        student.setCid(1);
        studentService.save(student);
    }

    //2、单个删除学生
    @Test
    public void deleteOne(){
        //这里用到了逻辑删除（相当于假删除，实际上查询的时候会附加一个条件where deleted=0,这样的话就能实现删除后用户看不见数据，但是数据还在）
        studentService.removeById("1622431204575850497");
    }

    //2.1、批量删除学生
    @Test
    public void delete(){
        List<String> list = Arrays.asList("7","8","9");
        studentService.removeByIds(list);
    }

    //3、修改学生
    @Test
    public void updateS(){
        student.setSid("7");
        student.setSname("老王");
        studentService.updateById(student);
    }

    //4.1查询单个学生
    @Test
    public void findOne(){
        Student id = studentService.getById("1622431204575850497");
        System.out.println(id);
    }

    //查询全部
    @Test
    public void FindAll(){
        List<Student> list = studentService.list();
        for (Student s : list) {
            System.out.println(s);
        }
    }

    //方法一：分页按条件查询
    @Test
    public void ConditionalQuery(){
//        PageUtil pageBean = new PageUtil();
//        pageBean.setCurrent(1);
//        pageBean.setSize(5);
//        Page<Student> page = new Page<>(pageBean.getCurrent(),pageBean.getSize());
//
//        QueryWrapper<Student> queryChainWrapper = new QueryWrapper<>();
//        if (student!=null){
//            queryChainWrapper.like(StringUtils.isNotBlank(student.getSname()),"sname",student.getSname());
//            queryChainWrapper.ge("age",20);
//        }
//        this.page(page,queryChainWrapper);
//        pageBean.setTotal(page.getTotal());
//        List<Student> records = page.getRecords();
//        for (Student s : records) {
//            System.out.println(s);
//        }
//        System.out.println(pageBean);
    }

    //方法二
    @Test
    public void ConditionalQuery2(){
//        student.setSname("兔");
//        //1、（链式）创建条件对象
//        QueryChainWrapper<Student> wrapper = new QueryChainWrapper<>(this.baseMapper);
//        if (student!=null){
//            wrapper.like(StringUtils.isNotBlank(student.getSname()),"sname",student.getSname())
//                    .like(StringUtils.isNotBlank(student.getSex()),"sex",student.getSex());
//        }
//        //
//        PageUtil pageUtil = new PageUtil();
//        pageUtil.setCurrent(1);
//        pageUtil.setSize(5);
//        Page<Student> page = new Page<>(pageUtil.getCurrent(),pageUtil.getSize());
//        IPage<Student> iPage = wrapper.page(page);
//        pageUtil.setTotal(iPage.getTotal());
//        List<Student> list = iPage.getRecords();
//        for (Student s : list) {
//            System.out.println(s);
//        }
//        System.out.println(pageUtil);
    }


}