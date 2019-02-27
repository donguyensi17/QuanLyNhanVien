package com.dns.nguyensi.quanlynhanvien.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.adapter.PhongBanAdapter;
import com.dns.nguyensi.quanlynhanvien.database.DatabaseHepper;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.util.ArrayList;

public class ChiTietPBActivity extends AppCompatActivity {

    public static DatabaseHepper database = null;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Button btnThem, btnXoa, btnSua;
    EditText txtMapb, txtTenpb;
    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_pb);
        Anhxa();
        ActionToolbar();
        CreateDatabase();
        Intent intent = getIntent();

        if (intent.hasExtra("pos")) {
            position = intent.getIntExtra("pos", -1);
        }
        PhongBan phongBan = NhanVienActivity.listPhongbaan.get(position);
        if (phongBan != null) {
            txtMapb.setText(phongBan.getMapb() );
            txtTenpb.setText(phongBan.getTenphongban());
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMapb.getText().toString().equals("") || txtTenpb.getText().toString().equals("")){
                    Toast.makeText(ChiTietPBActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    String txtmapb = txtMapb.getText().toString();
                    String txttenpb = txtTenpb.getText().toString();

                    PhongBan phongBan = new PhongBan(txtmapb, txttenpb);
                    database.addPhongBan(phongBan);
                    NhanVienActivity.listPhongbaan.add(phongBan);
                    finish();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapb = NhanVienActivity.listPhongbaan.get(position).getMapb();
                String tenpb = NhanVienActivity.listPhongbaan.get(position).getTenphongban();
                if (NhanVienActivity.database.deletePhongban(mapb) && NhanVienActivity.database.deletePhongban(tenpb)) {
                    NhanVienActivity.listPhongbaan.remove(position);
                    finish();
                    Toast.makeText(ChiTietPBActivity.this,"", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChiTietPBActivity.this, "", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

    }

    private void CreateDatabase() {
        database = new DatabaseHepper(this);
        NhanVienActivity.listPhongbaan = database.getAllPhongban();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chitiet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuthemnhanvien:
//                ControlDialog();
                break;
            case R.id.menunhanvien:
                Intent intent = new Intent(ChiTietPBActivity.this, NhanVienActivity.class);
                startActivity(intent);
                break;
            case R.id.menunphongban:
                Intent intent1 = new Intent(ChiTietPBActivity.this, PhongBanActivity.class);
                startActivity(intent1);
                break;
            case R.id.menubando:
                Intent intent2 = new Intent(ChiTietPBActivity.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case R.id.menuthoat:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarchitietphongban);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        btnThem = (Button) findViewById(R.id.btnThemchitietphongban);
        btnXoa = (Button) findViewById(R.id.btnXaochitietphongban);
        btnSua = (Button) findViewById(R.id.btnSuachitietphongban);
        txtMapb = (EditText) findViewById(R.id.edittextchitietmaphongban);
        txtTenpb = (EditText) findViewById(R.id.edittextchitiettenphongban);
//        adapter2 = new PhongBanAdapter(this,ChiTietPBActivity.listPhongbaan);
    }
}
