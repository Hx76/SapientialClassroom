package com.example.sapientialclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button register;
    private Button login;
    private EditText email;
    private EditText pwd;
    String Email;
    String Pwd;
    List<UserInformation> pwd_sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        List<UserInformation> books = LitePal.findAll(UserInformation.class);
        for (UserInformation book: books) {
            Log.d(TAG,"book name is " + book.getEmail());
            Log.d(TAG,"author is " + book.getPassWord());
        }

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email_login);
        pwd = findViewById(R.id.password_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = email.getText().toString();
                Pwd = pwd.getText().toString();

                if (Email == null || Email.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else {
                    if (Pwd == null || Pwd.isEmpty()) {
                        Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        pwd_sql = LitePal.select("PassWord").where("Email = ?",Email).find(UserInformation.class);
                      //  Log.d(TAG, "123"+pwd_sql.get(0));
                        String email1 = null;
                        for (UserInformation userInformation : pwd_sql) {
                            email1 = userInformation.getPassWord();
                            Log.d(TAG, "456"+email1);
                            Log.d(TAG, "789"+Pwd);
                        }
                        if (Pwd.equals(email1)) {
                            Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });


    }
}
