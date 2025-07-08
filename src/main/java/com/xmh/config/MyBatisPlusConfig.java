package com.xmh.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

@Configuration
public class MyBatisPlusConfig implements MetaObjectHandler {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("添加方法...自动填充更新和插入时间");
        this.fillStrategy(metaObject, "great_time", new Date()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "update_time",  new Date());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("修改方法....自动填充更新和插入时间");
        this.fillStrategy(metaObject, "update_time", new Date());

    }
}
