package com.xmh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmh.model.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper extends BaseMapper<Login> {
    @Select("select * from t_login where username=#{username} and password=#{password}")
    Login login(Login login);
}
