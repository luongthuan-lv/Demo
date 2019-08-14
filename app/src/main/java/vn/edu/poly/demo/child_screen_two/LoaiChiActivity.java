package vn.edu.poly.demo.child_screen_two;

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
import vn.edu.poly.demo.adapter_two.LoaiChiAdapter;
import vn.edu.poly.demo.database_two.chiDao;
import vn.edu.poly.demo.declare.Chi;

public class LoaiChiActivity extends AppCompatActivity {
    private RecyclerView rvRecyclerView1;
    private FloatingActionButton fcbChiAdd1;
    public vn.edu.poly.demo.database_two.chiDao chiDao;
    private AlertDialog alertDialog;
    public LoaiChiAdapter loaiChiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi);

        rvRecyclerView1 = findViewById(R.id.rvRecyclerView1);
        fcbChiAdd1 = findViewById(R.id.fcbChiAdd1);
        rvRecyclerView1.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
        chiDao = new chiDao(LoaiChiActivity.this);
        List<Chi> chiList = chiDao.getAllChi();
        rvRecyclerView1.setLayoutManager(staggeredGridLayoutManager);
        loaiChiAdapter = new LoaiChiAdapter(LoaiChiActivity.this, chiList);
        rvRecyclerView1.setAdapter(loaiChiAdapter);

        fcbChiAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoaiChiActivity.this);
                View dialog = LayoutInflater.from(LoaiChiActivity.this).inflate(R.layout.my_dialog, null);
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
                        chiDao = new chiDao(LoaiChiActivity.this);
                        String name = edtTen.getText().toString().trim();

                        if (name.equals("")) {
                            Toast.makeText(LoaiChiActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        } else {
                            Chi chi = new Chi();
                            chi.LoaiChi = name;
                            chi.KhoanChi = "No data entered";
                            chi.NgayChi = "dd/mm/yyyy";
                            chi.SoTienChi = 0.0;
                            long res = chiDao.insertChi(chi);

                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                            List<Chi> thuList = chiDao.getAllChi();
                            rvRecyclerView1.setLayoutManager(staggeredGridLayoutManager);
                            loaiChiAdapter = new LoaiChiAdapter(LoaiChiActivity.this, thuList);
                            rvRecyclerView1.setAdapter(loaiChiAdapter);
                            if (res > 0) {
                                Toast.makeText(LoaiChiActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(LoaiChiActivity.this, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();

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
        chiDao.Close();
    }
}
