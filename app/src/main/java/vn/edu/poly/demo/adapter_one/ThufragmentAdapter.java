package vn.edu.poly.demo.adapter_one;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.edu.poly.demo.fragment.KhoanThuFragment;
import vn.edu.poly.demo.fragment.LoaiThuFragment;

public class ThufragmentAdapter extends FragmentStatePagerAdapter {
    private String listTab[]={"Khoản Thu","Loại Thu"};
    private KhoanThuFragment khoanThuFragment;
    private LoaiThuFragment loaiThuFragment;
    public ThufragmentAdapter(FragmentManager fm) {
        super(fm);
        khoanThuFragment=new KhoanThuFragment();
        loaiThuFragment=new LoaiThuFragment();
    }

    @Override
    public Fragment getItem(int position) {
//        ThuFragment thuFragment=new ThuFragment();
//        Bundle bundle=new Bundle();
//        bundle.putString("Thu",position+"");
//        thuFragment.setArguments(bundle);

        if (position==0){
            return khoanThuFragment;
        } else if (position==1){
            return loaiThuFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
