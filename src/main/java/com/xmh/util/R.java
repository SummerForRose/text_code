package com.xmh.util;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class R {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();
    
    public R(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
    public static R ok(){
        return  new R(true, ResultCode.SUCCESS,"成功");
    }
    public static R error(){
        return new R(false, ResultCode.ERROR,"失败");
    }
    public R success(Boolean success){
        this.success = success;
        return this;
    }
    public R message(String message){
        this.message = message;
        return this;
    }
    public R code(Integer code){
        this.code = code;
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.data = map;
        return this;
    }
}