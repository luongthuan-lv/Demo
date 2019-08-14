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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.adapter_two.NgayChiAdapter;
import vn.edu.poly.demo.database_two.chiDao;
import vn.edu.poly.demo.declare.Chi;

public class NgayChiActivity extends AppCompatActivity {
    private RecyclerView rvRecyclerView2;
    public FloatingActionButton fcbChiAdd2;
    public vn.edu.poly.demo.database_two.chiDao chiDao;
    private AlertDialog alertDialog;
    public NgayChiAdapter ngayChiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngay_chi);

        rvRecyclerView2=findViewById(R.id.rvRecyclerView2);
        fcbChiAdd2=findViewById(R.id.fcbChiAdd2);
        rvRecyclerView2.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
        chiDao=new chiDao(NgayChiActivity.this);
        List<Chi> chiList = chiDao.getAllChi();
        rvRecyclerView2.setLayoutManager(staggeredGridLayoutManager);
        ngayChiAdapter = new NgayChiAdapter(NgayChiActivity.this, chiList);
        rvRecyclerView2.setAdapter(ngayChiAdapter);

        fcbChiAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NgayChiActivity.this);
                View dialog = LayoutInflater.from(NgayChiActivity.this).inflate(R.layout.my_dialog, null);
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
                        chiDao = new chiDao(NgayChiActivity.this);
                        String name = edtTen.getText().toString().trim();
                        if (name.equals("")) {
                            Toast.makeText(NgayChiActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.applyPattern("dd/mm/yyyy");
                            Date d = sdf.parse(name);

                        } catch (Exception e) {
                            Toast.makeText(NgayChiActivity.this, "Vui lòng nhập ngày là số và theo đúng định dạng dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Chi chi = new Chi();
                        chi.LoaiChi = "No data entered";
                        chi.KhoanChi = "No data entered";
                        chi.NgayChi = name;
                        chi.SoTienChi = 0.0;
                        long res = chiDao.insertChi(chi);

                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                        List<Chi> thuList = chiDao.getAllChi();
                        rvRecyclerView2.setLayoutManager(staggeredGridLayoutManager);
                        ngayChiAdapter = new NgayChiAdapter(NgayChiActivity.this, thuList);
                        rvRecyclerView2.setAdapter(ngayChiAdapter);
                        if (res > 0) {
                            Toast.makeText(NgayChiActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(NgayChiActivity.this, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();

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
        chiDao.Close();
    }
}
