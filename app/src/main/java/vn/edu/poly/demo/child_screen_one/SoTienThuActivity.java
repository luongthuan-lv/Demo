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
import vn.edu.poly.demo.adapter_one.SoTienThuAdapter;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.declare.Thu;

public class SoTienThuActivity extends AppCompatActivity {
    private RecyclerView rvLoaiThuRecyclerView3;
    private FloatingActionButton fcbAdd3;
    private AlertDialog alertDialog;
    private vn.edu.poly.demo.database_one.thuDao thuDao;
    private SoTienThuAdapter soTienThuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_tien_thu);

        rvLoaiThuRecyclerView3 = findViewById(R.id.rvSoTienThuRecyclerView3);
        fcbAdd3 = findViewById(R.id.fcbAdd3);
        rvLoaiThuRecyclerView3.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
        thuDao = new thuDao(SoTienThuActivity.this);
        List<Thu> thuList = thuDao.getAllThu();
        rvLoaiThuRecyclerView3.setLayoutManager(staggeredGridLayoutManager);
        soTienThuAdapter = new SoTienThuAdapter(SoTienThuActivity.this, thuList);
        rvLoaiThuRecyclerView3.setAdapter(soTienThuAdapter);

        fcbAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SoTienThuActivity.this);
                View dialog= LayoutInflater.from(SoTienThuActivity.this).inflate(R.layout.my_dialog,null);
                builder.setView(dialog);
                builder.create();
                alertDialog=builder.show();

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
                        thuDao=new thuDao(SoTienThuActivity.this);
                        String money = edtTen.getText().toString().trim();

                        if (money.equals("")) {
                            Toast.makeText(SoTienThuActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            if (Double.parseDouble(money)<0){
                                Toast.makeText(SoTienThuActivity.this, "Vui lòng tiền lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (Exception e){
                            Toast.makeText(SoTienThuActivity.this, "Vui lòng nhập tiền là số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Thu thu = new Thu();
                        thu.NgayThu = "dd/mm/yyyy";
                        thu.LoaiThu = "No data entered";
                        thu.KhoanThu = "No data entered";
                        thu.SoTienThu = Double.parseDouble(money);
                        long res = thuDao.insertThu(thu);

                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                        List<Thu> thuList = thuDao.getAllThu();
                        rvLoaiThuRecyclerView3.setLayoutManager(staggeredGridLayoutManager);
                        soTienThuAdapter = new SoTienThuAdapter(SoTienThuActivity.this, thuList);
                        rvLoaiThuRecyclerView3.setAdapter(soTienThuAdapter);

                        if (res > 0) {
                            Toast.makeText(SoTienThuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SoTienThuActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
