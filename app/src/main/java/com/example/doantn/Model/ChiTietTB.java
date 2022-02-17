package com.example.doantn.Model;

public class ChiTietTB {

    public String Serial;
    public String NhanHieu;
    public  String CPU;
    public String Ram;
    public String BoNho;
    public  String CongSuat;

    public ChiTietTB() {
    }

    public ChiTietTB(String serial, String nhanHieu, String CPU, String ram, String boNho, String congSuat) {
        this.Serial = serial;
        this.NhanHieu = nhanHieu;
        this.CPU = CPU;
        this.Ram = ram;
        this.BoNho = boNho;
        this.CongSuat = congSuat;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public String getNhanHieu() {
        return NhanHieu;
    }

    public void setNhanHieu(String nhanHieu) {
        NhanHieu = nhanHieu;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getBoNho() {
        return BoNho;
    }

    public void setBoNho(String boNho) {
        BoNho = boNho;
    }

    public String getCongSuat() {
        return CongSuat;
    }

    public void setCongSuat(String congSuat) {
        CongSuat = congSuat;
    }
}
