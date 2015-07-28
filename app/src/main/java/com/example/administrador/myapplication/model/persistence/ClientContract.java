package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 23/07/2015.
 */
public class ClientContract {

    public static final String TABLE = "CLIENTE";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String FONE = "FONE";
    public static final String ADDRESS = "ADDRESS";
    public static final String AGE = "AGE";
    //public static final String[] COLUMNS = {ID, NAME, FONE, ADDRESS, AGE};
    public static final String[] COLUMNS = {ID, NAME, FONE, AGE};


    public static String getCreateTable() {
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " INTEGER, ");
        sql.append(FONE + " INTEGER ");
        //sql.append(ADDRESS + " TEXT ");
        sql.append(" );");

        return sql.toString();
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.FONE, client.getFone());
        //values.put(ClientContract.ADDRESS, client.getAddDress());
        return values;
    }

    public static Client bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setFone(cursor.getInt(cursor.getColumnIndex(ClientContract.FONE)));
            //client.setAddDress(cursor.getString(cursor.getColumnIndex(ClientContract.ADDRESS)));
            return client;
        }
        return null;
    }

    public static List<Client> bindList(Cursor cursor) {
        List<Client> clients = new ArrayList<>();
        while (cursor.moveToNext()) {
            clients.add(bind(cursor));
        }
        return clients;
    }

}
