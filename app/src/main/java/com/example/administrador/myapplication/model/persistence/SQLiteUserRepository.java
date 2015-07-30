package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLiteUserRepository implements UserRepository {

    private static SQLiteUserRepository singletonInstace;

    private SQLiteUserRepository() {
        super();
    }

    public static SQLiteUserRepository getInstance() {
        if (singletonInstace == null) {
            singletonInstace = new SQLiteUserRepository();
        }
        return singletonInstace;
    }

    @Override
    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);
        } else {
            values.put(UserContract.ID, user.getId());
            String where = UserContract.ID + " = ?";
            String[] args = {user.getId().toString()};
            db.update(UserContract.TABLE, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, null, null, null, null, UserContract.NAME);

        users = UserContract.bindList(cursor);

        db.close();
        helper.close();
        return users;
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = UserContract.ID + " = ?";
        String[] args = {user.getId().toString()};
        db.delete(UserContract.TABLE, where, args);
        db.close();
        helper.close();
    }

    @Override
    public User getUser(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = UserContract.NAME + " = ? AND " + UserContract.PASSWORD + " = ?";
        String[] select = {user.getName(), user.getPassword()};

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, where, select, null, null, UserContract.NAME);

        User userRetorn = UserContract.bind(cursor);
        db.close();
        helper.close();
        return userRetorn;
    }
}
