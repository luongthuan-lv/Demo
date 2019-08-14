package vn.edu.poly.demo.child_screen_one;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.adapter_one.NgayThuAdapter;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.declare.Thu;

public class NgayThuActivity extends AppCompatActivity {
    public RecyclerView rvLoaiThuRecyclerView2;
    public FloatingActionButton fcbAdd2;
    public AlertDialog alertDialog;
    public NgayThuAdapter ngayThuAdapter;
    public vn.edu.poly.demo.database_one.thuDao thuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngay_thu);

        rvLoaiThuRecyclerView2 = findViewById(R.id.rvNgayThuRecyclerView2);
        fcbAdd2 = findViewById(R.id.fcbAdd2);
        rvLoaiThuRecyclerView2.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
        thuDao = new thuDao(NgayThuActivity.this);
        List<Thu> thuList = thuDao.getAllThu();
        rvLoaiThuRecyclerView2.setLayoutManager(staggeredGridLayoutManager);
        ngayThuAdapter = new NgayThuAdapter(NgayThuActivity.this, thuList);
        rvLoaiThuRecyclerView2.setAdapter(ngayThuAdapter);

        fcbAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NgayThuActivity.this);
                View dialog = LayoutInflater.from(NgayThuActivity.this).inflate(R.layout.my_dialog, null);
                builder.setView(dialog);
                builder.create();
                alertDialog = builder.show();

                final EditText edtTen;
                Button btnDestroy, btnAdd;

                edtTen = dialog.findViewById(R.id.edtTen);
                btnDestroy = dialog.findViewById(R.id.btnDestroy);
                btnAdd = dialog.findViewById(R.id.btnAdd);

                btnDestroy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        thuDao = new thuDao(NgayThuActivity.this);
                        String name = edtTen.getText().toString().trim();

                        if (name.equals("")) {
                            Toast.makeText(NgayThuActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.applyPattern("dd/mm/yyyy");
                            Date d = sdf.parse(name);

                        } catch (Exception e) {
                            Toast.makeText(NgayThuActivity.this, "Vui lòng nhập ngày là số và theo đúng định dạng dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        Thu thu = new Thu();
                        thu.NgayThu = name;
                        thu.LoaiThu = "No data entered";
                        thu.KhoanThu = "No data entered";
                        thu.SoTienThu = 0.0;
                        long res = thuDao.insertThu(thu);

                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                        List<Thu> thuList = thuDao.getAllThu();
                        rvLoaiThuRecyclerView2.setLayoutManager(staggeredGridLayoutManager);
                        ngayThuAdapter = new NgayThuAdapter(NgayThuActivity.this, thuList);
                        rvLoaiThuRecyclerView2.setAdapter(ngayThuAdapter);

                        if (res > 0) {
                            Toast.makeText(NgayThuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NgayThuActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thuDao.Close();
    }
}
