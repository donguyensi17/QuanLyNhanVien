package com.dns.nguyensi.quanlynhanvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<NhanVien> arrayListNhanVien;
    int pos = -1;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> arrayListNhanVien)
    {
        super(context, 0, arrayListNhanVien);
        this.context = context;
        this.arrayListNhanVien = arrayListNhanVien;
    }

    static class ViewHolder{
        TextView txtmanhanvien, txttennhanvien, txtphongban;
        ImageView hinhanh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NhanVienAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_nhanvien, null);
            viewHolder = new NhanVienAdapter.ViewHolder();
            viewHolder.txtmanhanvien = convertView.findViewById(R.id.textviewmasonhanvien);
            viewHolder.txttennhanvien = convertView.findViewById(R.id.textviewtennhanvien);
            viewHolder.txtphongban = convertView.findViewById(R.id.textviewphongbannhanvien);
            viewHolder.hinhanh = convertView.findViewById(R.id.imageviewnhanvien);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NhanVienAdapter.ViewHolder) convertView.getTag();
            String manhanvien = arrayListNhanVien.get(position).getManhanvien();
            String tennhanvien = arrayListNhanVien.get(position).getTennhanvien();
            String phongban = arrayListNhanVien.get(position).getMaphongban();
            byte[] hinhanh = arrayListNhanVien.get(position).getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);

            viewHolder.txtmanhanvien.setText("Mã nhân viên" + ": " + manhanvien);
            viewHolder.txttennhanvien.setText("Tên nhân viên" + ": " + tennhanvien);
            viewHolder.txtphongban.setText(phongban);

            viewHolder.hinhanh.setImageBitmap(bitmap);
            viewHolder.hinhanh.setClipToOutline(true);
        }
        return convertView;
    }
}
