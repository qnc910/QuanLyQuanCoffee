package DTO;

import DTO.TaiKhoanDTO;
import java.util.Date;

public class NhanVienTaiKhoanDTO {
    private String maNV;
    private String hoTen;
    private String cmnd;
    private String sdt;
    private String diaChi;
    private Date ngaySinh;
    private Date ngayVaoLam;
    private String viTri;

    private TaiKhoanDTO taiKhoan;

    public NhanVienTaiKhoanDTO() {
    }

    // Constructor đầy đủ
    public NhanVienTaiKhoanDTO(String maNV, String hoTen, String cmnd, String sdt,
                               String diaChi, Date ngaySinh, Date ngayVaoLam,
                               String viTri, TaiKhoanDTO taiKhoan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.viTri = viTri;
        this.taiKhoan = taiKhoan;
    }

    // Constructor dùng cho JPQL custom
    public NhanVienTaiKhoanDTO(String maNV, String hoTen, String cmnd, String sdt,
                               String diaChi, Date ngaySinh, Date ngayVaoLam,
                               String viTri, String tenDangNhap, String matKhau, String quyen) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.viTri = viTri;

        this.taiKhoan = new TaiKhoanDTO();
        this.taiKhoan.setTenDangNhap(tenDangNhap);
        this.taiKhoan.setMatKhau(matKhau);      // ✅ Gán mật khẩu
        this.taiKhoan.setQuyen(quyen);
        this.taiKhoan.setMaNV(maNV);
    }

    // Getters & Setters
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getCmnd() { return cmnd; }
    public void setCmnd(String cmnd) { this.cmnd = cmnd; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public Date getNgayVaoLam() { return ngayVaoLam; }
    public void setNgayVaoLam(Date ngayVaoLam) { this.ngayVaoLam = ngayVaoLam; }

    public String getViTri() { return viTri; }
    public void setViTri(String viTri) { this.viTri = viTri; }

    public TaiKhoanDTO getTaiKhoan() { return taiKhoan; }
    public void setTaiKhoan(TaiKhoanDTO taiKhoan) { this.taiKhoan = taiKhoan; }
}
