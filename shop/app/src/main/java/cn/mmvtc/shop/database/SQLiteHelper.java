package cn.mmvtc.shop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.shop.bean.ShopBean;
import cn.mmvtc.shop.utils.DBUtils;


public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    //创建数据库
    public SQLiteHelper(Context context){
        super(context, DBUtils.DATABASE_NAME,null,DBUtils.DATABASE_VERION);
        sqLiteDatabase = this.getWritableDatabase();
    }
    //创建表
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+DBUtils.DATABASE_TABLE+"("+DBUtils.Shop_ID+" integer primary key autoincrement,"+ DBUtils.Shop_name + " int,"+ DBUtils.Shop_price +" int)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
    //添加数据
    public boolean insertDate(String ShopName,String ShopPrice){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.Shop_name,ShopName);
        contentValues.put(DBUtils.Shop_price,ShopPrice);
        return
              sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,contentValues)>0;//添加到数据库的插入语句
    }
    //删除数据
    public boolean deleteData(String id){
        String sql=DBUtils.Shop_ID+"=?";
        String [] contentValuesArray=new String[]{String.valueOf(id)};
        return
               sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,sql,contentValuesArray)>0;//删除选定的数据
    }
    //查询数据
    //调用ShopBean里的方法来使数据以item的方式显示，即是列表展示，和实验android12一样
    public List <ShopBean> query(){
        List<ShopBean> list=new ArrayList <ShopBean>();
        Cursor cursor=sqLiteDatabase.query(DBUtils.DATABASE_TABLE,null,null,null,null,null,DBUtils.Shop_ID+" desc");
        if (cursor!=null){
            while (cursor.moveToNext()){
                ShopBean noteInfo=new ShopBean();
                String id=String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.Shop_ID)));
                String name=cursor.getString(cursor.getColumnIndex(DBUtils.Shop_name));
                String price=cursor.getString(cursor.getColumnIndex(DBUtils.Shop_price));
                noteInfo.setId(id);
                noteInfo.setTitle(name);
                noteInfo.setPrice(price);
                list.add(noteInfo);
            }
            cursor.close();
        }
        return list;
    }
}
