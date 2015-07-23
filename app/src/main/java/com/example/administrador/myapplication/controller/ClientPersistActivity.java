package com.example.administrador.myapplication.controller;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.util.FormHelper;

public class ClientPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private Client client;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextFone;
    private EditText editTextAddDress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        Bundle extra = getIntent().getExtras();
        bindForm();
        client = new Client();

        if (extra != null) {
            client = (Client) getIntent().getExtras().getParcelable(CLIENT_PARAM);
            if (client == null) {
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_cliente_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            if (FormHelper.riquiredValidate(ClientPersistActivity.this, editTextName, editTextAge, editTextFone, editTextAddDress)) {
                Client client = bindClient();
                client.save();
                Toast.makeText(ClientPersistActivity.this, Client.getAll().toString(), Toast.LENGTH_LONG).show();
                ClientPersistActivity.this.finish();
            }
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

    private void bindForm() {
        editTextName = (EditText) findViewById(R.id.textViewName);
        editTextAge = (EditText) findViewById(R.id.textViewAge);
        editTextFone = (EditText) findViewById(R.id.textViewFone);
        editTextAddDress = (EditText) findViewById(R.id.textViewAddDress);
    }

    private void bindForm(Client client) {
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextFone.setText(client.getFone().toString());
        editTextAddDress.setText(client.getAddDress());
    }

}