package com.hbrisson.applicationbase.database.model;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hbrisson.applicationbase.database.helper.KeyHelper;


/**
 * Created by hbrisson on 04/02/2015.
 */
public class KeyDAO {

    private static SQLiteDatabase database;
    private static KeyHelper dbHelper;
    private static String[] allcolumns = {KeyHelper.KEY_TABLE_ID, KeyHelper.KEY_TABLE_KEY, KeyHelper.KEY_TABLE_VALUE};

    /**
     * Open database connexion.
     */

    public static void open() {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connexion.
     */
    public static void close() {
        dbHelper.close();
    }

    /**
     * This function tests if a key exists in the table.
     *
     * @param key the key we are looking for
     * @return true if the key exists, false otherwise
     */
    private static boolean keyExists(String key) {
        return (getValue(key) != null);
    }

    /**
     * This function fetches a value in writableDatabase and returns it.
     *
     * @param key the key of the value we are looking for
     * @return null if nothing exists in base, the value of the key otherwise
     */
    public static String getValue(String key) {
        open();
        Cursor cursor = database.query(KeyHelper.KEY_TABLE, null, KeyHelper.KEY_TABLE_KEY + "=?", new String[]{key}, null, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            close();
            return null;
        } else {
            cursor.moveToFirst();
            KeyValue kv = cursorToKeyValue(cursor);
            cursor.close();
            close();
            return kv.mValue;
        }
    }

    /**
     * This function fetches a value in writableDatabase and returns it
     *
     * @param key the key of the value we are looking for
     * @return null if nothing exists in base, the value of the key otherwise
     */
    public static boolean setValue(String key, String value) {
        open();
        updateValue(key, value);
        close();
        return true;
    }

    /**
     * Acts like an instert or update this value with this key
     *
     * @param key
     * @param value
     * @return the id of the element inserted
     */
    private static long updateValue(String key, String value) {
        // if the key exists, just update it
        if (keyExists(key)) {
            return database.update(KeyHelper.KEY_TABLE, getContentValues(key, value), KeyHelper.KEY_TABLE_KEY + "=?", new String[]{key});
        }
        // if the key does not exist, insert it in base
        else {
            return database.insert(KeyHelper.KEY_TABLE, null, getContentValues(key, value));
        }
    }

    /**
     * This function creates an instance of KeyValue from a cursor in writableDatabase
     *
     * @param cursor
     * @return a KeyValue
     */
    private static KeyValue cursorToKeyValue(Cursor cursor) {
        // Id is not used yet, but maybe later (who knows ?)
        return new KeyValue(cursor.getString(KeyHelper.KEY_TABLE_NUM_COL_KEY), String.valueOf(cursor.getString(KeyHelper.KEY_TABLE_NUM_COL_VALUE)));
    }

    /**
     * This function generates a ContentValues (HashMap) with a key value pair
     *
     * @param key
     * @param value
     * @return a ContentValue with a value associated to a key
     */
    private static ContentValues getContentValues(String key, String value) {
        ContentValues urlValues = new ContentValues();
        urlValues.put(KeyHelper.KEY_TABLE_KEY, key);
        urlValues.put(KeyHelper.KEY_TABLE_VALUE, value);
        return urlValues;
    }


    /**
     * @author qklein This class is a basic model of what is stored in our
     *         table, an associative array with a key and a value
     */
    private static class KeyValue {

        //@SuppressWarnings("unused")
        String mKey;
        String mValue;

        public KeyValue(String key, String value) {
            mKey = key;
            mValue = value;
        }
    }
}
