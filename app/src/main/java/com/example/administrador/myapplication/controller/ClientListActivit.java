package com.example.administrador.myapplication.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;

import java.util.List;


public class ClientListActivit extends AppCompatActivity {
    private ListView listViewClients;
    private List<Client> clientsList;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindClientList();
    }

    private void bindClientList() {
        List<Client> clientsList = getClients();

        listViewClients = (ListView) findViewById(R.id.listViewClients);
        final ClientListAdapter clienteAdapter = new ClientListAdapter(ClientListActivit.this, clientsList);
        listViewClients.setAdapter(clienteAdapter);

        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });
        registerForContextMenu(listViewClients);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_lista, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent goToMainActivity = new Intent(ClientListActivit.this, ClientPersistActivity.class);
            startActivity(goToMainActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Client> getClients() {
        return Client.getAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();

    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuEdit) {
            Intent intent = new Intent(ClientListActivit.this, ClientPersistActivity.class);
            intent.putExtra(ClientPersistActivity.CLIENT_PARAM, (Parcelable) client);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menuDelete) {
            new AlertDialog.Builder(ClientListActivit.this).setMessage(getString(R.string.confirm_progress))
                    .setTitle(getString(R.string.confirm)).setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    client.delete();
                    refreshClientList();
                    Toast.makeText(ClientListActivit.this, getString(R.string.sucess), Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton(getString(R.string.not), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();

        }
        return super.onContextItemSelected(item);
    }
}
