package com.example.homecaicaitong.model;

public class Tb_inaccount {// 收入信息实体类

    private int _id;// 存储收入编号
    private double money;// 存储收入金额
    private String time;// 存储收入时间
    private String type;// 存储收入类别
    private String handler;// 存储收入付款方
    private String mark;// 存储收入备注

    public Tb_inaccount() {// 默认构造函数
        super();
    }

    // 定义有参构造函数，用来初始化收入信息实体类中的各个字段
    public Tb_inaccount(int id, double money, String time, String type, String handler, String mark) {
        super();
        this._id = id;// 为收入编号赋值
        this.money = money;// 为收入金额赋值
        this.time = time;// 为收入时间赋值
        this.type = type;// 为收入类别赋值
        this.handler = handler;// 为收入付款方赋值
        this.mark = mark;// 为收入备注赋值
    }

    public int getid() {// 设置收入编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置收入编号的可写属性
        this._id = id;
    }

    public double getMoney() {// 设置收入金额的可读属性
        return money;
    }

    public void setMoney(double money) {// 设置收入金额的可写属性
        this.money = money;
    }

    public String getTime() {// 设置收入时间的可读属性
        return time;
    }

    public void setTime(String time) {// 设置收入时间的可写属性
        this.time = time;
    }

    public String getType() {// 设置收入类别的可读属性
        return type;
    }

    public void setType(String type) {// 设置收入类别的可写属性
        this.type = type;
    }

    public String getHandler() {// 设置收入付款方的可读属性
        return handler;
    }

    public void setHandler(String handler) {// 设置收入付款方的可写属性
        this.handler = handler;
    }

    public String getMark() {// 设置收入备注的可读属性
        return mark;
    }

    public void setMark(String mark) {// 设置收入备注的可写属性
        this.mark = mark;
    }
}
