package com.example.administrador.myapplication.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.ClientAddres;
import com.example.administrador.myapplication.model.services.CepService;
import com.example.administrador.myapplication.util.FormHelper;

public class ClientPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private Client client;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextFone;
    private EditText editTextCep;

    private EditText editTextTipoDeLogradouro;
    private EditText editTextLogradouro;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextEstado;

    private ProgressDialog progressDialog;

    private Button buttonFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        Bundle extra = getIntent().getExtras();
        bindForm();
        getParameters(extra);
    }

    private void getParameters(Bundle extra) {
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
            if (FormHelper.riquiredValidate(ClientPersistActivity.this, editTextName, editTextAge, editTextFone)) {
                Client client = bindClient();
                client.save();
                Toast.makeText(ClientPersistActivity.this, Client.getAll().toString(), Toast.LENGTH_LONG).show();
                ClientPersistActivity.this.finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private Client bindClient() {
        if (client == null) {
            client = new Client();
        }
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setFone(Integer.valueOf(editTextFone.getText().toString()));
        //client.setAddDress(editTextAddDress.getText().toString());
        return client;
    }

    private void bindForm() {
        editTextName = (EditText) findViewById(R.id.textViewName);
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });

        editTextAge = (EditText) findViewById(R.id.textViewAge);
        editTextFone = (EditText) findViewById(R.id.textViewFone);

        editTextCep = (EditText) findViewById(R.id.textViewCep);
        editTextCep.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextCep.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextCep.getRight() - editTextCep.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        new GetAddressByCep().execute(editTextCep.getText().toString());
                    }
                }
                return false;
            }
        });

        editTextTipoDeLogradouro = (EditText) findViewById(R.id.TextViewTipoDeLogradouro);
        editTextLogradouro = (EditText) findViewById(R.id.TextViewLogradouro);
        editTextBairro = (EditText) findViewById(R.id.TextViewBairro);
        editTextCidade = (EditText) findViewById(R.id.TextViewCidade);
        editTextEstado = (EditText) findViewById(R.id.TextViewEstado);
        bindButtonFindCep();
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextFone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void bindButtonFindCep() {
        buttonFind = (Button) findViewById(R.id.buttonFind);
        buttonFind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new GetAddressByCep().execute(editTextCep.getText().toString());
            }
        });
    }

    private void bindForm(Client client) {
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextFone.setText(client.getFone().toString());

        editTextTipoDeLogradouro.setText(client.getAddDress().getTipoDeLogradouro());
        editTextLogradouro.setText(client.getAddDress().getLogradouro());
        editTextBairro.setText(client.getAddDress().getBairro());
        editTextCidade.setText(client.getAddDress().getCidade());
        editTextEstado.setText(client.getAddDress().getEstado());

        //editTextAddDress.setText(client.getAddDress());
    }

    private class GetAddressByCep extends AsyncTask<String, Void, ClientAddres> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ClientPersistActivity.this);
            progressDialog.setMessage(getString(R.string.aguardando));
            progressDialog.show();
        }

        @Override
        protected ClientAddres doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddres adress) {
            super.onPostExecute(adress);
            client.setAddDress(adress);
            editTextTipoDeLogradouro.setText(client.getAddDress().getTipoDeLogradouro());
            editTextLogradouro.setText(client.getAddDress().getLogradouro());
            editTextBairro.setText(client.getAddDress().getBairro());
            editTextCidade.setText(client.getAddDress().getCidade());
            editTextEstado.setText(client.getAddDress().getEstado());
            progressDialog.dismiss();
        }
    }

}