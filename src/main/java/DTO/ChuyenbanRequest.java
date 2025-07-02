/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author HELLO
 */
public class ChuyenbanRequest {
      private String maHdCu;      // not null
    private String maBanCu;     // not null
    private String maBanMoi;    // not null
    private String maNv;        // not null

    public String getMaHdCu() {
        return maHdCu;
    }

    public void setMaHdCu(String maHdCu) {
        this.maHdCu = maHdCu;
    }

    public String getMaBanCu() {
        return maBanCu;
    }

    public void setMaBanCu(String maBanCu) {
        this.maBanCu = maBanCu;
    }

    public String getMaBanMoi() {
        return maBanMoi;
    }

    public void setMaBanMoi(String maBanMoi) {
        this.maBanMoi = maBanMoi;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

}
