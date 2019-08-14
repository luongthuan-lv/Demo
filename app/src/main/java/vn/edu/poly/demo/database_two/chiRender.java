package vn.edu.poly.demo.database_two;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class chiRender extends SQLiteOpenHelper {

    public static final String TABLE_NAME1="chi";
    public static final String COL_STTS="stts";
    public static final String COL_KHOANCHI="khoanchi";
    public static final String COL_LOAICHI="loaichi";
    public static final String COL_NGAYCHI="ngaychi";
    public static final String COL_SOTIENCHI="sotienchi";
    public static final String CREATE_TABLE ="CREATE TABLE " + TABLE_NAME1 + " ("+COL_STTS+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_KHOANCHI+" TEXT,"+COL_LOAICHI+" TEXT,"+COL_NGAYCHI+" TEXT,"+COL_SOTIENCHI+" DOUBLE)";
    public chiRender(Context context) {
        super(context, "chi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
