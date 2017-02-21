package com.example.androidteam2.addinlistdynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndroidTeam2 on 2/13/2017.
 */

public class MyCustomAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<ListItem> arraylist;
    private List<ListItem> itemList;
    public static ArrayList<String> test = new ArrayList<>();
    private static String test2;

    MyCustomAdapter(Context context, int simple_list_item_1, List<ListItem> itemList){

        super(context ,0);
        this.itemList = itemList;
        mLayoutInflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<ListItem>();
        this.arraylist.addAll(itemList);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public ListItem getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();

            view = mLayoutInflater.inflate(R.layout.item, null);
            holder.itemName = (TextView) view.findViewById(R.id.edt_itemName);
            holder.itemCode = (TextView) view.findViewById(R.id.edt_itemCode);
            holder.itemqut = (TextView) view.findViewById(R.id.edt_itemQuty);
            holder.itemDis = (TextView) view.findViewById(R.id.edt_disc);
            holder.itemRate = (TextView) view.findViewById(R.id.edt_rate);

            view.setTag(holder);
        }
        else {

            holder = (ViewHolder)view.getTag();
        }
        holder.itemName.setText(itemList.get(position).getItemName());
        holder.itemCode.setText(itemList.get(position).getItemCode());
        holder.itemqut.setText(itemList.get(position).getIteQuty());
        holder.itemRate.setText(itemList.get(position).getItemRate());
        holder.itemDis.setText(itemList.get(position).getItemDisc());

        test.add(holder.itemName.getText().toString());
        test2 = holder.itemName.getText().toString();
        return view;

    }


    private static class ViewHolder {

        protected TextView itemName, itemCode,itemqut,itemDis,itemRate;

    }

}

