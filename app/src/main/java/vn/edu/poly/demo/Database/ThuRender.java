package vn.edu.poly.demo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThuRender extends SQLiteOpenHelper {

    public static final String TABLE_NAME="thu";
    public static final String COL_KHOANTHU="khoanthu";
    public static final String COL_LOAITHU="loaithu";
    public static final String COL_NGAYTHU="ngaythu";
    public static final String COL_SOTIENTHU="sotienthu";
    public ThuRender(Context context) {
        super(context, "thu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + " ("+COL_KHOANTHU+" TEXT PRIMARY KEY,"+COL_LOAITHU+" TEXT,"+COL_NGAYTHU+" TEXT,"+COL_SOTIENTHU+" FLOAT)";
       db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
