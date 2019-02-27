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
import android.widget.ListView;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.adapter.PhongBanAdapter;
import com.dns.nguyensi.quanlynhanvien.database.DatabaseHepper;
import com.dns.nguyensi.quanlynhanvien.model.NhanVien;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.util.ArrayList;

public class PhongBanActivity extends AppCompatActivity {

    private static final int FORDER_IMG = 0;
    public static final int REQUESTCODE_CAMERA = 1;
    public static ArrayList<PhongBan> listPhongbaan = new ArrayList<>();
    public static DatabaseHepper database = null;
    public static PhongBanAdapter adapter2;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ListView lstphongban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        CreateDatabase();
        anhxa();
        ActionToolbar();


        lstphongban.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhongBanActivity.this, ChiTietPBActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });


    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intentthem = new Intent(PhongBanActivity.this, ChiTietPBActivity.class);
                startActivity(intentthem);
                break;
            case R.id.menunhanvien:
                Intent intent = new Intent(PhongBanActivity.this, NhanVienActivity.class);
                startActivity(intent);
                break;
            case R.id.menunphongban:
                Intent intent1 = new Intent(PhongBanActivity.this, PhongBanActivity.class);
                startActivity(intent1);
                break;
            case R.id.menubando:
                Intent intent2 = new Intent(PhongBanActivity.this, MapsActivity.class);
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

    private void CreateDatabase() {
        database = new DatabaseHepper(this);
        listPhongbaan = database.getAllPhongban();
    }

    @Override
    protected void onResume() {

        super.onResume();
        CreateDatabase();
        anhxa();
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarphongban);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayoutpb);
        lstphongban = (ListView) findViewById(R.id.listviewphongban);
        adapter2 = new PhongBanAdapter(this, PhongBanActivity.listPhongbaan);
        lstphongban.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
    }

//    public void ControlDialog() {
//        dialog = new Dialog(this, R.style.Dialog);
//        dialog.setContentView(R.layout.custom_listview_thempb);
//        final EditText txttenpb = dialog.findViewById(R.id.edittexttenphongban);
//        Button btnxn = dialog.findViewById(R.id.buttonxacnhan);
//        btnxn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (txttenpb.getText().toString().equals("")) {
//                    Toast.makeText(PhongBanActivity.this, "Mời bạn nhập đầy đủ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String txtten = txttenpb.getText().toString();
//                PhongBan phongBan = new PhongBan(txtten);
//                phongBan = MainActivity.database.addPhongBan(phongBan);
//                MainActivity.listPhongbaan.add(phongBan);
//                phongBanAdapter.notifyDataSetChanged();
//                dialog.cancel();
//            }
//        });
//        dialog.create();
//        dialog.show();
//    }
}
