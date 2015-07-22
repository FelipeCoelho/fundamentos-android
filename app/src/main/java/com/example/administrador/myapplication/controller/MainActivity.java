package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Client> clientsList = getClients();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter clienteAdapter = new ClientListAdapter(MainActivity.this, clientsList);
        listViewClients.setAdapter(clienteAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_lista, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent goToMainActivity = new Intent(MainActivity.this, ClientPersistActivity.class);
            startActivity(goToMainActivity);
        }
        return super.onOptionsItemSelected(item);
    }


    private List<Client> getClients() {
        /*List<Client> clients = new ArrayList<Client>();

        Client c1 = new Client();
        Client c2 = new Client();

        c1.setName("Felipe");
        c1.setAge(34);

        c2.setName("Barbara");
        c2.setAge(13);

        clients.add(c1);
        clients.add(c2);

        return clients;*/
        return Client.getAll();
    }


}
