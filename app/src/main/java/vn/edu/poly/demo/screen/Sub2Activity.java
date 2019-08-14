package vn.edu.poly.demo.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.child_screen_two.KhoanChiActivity;
import vn.edu.poly.demo.child_screen_two.LoaiChiActivity;
import vn.edu.poly.demo.child_screen_two.NgayChiActivity;
import vn.edu.poly.demo.child_screen_two.SoTienChiActivity;

public class Sub2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
    }

    public void Khoanchi(View view) {
        startActivity(new Intent(Sub2Activity.this, KhoanChiActivity.class));
    }

    public void Loaichi(View view) {
        startActivity(new Intent(Sub2Activity.this, LoaiChiActivity.class));
    }

    public void Ngaychi(View view) {
        startActivity(new Intent(Sub2Activity.this, NgayChiActivity.class));
    }

    public void Sotienchi(View view) {
        startActivity(new Intent(Sub2Activity.this, SoTienChiActivity.class));
    }
}
