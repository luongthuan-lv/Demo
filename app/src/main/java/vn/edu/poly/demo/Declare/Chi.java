package vn.edu.poly.demo.Declare;

public class Chi {
    public String KhoanChi, NoiDungChi, NgayChi;
    public double SoTienChi;

    public Chi(String khoanChi, String noiDungChi, String ngayChi, double soTienChi) {
        this.KhoanChi = khoanChi;
        this.NoiDungChi = noiDungChi;
        this.NgayChi = ngayChi;
        this.SoTienChi = soTienChi;
    }

    public String getKhoanChi() {
        return KhoanChi;
    }

    public void setKhoanChi(String khoanChi) {
        KhoanChi = khoanChi;
    }

    public String getNoiDungChi() {
        return NoiDungChi;
    }

    public void setNoiDungChi(String noiDungChi) {
        NoiDungChi = noiDungChi;
    }

    public String getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(String ngayChi) {
        NgayChi = ngayChi;
    }

    public double getSoTienChi() {
        return SoTienChi;
    }

    public void setSoTienChi(double soTienChi) {
        SoTienChi = soTienChi;
    }
}
