package cn.mmvtc.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import cn.mmvtc.shop.bean.ShopBean;

public class shoppingActivity extends AppCompatActivity implements View.OnClickListener
{
   ImageView gw;
    private ListView mListView;
    private int[] id={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
    private String[] titles={
            "水煮肉片","麻婆豆腐","桌子","苹果","蛋糕","猕猴桃","香蕉","樱桃","菠萝","草莓","线衣","围巾","猫","老虎","哈士奇","小黄鸭","帆布鞋"
    };
    private  String[] prices={
            "36元","28元","1800元","10元/kg","300元","10元/kg","2元/kg","20元/kg","14元/箱","29元/盒","350"+"元","280元","120元","10000元","50元","120元","60元"
    };
    private int[] icons={
            R.drawable.boiledmeat,R.drawable.mapoytofu,
            R.drawable.table,R.drawable.apple,R.drawable.
            cake,R.drawable.kiwifruit, R.drawable.banana,
            R.drawable.cherry,R.drawable.pineapple,R.drawable.strawberry,
            R.drawable.wireclothes,R.drawable.scarf,
            R.drawable.cat,R.drawable.tiger,R.drawable.siberiankusky,R.drawable.yellowduck,
            R.drawable.fbx};
    List <ShopBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingmall);
        mListView=(ListView)findViewById(R.id.lv);
        gw=(ImageView) findViewById(R.id.gwc_1);
        gw.setOnClickListener(this);
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
        CilckItem();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case  R.id.gwc_1:
                Intent int1=new Intent(this,ShopCar.class);
                startActivity(int1);
                break;
        }
    }

    class MyBaseAdapter extends BaseAdapter  {
        @Override
        public int getCount(){
            return titles.length;
        }
        @Override
        public Object getItem(int position){
            return titles[position];
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ShopAdapter holder ;
            if (convertView == null) {
                convertView = View.inflate(shoppingActivity.this, R.layout.list_item, null);
                holder = new ShopAdapter();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ShopAdapter) convertView.getTag();
            }
            holder.title.setText(titles[position]);
            holder.price.setText(prices[position]);
            holder.iv.setBackgroundResource(icons[position]);
            return convertView;
        }
    }
    protected void CilckItem(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(shoppingActivity.this,Content.class);
                intent.putExtra("name",titles[position]);
                intent.putExtra("price",prices[position]);
                intent.putExtra("picture",icons[position]);

                startActivity(intent);
            }
        });
    }
}
