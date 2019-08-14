package vn.edu.poly.demo.database_one;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


import vn.edu.poly.demo.declare.Thu;

import static vn.edu.poly.demo.database_one.thuRender.COL_KHOANTHU;
import static vn.edu.poly.demo.database_one.thuRender.COL_NGAYTHU;
import static vn.edu.poly.demo.database_one.thuRender.COL_LOAITHU;
import static vn.edu.poly.demo.database_one.thuRender.COL_SOTIENTHU;
import static vn.edu.poly.demo.database_one.thuRender.COL_STT;
import static vn.edu.poly.demo.database_one.thuRender.TABLE_NAME;

public class thuDao {

    public vn.edu.poly.demo.database_one.thuRender thuRender;

    public thuDao(Context context) {
        thuRender = new thuRender(context);
    }

    public double sumThu(){
        SQLiteDatabase sqLiteDatabase = thuRender.getReadableDatabase();
        List<Thu> thuList = new ArrayList<>();
        String sumT="SELECT sum("+COL_SOTIENTHU+") FROM "+ TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sumT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            double kq = cursor.getDouble(0);


            cursor.close();

            return  kq;
        }
        return 0;
    }

    public long insertThu(Thu thu) {
        SQLiteDatabase sqLiteDatabase = thuRender.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_KHOANTHU, thu.KhoanThu);
        contentValues.put(COL_LOAITHU, thu.LoaiThu);
        contentValues.put(COL_NGAYTHU, thu.NgayThu);
        contentValues.put(COL_SOTIENTHU, thu.SoTienThu);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public long updateThu(Thu thu) {
        SQLiteDatabase sqLiteDatabase = thuRender.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STT, thu.Stt);
        contentValues.put(COL_KHOANTHU, thu.getKhoanThu());
        contentValues.put(COL_LOAITHU, thu.getLoaiThu());
        contentValues.put(COL_NGAYTHU, thu.getNgayThu());
        contentValues.put(COL_SOTIENTHU, thu.getSoTienThu());
        String[] selectionArgs={String.valueOf(thu.getStt())};
        return sqLiteDatabase.update(TABLE_NAME, contentValues, COL_STT + "=?", selectionArgs);

    }

    public long deleteThu(Thu thu) {
        SQLiteDatabase sqLiteDatabase = thuRender.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COL_STT + "=?", new String[]{String.valueOf(thu.Stt)});

    }

    public List<Thu> getAllThu() {
        SQLiteDatabase sqLiteDatabase = thuRender.getReadableDatabase();
        List<Thu> thuList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + TABLE_NAME;


        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Thu thu = new Thu();
                thu.Stt = cursor.getInt(cursor.getColumnIndex(COL_STT));
                thu.KhoanThu = cursor.getString(cursor.getColumnIndex(COL_KHOANTHU));
                thu.LoaiThu = cursor.getString(cursor.getColumnIndex(COL_LOAITHU));
                thu.NgayThu = cursor.getString(cursor.getColumnIndex(COL_NGAYTHU));
                thu.SoTienThu = cursor.getDouble(cursor.getColumnIndex(COL_SOTIENTHU));

                thuList.add(thu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return thuList;
    }


    public void Close() {
        thuRender.close();
    }
}
