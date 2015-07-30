package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.User;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {


    private EditText editTextLogin;
    private EditText editTextSenha;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = new User();
        bindForm();
        bindLoginButton();
    }

    private void bindLoginButton() {
        Button btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setName(editTextLogin.getText().toString());
                user.setPassword(editTextSenha.getText().toString());
                if (User.getAll().size() > 0) {
                    User userValida = User.getUser(user);
                    if (userValida != null) {
                        Intent
                                goToMainActivity = new Intent(LoginActivity.this, ClientListActivit.class);
                        startActivity(goToMainActivity);
                    }
                } else {
                    Intent goToMainActivity = new Intent(LoginActivity.this, UserPersistActivity.class);
                    startActivity(goToMainActivity);
                }
            }
        });
    }

    private void bindForm() {
        editTextLogin = (EditText) findViewById(R.id.editTextUserName);
        editTextSenha = (EditText) findViewById(R.id.editTextPassword);

    }
}
