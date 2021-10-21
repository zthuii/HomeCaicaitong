package com.example.homecaicaitong.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;// 定义数据库版本号
    private static final String DBNAME = "account.db";// 定义数据库名

    public DBOpenHelper(Context context) {// 定义构造函数

        super(context, DBNAME, null, VERSION);// 重写基类的构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db) {// 创建数据库
        db.execSQL("create table tb_outaccount (_id integer primary key,money decimal,time varchar(10),"
                + "type varchar(10),address varchar(100),mark varchar(200))");// 创建支出信息表
        db.execSQL("create table tb_inaccount (_id integer primary key,money decimal,time varchar(10),"
                + "type varchar(10),handler varchar(100),mark varchar(200))");// 创建收入信息表
        db.execSQL("create table tb_pwd (password varchar(20))");// 创建密码表
        db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))");// 创建便签信息表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {// 覆写基类的onUpgrade方法，以便数据库版本更新

    }
}
