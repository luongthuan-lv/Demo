package vn.edu.poly.demo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.edu.poly.demo.Database.ThuDao;
import vn.edu.poly.demo.Declare.Thu;
import vn.edu.poly.demo.R;

public class ThuAdapter extends RecyclerView.Adapter<ThuHolder> {
    public Context context;
    public List<Thu> thuList;
    public ThuDao thuDao;

    public ThuAdapter(Context context, List<Thu> thuList) {
        this.context = context;
        this.thuList = thuList;
    }

    @NonNull
    @Override
    public ThuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.thu, viewGroup, false);
        ThuHolder thuHolder = new ThuHolder(view);

        return thuHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuHolder thuHolder, final int position) {
        final Thu thu = thuList.get(position);
        thuHolder.tvStt.setText(position + 1 + "");
        thuHolder.tvName.setText(thu.KhoanThu);

        thuHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuDao.deleteThu(thu.KhoanThu);
                thuList.remove(position);
                notifyDataSetChanged();
            }
        });

//        thuHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thuDao.updateThu(thu.KhoanThu);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return thuList.size();
    }
}
