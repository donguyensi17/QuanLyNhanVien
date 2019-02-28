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
        AnhXa();
        ControlButton();
    }

    private void ControlButton() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn muốn thoát chứ?");
                builder.setIcon(R.drawable.thoat);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNeutralButton("Hông nè", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_username.getText().length() !=0 && edt_password.getText().length() !=0){
                    if(edt_username.getText().toString().equals("admin") && edt_password.getText().toString().equals("123456")){
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

    private void AnhXa() {
        edt_username = (EditText) findViewById(R.id.edittextuser);
        edt_password = (EditText) findViewById(R.id.edittextpassword);
        btn_login = (Button) findViewById(R.id.buttondangnhap);
        btn_signin = (Button) findViewById(R.id.buttondangky);
        btn_exit = (Button) findViewById(R.id.buttonthoat);
    }
}
