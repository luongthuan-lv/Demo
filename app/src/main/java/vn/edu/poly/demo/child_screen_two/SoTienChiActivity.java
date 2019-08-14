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
import vn.edu.poly.demo.adapter_two.SoTienChiAdapter;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.database_two.chiDao;
import vn.edu.poly.demo.declare.Chi;

public class SoTienChiActivity extends AppCompatActivity {
    public RecyclerView rvRecyclerView3;
    public FloatingActionButton fcbChiAdd3;
    public vn.edu.poly.demo.database_two.chiDao chiDao;
    public vn.edu.poly.demo.database_one.thuDao thuDao;
    private AlertDialog alertDialog;
    public SoTienChiAdapter soTienChiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_tien_chi);

        rvRecyclerView3=findViewById(R.id.rvRecyclerView3);
        fcbChiAdd3=findViewById(R.id.fcbChiAdd3);
        rvRecyclerView3.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
        chiDao=new chiDao(SoTienChiActivity.this);
        List<Chi> chiList = chiDao.getAllChi();
        rvRecyclerView3.setLayoutManager(staggeredGridLayoutManager);
        soTienChiAdapter = new SoTienChiAdapter(SoTienChiActivity.this, chiList);
        rvRecyclerView3.setAdapter(soTienChiAdapter);

        fcbChiAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SoTienChiActivity.this);
                View dialog = LayoutInflater.from(SoTienChiActivity.this).inflate(R.layout.my_dialog, null);
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
                        chiDao = new chiDao(SoTienChiActivity.this);
                        thuDao=new thuDao(SoTienChiActivity.this);
                        double totalmoney=thuDao.sumThu();
                        String money = edtTen.getText().toString().trim();
                        if (money.equals("")) {
                            Toast.makeText(SoTienChiActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }else if(Double.parseDouble(money)>totalmoney){
                            Toast.makeText(SoTienChiActivity.this, "Vui lòng nhập số tiền chi nhỏ hơn tổng thu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            if (Double.parseDouble(money)<0){
                                Toast.makeText(SoTienChiActivity.this, "Vui lòng tiền lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (Exception e){
                            Toast.makeText(SoTienChiActivity.this, "Vui lòng nhập tiền là số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Chi chi = new Chi();
                        chi.LoaiChi = "No data entered";
                        chi.KhoanChi = "No data entered";
                        chi.NgayChi = "dd/mm/yyyy";
                        chi.SoTienChi = Double.parseDouble(money);
                        long res = chiDao.insertChi(chi);

                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(9, StaggeredGridLayoutManager.HORIZONTAL);
                        List<Chi> thuList = chiDao.getAllChi();
                        rvRecyclerView3.setLayoutManager(staggeredGridLayoutManager);
                        soTienChiAdapter = new SoTienChiAdapter(SoTienChiActivity.this, thuList);
                        rvRecyclerView3.setAdapter(soTienChiAdapter);
                        if (res > 0) {
                            Toast.makeText(SoTienChiActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SoTienChiActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}
