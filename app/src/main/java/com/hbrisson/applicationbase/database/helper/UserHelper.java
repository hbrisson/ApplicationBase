package com.hbrisson.applicationbase.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hbrisson.applicationbase.database.DataBaseHelper;

/**
 * Created by hbrisson on 02/02/2015.
 */
public class UserHelper extends DataBaseHelper {

    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MAIL = "mail";
    public static final String COLUMN_PHOTO = "photo";



    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null ," + COLUMN_SURNAME
            + " text not null ," + COLUMN_MAIL
            + " text not null ," + COLUMN_PASSWORD
            + " text not null ," + COLUMN_PHOTO + " text );";

    public UserHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
