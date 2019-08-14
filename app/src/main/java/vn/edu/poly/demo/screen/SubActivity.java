package vn.edu.poly.demo.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.child_screen_one.LoaiThuActivity;
import vn.edu.poly.demo.child_screen_one.KhoanThuActivity;
import vn.edu.poly.demo.child_screen_one.NgayThuActivity;
import vn.edu.poly.demo.child_screen_one.SoTienThuActivity;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    public void Khoanthu(View view) {
        startActivity(new Intent(SubActivity.this, KhoanThuActivity.class));
    }

    public void Loaithu(View view) {
        startActivity(new Intent(SubActivity.this, LoaiThuActivity.class));
    }

    public void Ngaythu(View view) {
        startActivity(new Intent(SubActivity.this, NgayThuActivity.class));
    }

    public void Sotienthu(View view) {
        startActivity(new Intent(SubActivity.this, SoTienThuActivity.class));
    }
}
