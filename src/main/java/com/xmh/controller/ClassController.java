package com.xmh.controller;


import com.xmh.model.Class;
import com.xmh.service.IClassService;
import com.xmh.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassService classService;

    @RequestMapping("/list")
    public JsonData findAll(){
        List<Class> list = classService.list();
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(list);
        return jsonData;
    }

}
