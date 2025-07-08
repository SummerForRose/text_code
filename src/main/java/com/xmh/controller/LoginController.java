package com.xmh.controller;

import com.xmh.mapper.LoginMapper;
import com.xmh.model.Login;
import com.xmh.model.User;
import com.xmh.util.JwtUtil;
import com.xmh.util.R;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@CrossOrigin//允许跨域
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    //1、登录
    @PostMapping("/login")
    public R login(Login login){
        System.out.println("账号："+login.getUsername()+"，密码："+login.getPassword());
        Login userLogin = loginMapper.login(login);
        if(userLogin==null){
            return R.error().message("账号或密码错误");
        }else {
            String token = JwtUtil.getJwtToken(login.getUsername());
            return R.ok().data("token",token).data("avatar",userLogin.getUserImag()).message("登陆成功");
        }

    }

    //2、用户信息
    @GetMapping("/info")
    public R info(String token){
        System.out.println("token："+token);
        Claims checkToken = JwtUtil.getCheckToken(token);
        String username = checkToken.getSubject(); // 获取 sub 声明
        long iat = checkToken.getIssuedAt().getTime() / 1000L; // 获取 iat 声明，转换为秒
        long exp = checkToken.getExpiration().getTime() / 1000L; // 获取 exp 声明，转换为秒

        // 将时间戳转换为年月日时分秒格式
        String iatFormatted = formatTimestamp(iat);
        String expFormatted = formatTimestamp(exp);

        System.out.println("用户名："+username+"，iat："+iatFormatted+"，exp："+expFormatted);
        String UserImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTftnrw5W7o27tBrkqi6pt5NIooiPOVm8kagQ&s";
        return R.ok().data("name",username).data("avatar",UserImage).data("iat", iatFormatted).data("exp", expFormatted);
    }

    //3、用户信息
    // 3、用户退出
    @PostMapping("/logout")
    public R logout() {
        // 在这里可以添加使令牌失效的逻辑，例如将令牌存储在Redis中并设置过期时间
        System.out.println("退出成功");
        return R.ok().message("退出成功");
    }

    // 时间戳格式化方法
    private String formatTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
