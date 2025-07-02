package DTO;

public class ChuyenMonRequest {
    private String maHdNguon;
    private String maBanDich;
    private ChiTietMonDTO monChuyen;

    // Getters & Setters
    public String getMaHdNguon() {
        return maHdNguon;
    }

    public void setMaHdNguon(String maHdNguon) {
        this.maHdNguon = maHdNguon;
    }

    public String getMaBanDich() {
        return maBanDich;
    }

    public void setMaBanDich(String maBanDich) {
        this.maBanDich = maBanDich;
    }

    public ChiTietMonDTO getMonChuyen() {
        return monChuyen;
    }

    public void setMonChuyen(ChiTietMonDTO monChuyen) {
        this.monChuyen = monChuyen;
    }
}
