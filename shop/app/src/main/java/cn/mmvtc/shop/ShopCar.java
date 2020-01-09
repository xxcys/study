package cn.mmvtc.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import cn.mmvtc.shop.adapter.ContentAdapter;
import cn.mmvtc.shop.bean.ShopBean;
import cn.mmvtc.shop.database.SQLiteHelper;


public class ShopCar extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    ImageView imgcar;
    List <ShopBean> list;
    SQLiteHelper mSQl;
    ContentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        imgcar = findViewById(R.id.car_img);
        imgcar.setOnClickListener(this);
        listView=(ListView)findViewById(R.id.lv);
        iniData();
    }
    protected void iniData(){
        mSQl=new SQLiteHelper(this);
        showQueryData();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(ShopCar.this)
                        .setMessage("是否删除此商品?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ShopBean notepadBean=list.get(position);
                                if (mSQl.deleteData(notepadBean.getId())){
                                    list.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(ShopCar.this,"删除成功",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog=builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void showQueryData(){
        if (list!=null){
            list.clear();
        }
        //从数据库中查询数据(保存的记录)
        list=mSQl.query();

        adapter=new ContentAdapter(this,list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1 && resultCode==2){
            showQueryData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.car_img:
                 Intent IT=new Intent(this,shoppingActivity.class);
                 startActivity(IT);
        }
    }
}

