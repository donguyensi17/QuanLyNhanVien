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

    EditText edttaikhoan, edtmatkhau;
    Button btndangnhap, btndangky, btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        ControlButton();
    }

    private void ControlButton() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn muốn thoát chứ?");
                builder.setIcon(R.drawable.thoat);
                builder.setPositiveButton("Ừm nè", new DialogInterface.OnClickListener() {
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

//        btndangky.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,SigninActivity.class);
//                startActivity(intent);
//            }
//        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttaikhoan.getText().length() !=0 && edtmatkhau.getText().length() !=0){
                    if(edttaikhoan.getText().toString().equals("admin") && edtmatkhau.getText().toString().equals("123456")){
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

    private void anhxa() {
        edttaikhoan = (EditText) findViewById(R.id.edittextuser);
        edtmatkhau = (EditText) findViewById(R.id.edittextpassword);
        btndangnhap = (Button) findViewById(R.id.buttondangnhap);
        btndangky = (Button) findViewById(R.id.buttondangky);
        btnthoat = (Button) findViewById(R.id.buttonthoat);
    }
}
