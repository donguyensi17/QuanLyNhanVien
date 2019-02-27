package com.dns.nguyensi.quanlynhanvien.model;

public class PhongBan {
    private String mapb;
    private String tenphongban;

    public PhongBan(String mapb, String tenphongban) {
        this.mapb = mapb;
        this.tenphongban = tenphongban;
    }
    public PhongBan(){

    }
    public PhongBan(String tenphongban) {
        this.tenphongban = tenphongban;
    }

    public String getMapb() {
        return mapb;
    }

    public void setMapb(String mapb) {
        this.mapb = mapb;
    }

    public String getTenphongban() {
        return tenphongban;
    }

    public void setTenphongban(String tenphongban) {
        this.tenphongban = tenphongban;
    }

    @Override
    public String toString() {
        return "PhongBan{" +
                "mapb='" + mapb + '\'' +
                ", tenphongban='" + tenphongban + '\'' +
                '}';
    }
}
