package com.hbrisson.applicationbase.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hbrisson.applicationbase.database.DataBaseHelper;

/**
 * Created by hbrisson on 04/02/2015.
 */
public class KeyHelper extends DataBaseHelper {
    public static final String KEY_TABLE = "key";
    public static final String KEY_TABLE_ID = "_id";
    public static final int KEY_TABLE_NUM_COL_ID = 0;
    public static final String KEY_TABLE_KEY = "key";
    public static final int KEY_TABLE_NUM_COL_KEY = 1;
    public static final String KEY_TABLE_VALUE = "value";
    public static final int KEY_TABLE_NUM_COL_VALUE = 2;
    private static final String CREATE_KEY_TABLE = "CREATE TABLE IF NOT EXISTS "
            + KEY_TABLE + "("
            + KEY_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TABLE_KEY + " TEXT, "
            + KEY_TABLE_VALUE + " TEXT"
            + ");";

    public KeyHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_KEY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE);
        onCreate(db);
    }
}
