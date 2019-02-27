package com.dns.nguyensi.quanlynhanvien.model;

import java.util.Arrays;

public class NhanVien {
    private String manhanvien;
    private String tennhanvien;
    private String diachi;
    private int sodienthoai;
    private byte[] hinhanh;
    private String maphongban;

    public NhanVien(String manhanvien, String tennhanvien, String diachi, int sodienthoai, byte[] hinhanh, String maphongban) {
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.hinhanh = hinhanh;
        this.maphongban = maphongban;
    }

    public NhanVien(){

    }

    public NhanVien(String tennhanvien, String diachi, int sodienthoai, byte[] hinhanh, String maphongban) {
        this.tennhanvien = tennhanvien;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.hinhanh = hinhanh;
        this.maphongban = maphongban;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMaphongban() {
        return maphongban;
    }

    public void setMaphongban(String maphongban) {
        this.maphongban = maphongban;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "manhanvien='" + manhanvien + '\'' +
                ", tennhanvien='" + tennhanvien + '\'' +
                ", diachi='" + diachi + '\'' +
                ", sodienthoai=" + sodienthoai +
                ", hinhanh=" + Arrays.toString(hinhanh) +
                ", maphongban='" + maphongban + '\'' +
                '}';
    }
}
