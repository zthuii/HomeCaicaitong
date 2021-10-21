package com.example.homecaicaitong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gvInfo;// 创建GridView对象
    // 定义字符串数组，存储系统功能
    private String[] titles = new String[] { "新增支出", "新增收入", "我的支出", "我的收入", "数据管理", "系统设置", "收支便签", "退出" };
    // 定义int数组，存储功能对应的图标
    private int[] images = new int[] { R.drawable.addoutaccount, R.drawable.addinaccount, R.drawable.outaccountinfo, R.drawable.inaccountinfo,
            R.drawable.showinfo, R.drawable.sysset, R.drawable.accountflag, R.drawable.exit };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gvInfo = (GridView) findViewById(R.id.gvInfo);// 获取布局文件中的gvInfo组件
        PictureAdapter adapter = new PictureAdapter(titles, images, this);// 创建pictureAdapter对象
        gvInfo.setAdapter(adapter);// 为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {// 为GridView设置项单击事件
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = null;// 创建Intent对象
                switch (arg2) {
                    case 0:
                        intent = new Intent(MainActivity.this, AddOutaccount.class);// 使用AddOutaccount窗口初始化Intent
                        startActivity(intent);// 打开AddOutaccount
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, AddInaccount.class);// 使用AddInaccount窗口初始化Intent
                        startActivity(intent);// 打开AddInaccount
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Outaccountinfo.class);// 使用Outaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Outaccountinfo
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, Inaccountinfo.class);// 使用Inaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Inaccountinfo
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Showinfo.class);// 使用Showinfo窗口初始化Intent
                        startActivity(intent);// 打开Showinfo
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, Sysset.class);// 使用Sysset窗口初始化Intent
                        startActivity(intent);// 打开Sysset
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, Accountflag.class);// 使用Accountflag窗口初始化Intent
                        startActivity(intent);// 打开Accountflag
                        break;
                    case 7:
                        finish();// 关闭当前Activity
                }
            }
        });
    }
}
class PictureAdapter extends BaseAdapter {// 创建基于BaseAdapter的子类

    private LayoutInflater inflater;// 创建LayoutInflater对象
    private List<Picture> pictures;// 创建List泛型集合

    // 为类创建构造函数
    public PictureAdapter(String[] titles, int[] images, Context context) {
        super();
        pictures = new ArrayList<Picture>();// 初始化泛型集合对象
        inflater = LayoutInflater.from(context);// 初始化LayoutInflater对象
        for (int i = 0; i < images.length; i++)// 遍历图像数组
        {
            Picture picture = new Picture(titles[i], images[i]);// 使用标题和图像生成Picture对象
            pictures.add(picture);// 将Picture对象添加到泛型集合中
        }
    }

    @Override
    public int getCount() {// 获取泛型集合的长度
        if (null != pictures) {// 如果泛型集合不为空
            return pictures.size();// 返回泛型长度
        } else {
            return 0;// 返回0
        }
    }

    @Override
    public Object getItem(int arg0) {
        return pictures.get(arg0);// 获取泛型集合指定索引处的项
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;// 返回泛型集合的索引
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;// 创建ViewHolder对象
        if (arg1 == null) {// 判断图像标识是否为空

            arg1 = inflater.inflate(R.layout.gvitem, null);// 设置图像标识
            viewHolder = new ViewHolder();// 初始化ViewHolder对象
            viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// 设置图像标题
            viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// 设置图像的二进制值
            arg1.setTag(viewHolder);// 设置提示
        } else {
            viewHolder = (ViewHolder) arg1.getTag();// 设置提示
        }
        viewHolder.title.setText(pictures.get(arg0).getTitle());// 设置图像标题
        viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// 设置图像的二进制值
        return arg1;// 返回图像标识
    }
}

class ViewHolder {// 创建ViewHolder类

    public TextView title;// 创建TextView对象
    public ImageView image;// 创建ImageView对象
}

class Picture {// 创建Picture类

    private String title;// 定义字符串，表示图像标题
    private int imageId;// 定义int变量，表示图像的二进制值

    public Picture() {// 默认构造函数

        super();
    }

    public Picture(String title, int imageId) {// 定义有参构造函数

        super();
        this.title = title;// 为图像标题赋值
        this.imageId = imageId;// 为图像的二进制值赋值
    }

    public String getTitle() {// 定义图像标题的可读属性
        return title;
    }

    public void setTitle(String title) {// 定义图像标题的可写属性
        this.title = title;
    }

    public int getImageId() {// 定义图像二进制值的可读属性
        return imageId;
    }

    public void setimageId(int imageId) {// 定义图像二进制值的可写属性
        this.imageId = imageId;
    }
}