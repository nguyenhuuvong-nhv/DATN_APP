package com.example.doantn.Model;

public class TaiKhoan {
    public String _id;
    public String Username;
    public  String MatKhau;
    public  String LoaiTaiKhoan;
    public String TrangThai;
    public  String HoTen;

    public TaiKhoan() {
    }
    public TaiKhoan(String _id, String username, String matKhau, String loaiTaiKhoan, String trangThai, String hoTen) {
        this._id = _id;
        Username = username;
        MatKhau = matKhau;
        LoaiTaiKhoan = loaiTaiKhoan;
        TrangThai = trangThai;
        HoTen = hoTen;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        LoaiTaiKhoan = loaiTaiKhoan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }
}
