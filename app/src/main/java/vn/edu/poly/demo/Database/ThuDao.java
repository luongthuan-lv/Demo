package vn.edu.poly.demo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.demo.Declare.Thu;

import static vn.edu.poly.demo.Database.ThuRender.COL_KHOANTHU;
import static vn.edu.poly.demo.Database.ThuRender.COL_NGAYTHU;
import static vn.edu.poly.demo.Database.ThuRender.COL_LOAITHU;
import static vn.edu.poly.demo.Database.ThuRender.COL_SOTIENTHU;
import static vn.edu.poly.demo.Database.ThuRender.TABLE_NAME;

public class ThuDao {

    public ThuRender thuRender;

    public ThuDao(Context context) {
        thuRender = new ThuRender(context);
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

    public long updateThu(String thu) {
        SQLiteDatabase sqLiteDatabase=thuRender.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_KHOANTHU, thu.KhoanThu);
        contentValues.put(COL_LOAITHU, thu.LoaiThu);
        contentValues.put(COL_NGAYTHU, thu.NgayThu);
        contentValues.put(COL_SOTIENTHU, thu.SoTienThu);
        return sqLiteDatabase.update(TABLE_NAME, contentValues, COL_KHOANTHU + "=?", new String[]{thu.Khoanthu});
    }

    public long deleteThu(String thu) {
        SQLiteDatabase sqLiteDatabase = thuRender.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COL_KHOANTHU + "=?", new String[]{thu.Khoanthu});
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
                thu.KhoanThu = cursor.getString(cursor.getColumnIndex(COL_KHOANTHU));
                thu.LoaiThu = cursor.getString(cursor.getColumnIndex(COL_LOAITHU));
                thu.NgayThu = cursor.getString(cursor.getColumnIndex(COL_NGAYTHU));
                thu.SoTienThu = cursor.getFloat(cursor.getColumnIndex(COL_SOTIENTHU));

                thuList.add(thu);
                cursor.moveToNext();


            }
            cursor.close();
        }
        return thuList;
    }
}
