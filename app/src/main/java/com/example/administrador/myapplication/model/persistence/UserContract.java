package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 30/07/2015.
 */
public class UserContract {

    public static final String TABLE = "USER";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String[] COLUMNS = {ID, NAME, PASSWORD};


    public static String getCreateTable() {
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" );");

        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.ID, user.getId());
        values.put(UserContract.NAME, user.getName());
        values.put(UserContract.PASSWORD, user.getPassword());
        return values;
    }

    public static User bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(UserContract.ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(UserContract.NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> bindList(Cursor cursor) {
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(bind(cursor));
        }
        return users;
    }
}
