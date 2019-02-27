package com.dns.nguyensi.quanlynhanvien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.dns.nguyensi.quanlynhanvien.model.NhanVien;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.util.ArrayList;

public class DatabaseHepper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="QLNhanVien";
    private static final String TABLE_NHANVIEN ="nhanvien";
    private static final String TABLE_PHONGBAN ="phongban";
    private static final String IDTT ="DH";
    private static final String TABLE_ID_MAX ="max_id";


    private Context context;

    public DatabaseHepper(Context context) {
        super(context, DATABASE_NAME,null, 1);
        this.context = context;
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String idmanv = "CREATE TABLE IF NOT EXISTS " + IDTT + "("
                + "manhanvien" + " TEXT PRIMARY KEY"
                + ")";
        String tbnhanvien = "CREATE TABLE IF NOT EXISTS " + TABLE_NHANVIEN + "("
                + "manhanvien" + " TEXT PRIMARY KEY,"
                + "tennhanvien" + " TEXT,"
                + "diachi" + " TEXT,"
                + "sdt" + " INTEGER,"
                + "hinhanh" + " BLOB ,"
                + "maphongban" + " TEXT"
                + ")";
        String tbphongban = "CREATE TABLE IF NOT EXISTS  " + TABLE_PHONGBAN + "("
                + "mapb" + " TEXT PRIMARY KEY,"
                + "tenphongban" + " TEXT" + ")";
        String tbluuid = "CREATE TABLE IF NOT EXISTS  " + TABLE_ID_MAX + "("
                + "table_name" + " TEXT PRIMARY KEY," + "max_id" + " INTEGER" + ")";


        db.execSQL(tbnhanvien);
        db.execSQL(tbphongban);
        db.execSQL(tbluuid);
        db.execSQL(idmanv);

        Toast.makeText(context, "tạo bảng tc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHONGBAN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ID_MAX);
        onCreate(db);

    }

    public NhanVien addNhanVien(NhanVien  nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("manhanvien", nhanVien.getManhanvien() );
        values.put("name", nhanVien.getTennhanvien());
        values.put("diachi",nhanVien.getDiachi());
        values.put("sdt", nhanVien.getSodienthoai());
        values.put("hinhanh", nhanVien.getHinhanh());
        values.put("mapb", nhanVien.getMaphongban());
        db.insert(TABLE_NHANVIEN,null,values);
        nhanVien.setManhanvien(nhanVien.getManhanvien());
//        updateMaxId(TABLE_NHANVIEN,nhanVien.getManhanvien());
        db.close();
        return nhanVien;
    }

    public PhongBan addPhongBan(PhongBan phongBan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("mapb", phongBan.getMapb() );
        values.put("tenphongban", phongBan.getTenphongban());
        db.insert(TABLE_PHONGBAN,null,values);
        phongBan.setMapb(phongBan.getMapb() );
//        updateMaxId(TABLE_PHONGBAN,phongBan.getMapb() );
        db.close();
        return phongBan;
    }

    public void updateNhanVien(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", nhanVien.getTennhanvien());
        values.put("diachi",nhanVien.getDiachi());
        values.put("sdt", nhanVien.getSodienthoai());
        values.put("hinhanh", nhanVien.getHinhanh());
        values.put("mapb", nhanVien.getMaphongban());
        db.update(TABLE_NHANVIEN,values,"manhanvien = '"+nhanVien.getManhanvien() +"'",null);

    }

    public void updatePhongban(PhongBan phongBan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenphongban", phongBan.getTenphongban());
        db.update(TABLE_PHONGBAN,values,"maphongban = '" + phongBan.getMapb() + "'",null);

    }

    public Boolean deleteNhanVien(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NHANVIEN, "manhanvien" + " = '" + id + "'", null) > 0;
    }
    public Boolean deletePhongban(String id){
        if(!checkPhongbanNV(id)){
            SQLiteDatabase db = this.getReadableDatabase();
            return db.delete(TABLE_PHONGBAN, "maphongban" + " = '" + id + "'", null) > 0;
        }
        return false;
    }

    public Boolean checkPhongbanNV(String maphongban){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NHANVIEN + " WHERE " + "maphongban" + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[] {maphongban+""});
        if (cursor != null){
            if(cursor.getCount() > 0){
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        }
        return true;
    }

    public ArrayList<NhanVien> getAllNhanVien(){
        ArrayList<NhanVien> lst = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor  cursor = db.rawQuery("select manhanvien,tennhanvien,diachi,sdt,hinhanh,maphongban from "+TABLE_NHANVIEN,null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String manhanvien = cursor.getString(cursor.getColumnIndex("manhanvien"));
                    String tennhanvien = cursor.getString(cursor.getColumnIndex("tennhanvien"));
                    String diachi = cursor.getString(cursor.getColumnIndex("diachi"));
                    int sodienthoai = cursor.getInt(cursor.getColumnIndex("sodienthoai"));
                    byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex("hinhanh"));
                    String maphongban = cursor.getString(cursor.getColumnIndex("maphongban"));

                    lst.add(new NhanVien(manhanvien,tennhanvien,diachi,sodienthoai,hinhanh,maphongban));
                    cursor.moveToNext();
                }
            }
        }finally {

        }
        return lst;
    }

    public ArrayList<PhongBan> getAllPhongban(){
        ArrayList<PhongBan> lst = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from "+TABLE_PHONGBAN,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String mapb = cursor.getString(cursor.getColumnIndex("mapb"));
                String tenphongban = cursor.getString(cursor.getColumnIndex("tenphongban"));

                lst.add(new PhongBan(mapb,tenphongban));
                cursor.moveToNext();
            }
        }
        return lst;
    }
}
