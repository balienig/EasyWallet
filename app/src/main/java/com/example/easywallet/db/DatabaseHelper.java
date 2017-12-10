package com.example.easywallet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by balie on 10-Dec-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 8;

    public static final String TABLE_NAME = "number";
    public static final String COL_ID = "_id";
    public static final String COL_STR = "textStr";
    public static final String COL_NUM = "NumMoney";
    public static final String COL_PIC = "picture";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            +COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_STR + " TEXT, "
            +COL_NUM + " TEXT, "
            +COL_PIC + " TEXT)";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }
    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_STR,"คุณพ่อให้เงิน");
        cv.put(COL_NUM,"8000");
        cv.put(COL_PIC,"ic_income.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_STR,"จ่ายค่าหอ");
        cv.put(COL_NUM,"2500");
        cv.put(COL_PIC,"ic_expense.png");
        db.insert(TABLE_NAME, null, cv);


        cv = new ContentValues();
        cv.put(COL_STR,"ซื้อล็อตเตอรี่ 1 ชุด");
        cv.put(COL_NUM,"700");
        cv.put(COL_PIC,"ic_expense.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_STR,"ถูกล็อตเตอรี่รางวัลที่ 1");
        cv.put(COL_NUM,"30000000");
        cv.put(COL_PIC,"ic_income.png");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
