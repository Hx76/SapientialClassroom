package com.example.sapientialclassroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;

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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private Button register_right_now;
    private EditText email;
    private EditText pwd;
    private EditText pwd_affirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_right_now = findViewById(R.id.Register_right_now);
        email = findViewById(R.id.email_register);
        pwd = findViewById(R.id.password_register);
        pwd_affirm = findViewById(R.id.password_affirm);



        register_right_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString();
                String Pwd = pwd.getText().toString();
                String Pwd_affirm = pwd_affirm.getText().toString();
               // Connector.getDatabase();
                Log.d(TAG, "onClick: 数据库创建成功");
                if (Email == null || Email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"注册邮箱不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if (Pwd == null || Pwd.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Pwd_affirm == null || Pwd_affirm.isEmpty()) {
                            Toast.makeText(RegisterActivity.this, "请再次确认密码", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!Pwd.equals(Pwd_affirm)) {
                                Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                            }else {
                                List<UserInformation> email_sql = LitePal.select("Email").where("Email = ?",Email).find(UserInformation.class);
                                Log.d(TAG, "123"+email_sql.toString());
                                String email1 = null;
                                for (UserInformation userInformation :email_sql) {
                                    email1 = userInformation.getEmail();
                                }
                                    if (email1 != null) {
                                        Toast.makeText(RegisterActivity.this,"该邮箱已被注册",Toast.LENGTH_SHORT).show();
                                    }else {
                                        UserInformation userInformation = new UserInformation();
                                        userInformation.setEmail(Email);
                                        userInformation.setPassWord(Pwd);
                                        userInformation.save();
                                        Log.d(TAG, "onClick: 注册成功");
                                        Intent intent = new Intent(RegisterActivity.this,SuccessfulRegisterActivity.class);
                                        startActivity(intent);

                                    }

                            }
                        }
                    }
                }
            }
        });
    }
}
