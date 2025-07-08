package com.xmh.service;

import com.xmh.model.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-05
 */
public interface IStudentService extends IService<Student> {

    PageResult<Student> listAll(PageUtil pageUtil, Student student);
}
