package com.example.doantn.Model;

public class ThietBi {

    public String _id;
    public String TenTB;
    public  String NgayNhap;
    public String ThoiHanBaoHanh;
    public String NguoiQuanLy;
    public  String MaPB;
    public String TrangThai;
    public ChiTietTB ChiTietThietBi;

    public ThietBi(String _id, String tenTB, String ngayNhap, String thoiHanBaoHanh, String nguoiQuanLy, String maPB, String trangThai, ChiTietTB chiTietThietBi) {
        this._id = _id;
        TenTB = tenTB;
        NgayNhap = ngayNhap;
        ThoiHanBaoHanh = thoiHanBaoHanh;
        NguoiQuanLy = nguoiQuanLy;
        MaPB = maPB;
        TrangThai = trangThai;
        ChiTietThietBi = chiTietThietBi;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String tenTB) {
        TenTB = tenTB;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public String getThoiHanBaoHanh() {
        return ThoiHanBaoHanh;
    }

    public void setThoiHanBaoHanh(String thoiHanBaoHanh) {
        ThoiHanBaoHanh = thoiHanBaoHanh;
    }

    public String getNguoiQuanLy() {
        return NguoiQuanLy;
    }

    public void setNguoiQuanLy(String nguoiQuanLy) {
        NguoiQuanLy = nguoiQuanLy;
    }

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String maPB) {
        MaPB = maPB;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public ChiTietTB getChiTietThietBi() {
        return ChiTietThietBi;
    }

    public void setChiTietThietBi(ChiTietTB chiTietThietBi) {
        ChiTietThietBi = chiTietThietBi;
    }
}
