package com.example.bakeryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class orderadapter extends BaseAdapter {

    private Context context;
    private ArrayList<order> orderlist;
    private LayoutInflater layoutInflater;



    public orderadapter(Context applicationContext, ArrayList<order> orderlist) {

        context = applicationContext;
        this.orderlist = orderlist;
        layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return orderlist.size();
    }

    @Override
    public Object getItem(int position) {
        return orderlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.display, null);

            holder = new ViewHolder();

            holder.fullname = convertView.findViewById(R.id.fullname);
            holder.ordered = convertView.findViewById(R.id.ordered);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fullname.setText(orderlist.get(position).getFullname());
        holder.ordered.setText(orderlist.get(position).getOrdered());
        holder.price.setText(Integer.toString(orderlist.get(position).getPrice()));

        return convertView;
    }

    private static class ViewHolder{
        TextView fullname, ordered, price;
    }
}
