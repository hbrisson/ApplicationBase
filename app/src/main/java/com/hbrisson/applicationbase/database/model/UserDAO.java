package com.hbrisson.applicationbase.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.hbrisson.applicationbase.entites.User;
import com.hbrisson.applicationbase.database.helper.UserHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbrisson on 02/02/2015.
 * Database Class for User.
 */

public class UserDAO {

    private SQLiteDatabase database;
    private UserHelper dbHelper;
    private String[] allcolumns = {UserHelper.COLUMN_ID, UserHelper.COLUMN_NAME, UserHelper.COLUMN_SURNAME, UserHelper.COLUMN_MAIL, UserHelper.COLUMN_PASSWORD, UserHelper.COLUMN_PHOTO};

    /**
     * Constructor.
     *
     * @param context
     */
    public UserDAO(Context context) {
        dbHelper = new UserHelper(context);
    }

    /**
     * Open database connexion.
     */
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connexion.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Insert a user in database.
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        try {
            database.insert(UserHelper.TABLE_USER, null, values(user));
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Insert a user in database and return it whith the good id.
     *
     * @param user
     * @return
     */
    public User insertGetUser(User user) {
        long insertId = database.insert(UserHelper.TABLE_USER, null, values(user));
        Cursor cursor = database.query(UserHelper.TABLE_USER, allcolumns, UserHelper.COLUMN_ID + "=" + insertId, null, null, null, null);
        cursor.moveToFirst();
        return cursoToUser(cursor);
    }

    /**
     * Return all users in database.
     *
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(UserHelper.TABLE_USER, allcolumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(cursoToUser(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    /**
     * Update a user with iduser.
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        int id = user.getmId();
        try {
            database.update(UserHelper.TABLE_USER, values(user), UserHelper.COLUMN_ID + "=" + id, null);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Delete a user with iduser.
     *
     * @param user
     * @return
     */
    public boolean deleteUser(User user) {
        int id = user.getmId();
        try {
            database.delete(UserHelper.TABLE_USER, UserHelper.COLUMN_ID + "=" + id, null);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * This method allows to contain all user 's values for insert in the database.
     *
     * @param user
     * @return
     */
    private ContentValues values(User user) {
        ContentValues values = new ContentValues();
        values.put(UserHelper.COLUMN_NAME, user.getmName());
        values.put(UserHelper.COLUMN_SURNAME, user.getmSurname());
        values.put(UserHelper.COLUMN_MAIL, user.getmMail());
        values.put(UserHelper.COLUMN_PASSWORD, user.getmPassword());
        values.put(UserHelper.COLUMN_PHOTO, user.getmPhoto());

        return values;
    }

    /**
     * This method allows to read request's result from user table.
     *
     * @param cursor
     * @return
     */
    private User cursoToUser(Cursor cursor) {
        User user = new User();

        user.setmId(cursor.getInt(0));
        user.setmName(cursor.getString(1));
        user.setmSurname(cursor.getString(2));
        user.setmMail(cursor.getString(3));
        user.setmPassword(cursor.getString(4));
        user.setmPhoto(cursor.getString(5));

        return user;
    }
}
