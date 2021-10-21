package com.example.homecaicaitong.dao;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import java.util.ArrayList;
    import java.util.List;
    import com.example.homecaicaitong.model.Tb_flag;
public class FlagDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public FlagDAO(Context context) {// 定义构造函数

        helper = new DBOpenHelper(context);// 初始化DBOpenHelper对象
    }

    /**
     * 添加便签信息
     *
     * @param tb_flag
     */
    public void add(Tb_flag tb_flag) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        db.execSQL("insert into tb_flag (_id,flag) values (?,?)", new Object[] { tb_flag.getid(), tb_flag.getFlag() });// 执行添加便签信息操作
    }

    /**
     * 更新便签信息
     *
     * @param tb_flag
     */
    public void update(Tb_flag tb_flag) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        db.execSQL("update tb_flag set flag = ? where _id = ?", new Object[] { tb_flag.getFlag(), tb_flag.getid() });// 执行修改便签信息操作
    }

    /**
     * 查找便签信息
     *
     * @param id
     * @return
     */
    public Tb_flag find(int id) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select _id,flag from tb_flag where _id = ?", new String[] { String.valueOf(id) });// 根据编号查找便签信息，并存储到Cursor类中
        if (cursor.moveToNext()) {// 遍历查找到的便签信息

            // 将遍历到的便签信息存储到Tb_flag类中
            return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("flag")));
        }
        return null;// 如果没有信息，则返回null
    }

    /**
     * 刪除便签信息
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
            db = helper.getWritableDatabase();// 创建SQLiteDatabase对象
            // 执行删除便签信息操作
            db.execSQL("delete from tb_flag where _id in (" + sb + ")", (Object[]) ids);
        }
    }

    /**
     * 获取便签信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_flag> getScrollData(int start, int count) {
        List<Tb_flag> lisTb_flags = new ArrayList<Tb_flag>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有便签信息
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?,?", new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext()) {// 遍历所有的便签信息

            // 将遍历到的便签信息添加到集合中
            lisTb_flags.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("flag"))));
        }
        return lisTb_flags;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);// 获取便签信息的记录数
        if (cursor.moveToNext()) {// 判断Cursor中是否有数据

            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取便签最大编号
     *
     * @return
     */
    public int getMaxId() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);// 获取便签信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
}
