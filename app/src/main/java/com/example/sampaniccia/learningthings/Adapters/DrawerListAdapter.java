package com.example.sampaniccia.learningthings.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampaniccia.learningthings.Objects.MenuListItem;
import com.example.sampaniccia.learningthings.R;

import java.util.ArrayList;

/**
 * Adapter for the pullout menu.
 */

public class DrawerListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MenuListItem> menuListItems;

    public DrawerListAdapter(Context c, ArrayList<MenuListItem> items) {
        context = c;
        menuListItems = items;
    }

    @Override
    public int getCount(){
        return menuListItems.size();
    }

    @Override
    public Object getItem(int position){
        return menuListItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){
        View view;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //^ LayoutInflater.from(context) ??? ^
            view = inflater.inflate(R.layout.menu_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.menu_item_title);
        ImageView iconView = (ImageView) view.findViewById(R.id.menu_item_icon);

        titleView.setText(menuListItems.get(position).getTitle());
        iconView.setImageResource(menuListItems.get(position).getIcon());

        return view;
    }

}
