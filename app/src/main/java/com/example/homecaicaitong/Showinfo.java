package com.example.homecaicaitong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.homecaicaitong.dao.FlagDAO;
import com.example.homecaicaitong.dao.InaccountDAO;
import com.example.homecaicaitong.dao.OutaccountDAO;
import com.example.homecaicaitong.model.Tb_flag;
import com.example.homecaicaitong.model.Tb_inaccount;
import com.example.homecaicaitong.model.Tb_outaccount;

import java.util.List;

public class Showinfo extends AppCompatActivity {
    public static final String FLAG = "id";// 定义一个常量，用来作为请求码
    private Button btnoutinfo, btnininfo, btnflaginfo;// 创建3个Button对象
    private ListView lvinfo;// 创建ListView对象
    private String strType = "";// 创建字符串，记录管理类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);// 设置布局文件

        lvinfo = (ListView) findViewById(R.id.lvinfo);// 获取布局文件中的ListView组件
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// 获取布局文件中的支出信息按钮
        btnininfo = (Button) findViewById(R.id.btnininfo);// 获取布局文件中的收入信息按钮
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);// 获取布局文件中的便签信息按钮

        showInfo(R.id.btnoutinfo);// 默认显示支出信息

        btnoutinfo.setOnClickListener(new View.OnClickListener() {// 为支出信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnoutinfo);// 显示支出信息
            }
        });

        btnininfo.setOnClickListener(new View.OnClickListener() {// 为收入信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnininfo);// 显示收入信息
            }
        });
        btnflaginfo.setOnClickListener(new View.OnClickListener() {// 为便签信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnflaginfo);// 显示便签信息
            }
        });

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {// 为ListView添加项单击事件

            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// 记录单击的项信息
                String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从项信息中截取编号
                Intent intent = null;// 创建Intent对象
                if (strType == "btnoutinfo" | strType == "btnininfo") {// 判断如果是支出或者收入信息
                    intent = new Intent(Showinfo.this, InfoManage.class);// 使用InfoManage窗口初始化Intent对象
                    intent.putExtra(FLAG, new String[] { strid, strType });// 设置要传递的数据
                } else if (strType == "btnflaginfo") {// 判断如果是便签信息
                    intent = new Intent(Showinfo.this, FlagManage.class);// 使用FlagManage窗口初始化Intent对象
                    intent.putExtra(FLAG, strid);// 设置要传递的数据
                }
                startActivity(intent);// 执行Intent，打开相应的Activity
            }
        });
    }

    private void showInfo(int intType) {// 用来根据传入的管理类型，显示相应的信息
        String[] strInfos = null;// 定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
        switch (intType) {// 以intType为条件进行判断
            case R.id.btnoutinfo:// 如果是btnoutinfo按钮
                strType = "btnoutinfo";// 为strType变量赋值
                OutaccountDAO outaccountinfo = new OutaccountDAO(Showinfo.this);// 创建OutaccountDAO对象
                // 获取所有支出信息，并存储到List泛型集合中
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];// 设置字符串数组的长度
                int i = 0;// 定义一个开始标识
                for (Tb_outaccount tb_outaccount : listoutinfos) {// 遍历List泛型集合
                    // 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType() + " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
                            + tb_outaccount.getTime();
                    i++;// 标识加1
                }
                break;
            case R.id.btnininfo:// 如果是btnininfo按钮
                strType = "btnininfo";// 为strType变量赋值
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this);// 创建InaccountDAO对象
                // 获取所有收入信息，并存储到List泛型集合中
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];// 设置字符串数组的长度
                int m = 0;// 定义一个开始标识
                for (Tb_inaccount tb_inaccount : listinfos) {// 遍历List泛型集合
                    // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[m] = tb_inaccount.getid() + "|" + tb_inaccount.getType() + " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
                            + tb_inaccount.getTime();
                    m++;// 标识加1
                }
                break;
            case R.id.btnflaginfo:// 如果是btnflaginfo按钮
                strType = "btnflaginfo";// 为strType变量赋值
                FlagDAO flaginfo = new FlagDAO(Showinfo.this);// 创建FlagDAO对象
                // 获取所有便签信息，并存储到List泛型集合中
                List<Tb_flag> listFlags = flaginfo.getScrollData(0, (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];// 设置字符串数组的长度
                int n = 0;// 定义一个开始标识
                for (Tb_flag tb_flag : listFlags) {// 遍历List泛型集合
                    // 将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)// 判断便签信息的长度是否大于15
                        strInfos[n] = strInfos[n].substring(0, 15) + "……";// 将位置大于15之后的字符串用……代替
                    n++;// 标识加1
                }
                break;
        }
        // 使用字符串数组初始化ArrayAdapter对象
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
    }

    @Override
    protected void onRestart() {
        super.onRestart();// 实现基类中的方法
        showInfo(R.id.btnoutinfo);// 显示支出信息
    }
}