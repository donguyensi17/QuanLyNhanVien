package com.dns.nguyensi.quanlynhanvien.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dns.nguyensi.quanlynhanvien.R;
import com.dns.nguyensi.quanlynhanvien.model.PhongBan;

import java.util.ArrayList;

public class PhongBanAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<PhongBan> arrayListPhongban;


    public PhongBanAdapter(Context context,ArrayList<PhongBan> arrayListPhongban){
        super(context,0,arrayListPhongban);
        this.context = context;
        this.arrayListPhongban = arrayListPhongban;
    }



//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        PhongBanAdapter.ViewHolder viewHolder;
//        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_phongban,null);
//            viewHolder = new PhongBanAdapter.ViewHolder();
//            viewHolder.txtmapb = convertView.findViewById(R.id.textviewmaphongban);
//            viewHolder.txttenpb = convertView.findViewById(R.id.textviewtenphongban);
//            convertView.setTag(viewHolder);
//        }
//        else
//            viewHolder = (PhongBanAdapter.ViewHolder) convertView.getTag();
//        if(position > -1){
//            String id = arrayListPhongban.get(position).getMapb();
//            String name = arrayListPhongban.get(position).getTenphongban();
//            viewHolder.txtmapb.setText(id+"");
//            viewHolder.txttenpb.setText(name);
//        }
//        return convertView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhongBanAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new PhongBanAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview_phongban,null);
            viewHolder.txtmapb = (TextView) convertView.findViewById(R.id.textviewmaphongban);
            viewHolder.txttenpb = (TextView)  convertView.findViewById(R.id.textviewtenphongban);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (PhongBanAdapter.ViewHolder) convertView.getTag();
        }
        PhongBan phongBan = (PhongBan) getItem(position);
        viewHolder.txtmapb.setText(phongBan.getMapb());
        viewHolder.txttenpb.setText(phongBan.getTenphongban());
        return convertView;
    }

    static class ViewHolder{
        TextView txtmapb;
        TextView txttenpb;
    }
}
