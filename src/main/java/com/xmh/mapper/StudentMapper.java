package com.xmh.mapper;

import com.xmh.model.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-05
 */
@Repository //@Repository代表数据访问层，一般用在dao接口类上，表示该接口是一个数据访问接口
public interface StudentMapper extends BaseMapper<Student> {

}
