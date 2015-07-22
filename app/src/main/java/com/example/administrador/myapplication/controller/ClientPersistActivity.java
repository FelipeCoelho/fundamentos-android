package com.example.administrador.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.persistence.MemoryClientRepoditory;

public class ClientPersistActivity extends AppCompatActivity {

    private Client client;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextFone;
    private EditText editTextAddDress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        client = new Client();

        editTextName = (EditText) findViewById(R.id.textViewName);
        editTextAge = (EditText) findViewById(R.id.textViewAge);
        editTextFone = (EditText) findViewById(R.id.textViewFone);
        editTextAddDress = (EditText) findViewById(R.id.textViewAddDress);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_cliente_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            Client client = bindClient();
            client.save();

            Toast.makeText(ClientPersistActivity.this,Client.getAll().toString(),Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private Client bindClient() {
        client = new Client();
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setFone(Integer.valueOf(editTextFone.getText().toString()));
        client.setAddDress(editTextAddDress.getText().toString());
        return client;
    }


}