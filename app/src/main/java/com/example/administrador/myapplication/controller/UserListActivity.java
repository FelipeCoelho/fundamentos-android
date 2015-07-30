package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;

import java.util.List;

/**
 * Created by Administrador on 30/07/2015.
 */
public class UserListActivity extends AppCompatActivity {
    private ListView listViewUsers;
    private List<User> usersList;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        bindUserList();
    }

    private void bindUserList() {
        List<User> usersList = getUsersList();

        listViewUsers = (ListView) findViewById(R.id.listViewUser);
        final UserListAdapter userAdapter = new UserListAdapter(UserListActivity.this, usersList);
        listViewUsers.setAdapter(userAdapter);

        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                user = (User) parent.getItemAtPosition(position);
                return false;
            }
        });
    }

    private List<User> getUsersList() {
        return User.getAll();
    }
}
