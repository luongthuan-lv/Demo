package vn.edu.poly.demo.adapter_one;

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

import java.util.List;

import vn.edu.poly.demo.R;
import vn.edu.poly.demo.child_screen_one.SoTienThuActivity;
import vn.edu.poly.demo.database_one.thuDao;
import vn.edu.poly.demo.declare.Thu;

public class SoTienThuAdapter extends RecyclerView.Adapter<ThuHolder> {
    public Context context;
    public List<Thu> thuList;
    public vn.edu.poly.demo.database_one.thuDao thuDao;
    public AlertDialog alertDialog;

    public SoTienThuAdapter(Context context, List<Thu> thuList) {
        this.context = context;
        this.thuList = thuList;
        this.thuDao = new thuDao(context);
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
        final Thu thu = thuList.get(position);
        thuHolder.tvStt.setText(position + 1 + "");
        thuHolder.tvName.setText(thu.SoTienThu + "");

        thuHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuDao.deleteThu(thu);
                thuList.remove(position);
                notifyDataSetChanged();
            }
        });


        thuHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
                        thuDao = new thuDao(context);
                        Double ten = Double.valueOf(edtTen.getText().toString().trim());

                        if (ten.equals("")) {
                            Toast.makeText(context, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        }

                        try {
                            if (ten<0){
                                Toast.makeText(context, "Vui lòng tiền lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (Exception e){
                            Toast.makeText(context, "Vui lòng nhập tiền là số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                            thu.SoTienThu = ten;
                            String res = String.valueOf(thuDao.updateThu(thu));
                            thuHolder.tvName.setText(ten + "");
                            alertDialog.dismiss();

                            if (!res.equals(ten)) {
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
        return thuList.size();
    }
}
