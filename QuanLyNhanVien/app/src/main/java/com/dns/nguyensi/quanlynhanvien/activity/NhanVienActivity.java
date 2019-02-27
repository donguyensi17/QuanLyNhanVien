package com.dns.nguyensi.quanlynhanvien.activity;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.adapter.NhanVienAdapter;
import com.dns.nguyensi.quanlynhanvien.database.DatabaseHepper;
import com.dns.nguyensi.quanlynhanvien.model.NhanVien;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.util.ArrayList;

public class NhanVienActivity extends AppCompatActivity {

    private static final int FORDER_IMG = 0;
    public static final int REQUESTCODE_CAMERA = 1;
    public static ArrayList<NhanVien> listNhanvien = new ArrayList<>();
    public static ArrayList<PhongBan> listPhongbaan = new ArrayList<>();
    public Dialog dialog = null;
    public static DatabaseHepper database = null;
    ArrayAdapter<PhongBan> adapterPhongban = null;

    ListView lstnhanvien;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    public static NhanVienAdapter adapter;
//    public static PhongBanAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        Anhxa();
        ActionToolbar();
        initDatabase();
        lstnhanvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NhanVienActivity.this, ChiTietNVActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intentthem = new Intent(NhanVienActivity.this, ChiTietNVActivity.class);
                startActivity(intentthem);
                break;
            case R.id.menunhanvien:
                Intent intent = new Intent(NhanVienActivity.this, NhanVienActivity.class);
                startActivity(intent);
                break;
            case R.id.menunphongban:
                Intent intent1 = new Intent(NhanVienActivity.this, PhongBanActivity.class);
                startActivity(intent1);
                break;
            case R.id.menubando:
                Intent intent2 = new Intent(NhanVienActivity.this, MapsActivity.class);
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

    private void initDatabase() {
        database = new DatabaseHepper(this);
        listNhanvien = database.getAllNhanVien();
        listPhongbaan = database.getAllPhongban();
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        lstnhanvien = (ListView) findViewById(R.id.listviewnhanvien);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        adapter = new NhanVienAdapter(this, listNhanvien);
        adapter.notifyDataSetChanged();
    }
}
