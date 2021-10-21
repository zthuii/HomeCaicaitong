package com.example.homecaicaitong.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
        import com.example.homecaicaitong.model.Tb_outaccount;

public class OutaccountDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public OutaccountDAO(Context context) {// 定义构造函数

        helper = new DBOpenHelper(context);// 初始化DBOpenHelper对象
    }

    /**
     * 添加支出信息
     *
     * @param tb_outaccount
     */
    public void add(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行添加支出信息操作
        db.execSQL("insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",
                new Object[] { tb_outaccount.getid(), tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(),
                        tb_outaccount.getMark() });
    }

    /**
     * 更新支出信息
     *
     * @param tb_outaccount
     */
    public void update(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改支出信息操作
        db.execSQL("update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? where _id = ?", new Object[] { tb_outaccount.getMoney(),
                tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(), tb_outaccount.getMark(), tb_outaccount.getid() });
    }

    /**
     * 查找支出信息
     *
     * @param id
     * @return
     */
    public Tb_outaccount find(int id) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id = ?", new String[] { String.valueOf(id) });// 根据编号查找支出信息，并存储到Cursor类中
        if (cursor.moveToNext()) {// 遍历查找到的支出信息

            // 将遍历到的支出信息存储到Tb_outaccount类中
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")), cursor.getString(cursor
                    .getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("mark")));
        }
        return null;// 如果没有信息，则返回null
    }

    /**
     * 刪除支出信息
     *
     * @param ids
     */
    public void detele(Integer... ids) {
        if (ids.length > 0) {// 判断是否存在要删除的id

            StringBuffer sb = new StringBuffer();// 创建StringBuffer对象
            for (int i = 0; i < ids.length; i++) {// 遍历要删除的id集合

                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
            db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除支出信息操作
            db.execSQL("delete from tb_outaccount where _id in (" + sb + ")", (Object[]) ids);
        }
    }

    /**
     * 获取支出信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_outaccount> getScrollData(int start, int count) {
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有支出信息
        Cursor cursor = db.rawQuery("select * from tb_outaccount limit ?,?", new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext()) {// 遍历所有的支出信息

            // 将遍历到的支出信息添加到集合中
            tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")), cursor
                    .getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")), cursor.getString(cursor
                    .getColumnIndex("address")), cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_outaccount;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount", null);// 获取支出信息的记录数
        if (cursor.moveToNext()) {// 判断Cursor中是否有数据

            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取支出最大编号
     *
     * @return
     */
    public int getMaxId() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);// 获取支出信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
}
