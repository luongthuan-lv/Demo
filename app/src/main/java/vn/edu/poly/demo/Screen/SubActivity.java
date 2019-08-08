package vn.edu.poly.demo.Screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.ThuActivity;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    public void Khoanthu(View view) {
        startActivity(new Intent(SubActivity.this, ThuActivity.class));
    }
}
