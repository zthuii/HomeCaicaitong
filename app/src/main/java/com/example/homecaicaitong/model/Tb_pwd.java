package com.example.homecaicaitong.model;


public class Tb_pwd// 密码数据表实体类
{
    private String password;// 定义字符串，表示用户密码

    public Tb_pwd() {// 默认构造函数
        super();
    }

    public Tb_pwd(String password) {// 定义有参构造函数
        super();
        this.password = password;// 为密码赋值
    }

    public String getPassword() {// 定义密码的可读属性
        return password;
    }

    public void setPassword(String password) {// 定义密码的可写属性
        this.password = password;
    }
}
