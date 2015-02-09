package com.hbrisson.applicationbase.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbrisson.applicationbase.R;
import com.hbrisson.applicationbase.entites.User;
import com.hbrisson.applicationbase.item.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by hbrisson on 30/01/2015.
 * Adapter for inflate list for navigation drawer.
 */
public class navDrawerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private User user;

    /**
     * Construtor.
     *
     * @param context
     * @param navDrawerItems
     */
    public navDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    public navDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems, User user) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.user = user;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (position == 0) {
                convertView = mInflater.inflate(R.layout.account_item, null);
                ImageView imgIcon = (ImageView) convertView.findViewById(R.id.photo_view);
                TextView txtName = (TextView) convertView.findViewById(R.id.name_text);
                TextView txtMail = (TextView) convertView.findViewById(R.id.mail_text);
                txtName.setText(user.getmName() + " " + user.getmSurname());
                txtMail.setText(user.getmMail());
            } else {
                convertView = mInflater.inflate(R.layout.drawer_list_item, null);
                ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
                TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
                imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
                txtTitle.setText(navDrawerItems.get(position).getTitle());
            }


        }
        return convertView;
    }
}
