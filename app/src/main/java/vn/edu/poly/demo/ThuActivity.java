package vn.edu.poly.demo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.demo.Adapter.ThuAdapter;
import vn.edu.poly.demo.Database.ThuDao;
import vn.edu.poly.demo.Database.ThuRender;
import vn.edu.poly.demo.Declare.Thu;

public class ThuActivity extends AppCompatActivity {
    public RecyclerView rvThuRecyclerView;
    public ThuDao thuDao;
    public ThuRender thuRender;
    public ThuAdapter thuAdapter;
    private FloatingActionButton fcbAdd;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu);

        rvThuRecyclerView = findViewById(R.id.rvThuRecyclerView);
        fcbAdd = findViewById(R.id.fcbAdd);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThuActivity.this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(ThuActivity.this,3);


        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(9,StaggeredGridLayoutManager.HORIZONTAL);


        thuDao = new ThuDao(ThuActivity.this);
        List<Thu> thuList = thuDao.getAllThu();
        rvThuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        thuAdapter = new ThuAdapter(ThuActivity.this, thuList);
        rvThuRecyclerView.setAdapter(thuAdapter);

        fcbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThuActivity.this);
                View dialog = LayoutInflater.from(ThuActivity.this).inflate(R.layout.my_dialog, null);
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
                        thuDao = new ThuDao(ThuActivity.this);
                        String ten = edtTen.getText().toString().trim();

                        if (ten.equals("")) {
                            Toast.makeText(ThuActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        } else {
                            Thu thu = new Thu();
                            thu.KhoanThu = ten;
                            long res = thuDao.insertThu(thu);

                            StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(9,StaggeredGridLayoutManager.HORIZONTAL);
                            List<Thu> thuList = thuDao.getAllThu();
                            rvThuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                            thuAdapter = new ThuAdapter(ThuActivity.this, thuList);
                            rvThuRecyclerView.setAdapter(thuAdapter);

                            if (res > 0) {

                                Toast.makeText(ThuActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            } else {

                                Toast.makeText(ThuActivity.this, "Thêm Thất Bại!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });


            }
        });

    }


}
