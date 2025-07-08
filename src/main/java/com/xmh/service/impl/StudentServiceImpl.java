package com.xmh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xmh.model.Class;
import com.xmh.model.Student;
import com.xmh.mapper.StudentMapper;
import com.xmh.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassServiceImpl classService;

    @Override
    public PageResult<Student> listAll(PageUtil pageUtil, Student student) {

        Page<Student> page = new Page<>(pageUtil.getCurrent(),pageUtil.getSize());

        // 查询条件
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (student!=null){
            queryWrapper.like(StringUtils.isNotBlank(student.getSname()),"sname",student.getSname());
            if (student.getCid()!=null){
                queryWrapper.like("cid",student.getCid());
            }
        }
        // 分页查询
        this.page(page,queryWrapper);
        List<Student> records = page.getRecords();// 获取分页数据
        for (Student s : records) {
            Class id = classService.getById(s.getCid());
            s.setCname(id.getCname());
            System.out.println(s);
        }
        PageResult<Student> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setRows(records);
        return result;
    }
}
