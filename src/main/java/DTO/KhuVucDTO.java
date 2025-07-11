package DTO;

public class KhuVucDTO {
    private String maKV;
    private String tenKV;
        private Boolean daXoa;

    public KhuVucDTO() {
    }

    public KhuVucDTO(String maKV, String tenKV) {
        this.maKV = maKV;
        this.tenKV = tenKV;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getTenKV() {
        return tenKV;
    }

    public void setTenKV(String tenKV) {
        this.tenKV = tenKV;
    }

    @Override
    public String toString() {
        return maKV + " - " + tenKV;
    }
    
        public Boolean getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }
}
