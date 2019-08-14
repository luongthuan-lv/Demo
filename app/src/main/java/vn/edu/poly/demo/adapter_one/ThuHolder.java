package vn.edu.poly.demo.adapter_one;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.poly.demo.R;

public class ThuHolder extends RecyclerView.ViewHolder {
public TextView tvStt,tvName;
public Button btnEdit,btnDelete;
    public ThuHolder(@NonNull View itemView) {
        super(itemView);

        tvStt=itemView.findViewById(R.id.tvStt);
        tvName=itemView.findViewById(R.id.tvName);
        btnEdit=itemView.findViewById(R.id.btnEdit);
        btnDelete=itemView.findViewById(R.id.btnDelete);
    }
}
