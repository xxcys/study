package cn.mmvtc.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.mmvtc.shop.R;
import cn.mmvtc.shop.bean.ShopBean;


public class ContentAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<ShopBean> list;
    public ContentAdapter(Context context, List <ShopBean> list){
        this.layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount(){
        return list==null ? 0 : list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position , View convertView , ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shop_car_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopBean noteInfo = (ShopBean) getItem(position);
        viewHolder.tvName.setText(noteInfo.getTitle());
        viewHolder.tvPrice.setText(noteInfo.getPrice());
        return convertView;
    }
    class ViewHolder{
        TextView tvName;
        TextView tvPrice;
        public ViewHolder(View view){
            tvName=(TextView)view.findViewById(R.id.item_name);
            tvPrice=(TextView)view.findViewById(R.id.item_price);
        }
    }
}

