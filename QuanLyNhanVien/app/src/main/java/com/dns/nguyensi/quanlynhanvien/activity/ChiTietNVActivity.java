package com.dns.nguyensi.quanlynhanvien.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.adapter.NhanVienAdapter;
import com.dns.nguyensi.quanlynhanvien.database.DatabaseHepper;
import com.dns.nguyensi.quanlynhanvien.model.NhanVien;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.dns.nguyensi.quanlynhanvien.activity.NhanVienActivity.REQUESTCODE_CAMERA;
import static com.dns.nguyensi.quanlynhanvien.activity.NhanVienActivity.adapter;
import static com.dns.nguyensi.quanlynhanvien.activity.NhanVienActivity.database;
import static com.dns.nguyensi.quanlynhanvien.activity.NhanVienActivity.listNhanvien;

public class ChiTietNVActivity extends AppCompatActivity {

    private static final int IMG_CH = 0;
    public static final int REQUESTCODE_CAMERA = 1;
    public static ArrayList<NhanVien> listNhanvien = new ArrayList<>();
    public static DatabaseHepper database = null;
    int position = -1;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView imgNhanVien, imgchup, imgchon;
    Button btnThem, btnXoa, btnSua;
    TextView txtmanv, txtTen, txtDiachi, txtSdt, txtMaPhongban;
    Spinner spinnerMaphongban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nv);
        Anhxa();
        ActionToolbar();

        imgchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMG_CH);
            }
        });
        imgchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUESTCODE_CAMERA);
            }
        });

        NhanVien nhanVien;
        if(position != -1){
            nhanVien = NhanVienActivity.listNhanvien.get(position);
            if (nhanVien != null) {
                txtTen.setText(nhanVien.getTennhanvien());
                Bitmap bitmap = BitmapFactory.decodeByteArray(nhanVien.getHinhanh(), 0, nhanVien.getHinhanh().length);
                imgNhanVien.setImageBitmap(bitmap);
                txtSdt.setText(nhanVien.getSodienthoai() + "");

                ArrayAdapter<PhongBan> adapterPhongban = new ArrayAdapter<>(this, R.layout.custom_spinner_maphongban, NhanVienActivity.listPhongbaan);
                adapterPhongban.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinnerMaphongban.setAdapter(adapterPhongban);
                for (int i = 0; i < NhanVienActivity.listPhongbaan.size(); i++) {
                    if (NhanVienActivity.listPhongbaan.get(i).getMapb().equals(nhanVien.getMaphongban())) {
                        spinnerMaphongban.setSelection(i);
                        break;
                    }
                }
            }
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] imageInByte = null;
                if (imgNhanVien.getDrawable() != null) {
                    BitmapDrawable drawable = (BitmapDrawable) imgNhanVien.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imageInByte = stream.toByteArray();
                } else {
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.error, null);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imageInByte = stream.toByteArray();
                }

                PhongBan phongBan = (PhongBan) spinnerMaphongban.getSelectedItem();

                if(txtmanv.getText().toString().equals("") || txtTen.getText().toString().equals("") || txtDiachi.getText().toString().equals("")
                        || txtSdt.getText().toString().equals("") || txtMaPhongban.getText().toString().equals("")){
                    Toast.makeText(ChiTietNVActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }else if (imageInByte == null){
                    Toast.makeText(ChiTietNVActivity.this, "lỗi", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String txtma = txtmanv.getText().toString();
                    String txttennv = txtTen.getText().toString();
                    String txtdc = txtDiachi.getText().toString();
                    int txtsdt = Integer.parseInt(txtSdt.getText().toString());

                    NhanVien nhanVien = new NhanVien(txtma, txttennv, txtdc, txtsdt, imageInByte, phongBan.getMapb());
                    database.addNhanVien(nhanVien);
                    listNhanvien.add(nhanVien);
                    Toast.makeText(ChiTietNVActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ChiTietNVActivity.this, NhanVienActivity.class);
                    startActivity(intent);
                }
            }
        });
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
                Intent intent = new Intent(ChiTietNVActivity.this, NhanVienActivity.class);
                startActivity(intent);
                break;
            case R.id.menunphongban:
                Intent intent1 = new Intent(ChiTietNVActivity.this, PhongBanActivity.class);
                startActivity(intent1);
                break;
            case R.id.menubando:
                Intent intent2 = new Intent(ChiTietNVActivity.this, MapsActivity.class);
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
        toolbar = (Toolbar) findViewById(R.id.toolbarchitietnhanvien);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        imgNhanVien = findViewById(R.id.imghinhanhchitietnhanvien);
        imgchup = (ImageView) findViewById(R.id.imgchitietchuphinhanh);
        imgchon = (ImageView) findViewById(R.id.imgchitietchonhinhanh);
        btnThem = (Button) findViewById(R.id.btnThemchitietnhanvien);
        btnXoa = (Button) findViewById(R.id.btnXaochitietnhanvien);
        btnSua = (Button) findViewById(R.id.btnSuachitietnhanvien);
        txtmanv = (TextView) findViewById(R.id.textviewchitietmanhanvien);
        txtTen = (TextView) findViewById(R.id.textviewchitiettennhanvien);
        txtDiachi = (TextView) findViewById(R.id.textviewchitietdiachinhanvien);
        txtSdt = (TextView) findViewById(R.id.textviewchitietsdtnhanvien);
        txtMaPhongban = (TextView) findViewById(R.id.textviewchitietmaphongbancuanhanvien);
        spinnerMaphongban = (Spinner) findViewById(R.id.spinnerchitietmaPhongban);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap b = (Bitmap) data.getExtras().get("data");
            imgNhanVien.setImageBitmap(b);
        }
        if (requestCode == IMG_CH && resultCode == Activity.RESULT_OK) {
            try {
                final Uri a = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(a);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgNhanVien.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
