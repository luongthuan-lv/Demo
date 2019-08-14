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

import java.util.List;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.adapter_one.ThuAdapter;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.declare.Thu;

public class KhoanThuActivity extends AppCompatActivity {
    public RecyclerView rvThuRecyclerView;
    public vn.edu.poly.demo.database_one.thuDao thuDao;
    public ThuAdapter thuAdapter;
    private FloatingActionButton fcbAdd;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_thu);

        rvThuRecyclerView = findViewById(R.id.rvThuRecyclerView);
        fcbAdd = findViewById(R.id.fcbAdd);

        rvThuRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);


        thuDao = new thuDao(KhoanThuActivity.this);
        List<Thu> thuList = thuDao.getAllThu();
        rvThuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        thuAdapter = new ThuAdapter(KhoanThuActivity.this, thuList);
        rvThuRecyclerView.setAdapter(thuAdapter);

        fcbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KhoanThuActivity.this);
                View dialog = LayoutInflater.from(KhoanThuActivity.this).inflate(R.layout.my_dialog, null);
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
                        thuDao = new thuDao(KhoanThuActivity.this);
                        String name = edtTen.getText().toString().trim();

                        if (name.equals("")) {
                            Toast.makeText(KhoanThuActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        } else {
                            Thu thu = new Thu();
                            thu.KhoanThu = name;
                            thu.LoaiThu = "No data entered";
                            thu.NgayThu = "dd/mm/yyyy";
                            thu.SoTienThu = 0.0;
                            long res = thuDao.insertThu(thu);

                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                            List<Thu> thuList = thuDao.getAllThu();
                            rvThuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                            thuAdapter = new ThuAdapter(KhoanThuActivity.this, thuList);
                            rvThuRecyclerView.setAdapter(thuAdapter);

                            if (res > 0) {
                                Toast.makeText(KhoanThuActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(KhoanThuActivity.this, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();

                            }
                            alertDialog.dismiss();
                        }
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
