package com.example.juber.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juber.myapplication.form.LoginDataBaseAdapter;

public class Login extends AppCompatActivity {

    private EditText editTextUserName,editTextPassword;
    private Button btnSignUp,btnSignIn;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        editTextUserName = findViewById(R.id.login_userName_id);
        editTextPassword = findViewById(R.id.login_Password_id);


        btnSignIn = findViewById(R.id.login_Button_id);
        btnSignUp = findViewById(R.id.login_reggistration_id);


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String storedPassword = loginDataBaseAdapter
                        .getSinlgeEntry(userName);
                if (password.equals(storedPassword)) {
                    Toast.makeText(Login.this,
                            "Congrats: Login Successfull", Toast.LENGTH_LONG)
                            .show();

                    Intent main = new Intent(Login.this, MainActivity.class);
                    startActivity(main);
                    finish();

                } else {
                    Toast.makeText(Login.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignUP = new Intent(Login.this,
                        Signup.class);
                startActivity(intentSignUP);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

}
