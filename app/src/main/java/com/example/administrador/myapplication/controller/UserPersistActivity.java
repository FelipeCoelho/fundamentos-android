package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.FormHelper;

/**
 * Created by Administrador on 30/07/2015.
 */
public class UserPersistActivity extends AppCompatActivity {

    public static String USER_PARAM = "USER_PARAM";

    private User user;
    private EditText editTextLogin;
    private EditText editTextPassword;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_user);

        Bundle extra = getIntent().getExtras();
        bindForm();
        getParameters(extra);
    }

    private void bindForm() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void getParameters(Bundle extra) {
        user = new User();

        if (extra != null) {
            user = (User) getIntent().getExtras().getParcelable(USER_PARAM);
            if (user == null) {
                throw new IllegalArgumentException();
            }
            bindForm(user);
        }
    }

    private void bindForm(User user) {
        editTextLogin.setText(user.getName());
        editTextPassword.setText(user.getPassword());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_user_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSaveUser) {
            if (FormHelper.riquiredValidate(UserPersistActivity.this, editTextLogin, editTextPassword)) {
                User user = bindUser();
                user.save();
               // Toast.makeText(UserPersistActivity.this, User.getAll().toString(), Toast.LENGTH_LONG).show();
                Intent goToMainActivity = new Intent(UserPersistActivity.this, UserListActivity.class);
                startActivity(goToMainActivity);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private User bindUser() {
        if (user == null) {
            user = new User();
        }
        user.setName(editTextLogin.getText().toString());
        user.setPassword(editTextPassword.getText().toString());

        return user;
    }
}
