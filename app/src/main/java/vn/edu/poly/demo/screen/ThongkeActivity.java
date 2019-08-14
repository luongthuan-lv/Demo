package vn.edu.poly.demo.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.database_two.chiDao;
import vn.edu.poly.demo.declare.Thu;

public class ThongkeActivity extends AppCompatActivity {
    public vn.edu.poly.demo.database_one.thuDao thuDao;
    public vn.edu.poly.demo.database_two.chiDao chiDao;
    public TextView tvTongthu,tvTongchi;
    public Button btnTongthu,btnTongchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        tvTongchi=findViewById(R.id.tvTongchi);
        tvTongthu=findViewById(R.id.tvTongthu);
        btnTongchi=findViewById(R.id.btnTongchi);
        btnTongthu=findViewById(R.id.btnTongthu);
        thuDao=new thuDao(ThongkeActivity.this);
        chiDao=new chiDao(ThongkeActivity.this);

        btnTongthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double money=thuDao.sumThu();
                tvTongthu.setText(money+"");

            }
        });

        btnTongchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double money=chiDao.sumChi();
                tvTongchi.setText(money+"");
            }
        });


    }
}
