package com.mycompany.quanlyquancoffee.Models;

public class TaiKhoanDTO {
    private String tenDangNhap;
    private String matKhau;
    private String quyen;
    private String maNV; // dùng khi tạo hoặc gán lại tài khoản

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String tenDangNhap, String matKhau, String quyen, String maNV) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.maNV = maNV;
    }

    // Getters và Setters
    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getQuyen() { return quyen; }
    public void setQuyen(String quyen) { this.quyen = quyen; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
}
