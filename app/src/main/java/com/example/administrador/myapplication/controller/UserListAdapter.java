package com.example.administrador.myapplication.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;

import java.util.List;


public class UserListAdapter extends BaseAdapter {

    private List<User> userList;
    private Activity context;


    public UserListAdapter(Activity context, List<User> userList) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.user_list_item, parent, false);

        User user = getItem(position);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewUserName);
        textViewName.setText(user.getName());

        return view;
    }
}
