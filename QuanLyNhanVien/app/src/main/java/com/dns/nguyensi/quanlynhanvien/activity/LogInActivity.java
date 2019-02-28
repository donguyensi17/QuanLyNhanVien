package com.dns.nguyensi.quanlynhanvien.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dns.nguyensi.quanlynhanvien.R;

public class LogInActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogIn;
    private Button btnSignIn;
    private Button btnExit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        controlButton();
    }

    private void controlButton() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Exit?");
                builder.setIcon(R.drawable.thoat);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                private static final int USER_NAME = "admin";
                private static final int PASS_WORD = 123456;
                if(edtUserName.getText().length() !=0 && edtPassWord.getText().length() !=0){
                    if(edtUserName.getText().toString().equals(USER_NAME) && edtPassWord.getText().toString().equals(PASS_WORD)){
                        Toast.makeText(LogInActivity.this,"Bạn đã đăng nhập thành công!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LogInActivity.this,NhanVienActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LogInActivity.this,"Bạn đã đăng nhập thất bại!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LogInActivity.this,"Mời bạn điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        edtUserName = (EditText) findViewById(R.id.edittextuser);
        edtPassWord = (EditText) findViewById(R.id.edittextpassword);
        btnLogIn = (Button) findViewById(R.id.buttondangnhap);
        btnSignIn = (Button) findViewById(R.id.buttondangky);
        btnExit = (Button) findViewById(R.id.buttonthoat);
    }
}
