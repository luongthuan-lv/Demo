package vn.edu.poly.demo.database_two;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.demo.declare.Chi;

import static vn.edu.poly.demo.database_two.chiRender.COL_KHOANCHI;
import static vn.edu.poly.demo.database_two.chiRender.COL_LOAICHI;
import static vn.edu.poly.demo.database_two.chiRender.COL_NGAYCHI;
import static vn.edu.poly.demo.database_two.chiRender.COL_SOTIENCHI;
import static vn.edu.poly.demo.database_two.chiRender.COL_STTS;
import static vn.edu.poly.demo.database_two.chiRender.TABLE_NAME1;

public class chiDao {
    public vn.edu.poly.demo.database_two.chiRender chiRender;

    public chiDao(Context context) {
        chiRender = new chiRender(context);
    }

    public double sumChi(){
        SQLiteDatabase sqLiteDatabase = chiRender.getReadableDatabase();
        List<Chi> chiList = new ArrayList<>();
        String sumT="SELECT sum("+COL_SOTIENCHI+") FROM "+ TABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(sumT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            double kq = cursor.getDouble(0);


            cursor.close();

            return  kq;
        }
        return 0;
    }

    public long insertChi(Chi chi) {
        SQLiteDatabase sqLiteDatabase = chiRender.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_KHOANCHI, chi.KhoanChi);
        contentValues.put(COL_LOAICHI, chi.LoaiChi);
        contentValues.put(COL_NGAYCHI, chi.NgayChi);
        contentValues.put(COL_SOTIENCHI, chi.SoTienChi);
        return sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
    }

    public long updateChi(Chi chi) {
        SQLiteDatabase sqLiteDatabase = chiRender.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STTS, chi.Stt);
        contentValues.put(COL_KHOANCHI, chi.KhoanChi);
        contentValues.put(COL_LOAICHI, chi.LoaiChi);
        contentValues.put(COL_NGAYCHI, chi.NgayChi);
        contentValues.put(COL_SOTIENCHI, chi.SoTienChi);
        String[] selectionArgs={String.valueOf(chi.Stt)};
        return sqLiteDatabase.update(TABLE_NAME1, contentValues, COL_STTS + "=?",selectionArgs);
    }

    public long deleteChi(Chi chi) {
        SQLiteDatabase sqLiteDatabase = chiRender.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME1, COL_STTS + "=?", new String[]{String.valueOf(chi.Stt)});

    }

    public List<Chi> getAllChi() {
        SQLiteDatabase sqLiteDatabase = chiRender.getReadableDatabase();
        List<Chi> chiList = new ArrayList<>();
        String SELECTC = " SELECT * FROM " + TABLE_NAME1;


        Cursor cursor = sqLiteDatabase.rawQuery(SELECTC, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Chi chi = new Chi();
                chi.Stt = cursor.getInt(cursor.getColumnIndex(COL_STTS));
                chi.KhoanChi = cursor.getString(cursor.getColumnIndex(COL_KHOANCHI));
                chi.LoaiChi = cursor.getString(cursor.getColumnIndex(COL_LOAICHI));
                chi.NgayChi = cursor.getString(cursor.getColumnIndex(COL_NGAYCHI));
                chi.SoTienChi = cursor.getDouble(cursor.getColumnIndex(COL_SOTIENCHI));

                chiList.add(chi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return chiList;
    }

    public void Close() {
        chiRender.close();
    }
}
