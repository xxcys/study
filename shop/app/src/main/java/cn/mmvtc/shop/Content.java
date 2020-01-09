package cn.mmvtc.shop;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.mmvtc.shop.database.SQLiteHelper;


public class Content extends AppCompatActivity implements View.OnClickListener{
    TextView content_name;
    TextView content_price;
    ImageView content_picture;
    ImageView add;
    SQLiteHelper mSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.mmvtc.shop.R.layout.activity_content);
        content_name=(TextView)findViewById(R.id.content_name);
        content_price=(TextView)findViewById(R.id.content_price);
        content_picture=(ImageView)findViewById(R.id.content_picture);

        add=(ImageView)findViewById(R.id.add);
        add.setOnClickListener(this);
        nameData ();
        sqlData();
    }
    public void nameData(){
        Intent intent=getIntent();

        content_name.setText(intent.getStringExtra("name"));
        content_price.setText(intent.getStringExtra("price"));
        int img=getIntent().getIntExtra("picture",-1);
        content_picture.setBackgroundResource(img);
    }
    protected void sqlData(){
        mSQL=new SQLiteHelper(this);//数据库
    }
    public void onClick(View view){
        switch(view.getId()){

            case R.id.add:
                String name=content_name.getText().toString().trim();
                String price=content_price.getText().toString().trim();
            if (mSQL.insertDate(name,price)) {
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                setResult(2);
                finish();
                Intent intent=new Intent(Content.this,ShopCar.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }
                break;
        }
    }
}
