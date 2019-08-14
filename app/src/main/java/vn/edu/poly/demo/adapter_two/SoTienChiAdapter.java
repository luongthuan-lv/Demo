package vn.edu.poly.demo.adapter_two;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.adapter_one.ThuHolder;
import vn.edu.poly.demo.child_screen_two.SoTienChiActivity;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.database_two.chiDao;
import vn.edu.poly.demo.declare.Chi;

public class SoTienChiAdapter extends RecyclerView.Adapter<ThuHolder> {
    public Context context;
    public List<Chi> chiList;
    public vn.edu.poly.demo.database_two.chiDao chiDao;
    public vn.edu.poly.demo.database_one.thuDao thuDao;
    public AlertDialog alertDialog;

    public SoTienChiAdapter(Context context, List<Chi> chiList) {
        this.context = context;
        this.chiList = chiList;
        this.chiDao = new chiDao(context);
    }

    @NonNull
    @Override
    public ThuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.thu, viewGroup, false);
        ThuHolder thuHolder = new ThuHolder(view);
        return thuHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ThuHolder thuHolder, final int position) {
        final Chi chi = chiList.get(position);
        thuHolder.tvStt.setText(position + 1 + "");
        thuHolder.tvName.setText(chi.SoTienChi+"");

        thuHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiDao.deleteChi(chi);
                chiList.remove(position);
                notifyDataSetChanged();
            }
        });

        thuHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialog = LayoutInflater.from(context).inflate(R.layout.my_dialog_update, null);
                builder.setView(dialog);
                builder.create();
                alertDialog = builder.show();
                final EditText edtTen;
                Button btnDestroy;
                Button btnAdd;
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
                        chiDao = new chiDao(context);
                        Double name = Double.valueOf(edtTen.getText().toString().trim());
                        thuDao=new thuDao(context);
                        double totalmoney=thuDao.sumThu();

                        if (name.equals("")) {
                            Toast.makeText(context, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        }else if(name>totalmoney){
                            Toast.makeText(context, "Vui lòng nhập số tiền chi nhỏ hơn tổng thu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            if (name<0){
                                Toast.makeText(context, "Vui lòng tiền lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (Exception e){
                            Toast.makeText(context, "Vui lòng nhập tiền là số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                            chi.SoTienChi = name;
                            String res = String.valueOf(chiDao.updateChi(chi));
                            thuHolder.tvName.setText(name+"");
                            alertDialog.dismiss();

                            if (!res.equals(name)) {
                                Toast.makeText(context, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                            }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return chiList.size();
    }
}