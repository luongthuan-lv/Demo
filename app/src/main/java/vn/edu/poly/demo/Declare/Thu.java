package vn.edu.poly.demo.Declare;

public class Thu {
    public String KhoanThu, NoiDungThu, NgayThu;
    public double SoTienThu;

    public Thu(String khoanThu, String noiDungThu, String ngayThu, double soTienThu) {
        this.KhoanThu = khoanThu;
        this.NoiDungThu = noiDungThu;
        this.NgayThu = ngayThu;
        this.SoTienThu = soTienThu;
    }

    public String getKhoanThu() {
        return KhoanThu;
    }

    public void setKhoanThu(String khoanThu) {
        KhoanThu = khoanThu;
    }

    public String getNoiDungThu() {
        return NoiDungThu;
    }

    public void setNoiDungThu(String noiDungThu) {
        NoiDungThu = noiDungThu;
    }

    public String getNgayThu() {
        return NgayThu;
    }

    public void setNgayThu(String ngayThu) {
        NgayThu = ngayThu;
    }

    public double getSoTienThu() {
        return SoTienThu;
    }

    public void setSoTienThu(double soTienThu) {
        SoTienThu = soTienThu;
    }
}
