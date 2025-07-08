package com.xmh.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_login")
public class Login {

    private String username;
    private String password;
    private String token;
    private String role;
    private String userImag;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUserImag() {
        return userImag;
    }
    public void setUserImag(String userImag) {
        this.userImag = userImag;
    }
    @Override
    public String toString() {
        return "login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                ", userImag='" + userImag + '\'' +
                '}';
    }

}
