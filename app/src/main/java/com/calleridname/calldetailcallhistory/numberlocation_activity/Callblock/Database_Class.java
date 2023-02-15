package com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import java.util.ArrayList;

public class Database_Class {
    private static final String DATABASE_NAME = "numberDB";
    private static final String DATABASE_TABLE = "numberTable";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_CALL = "_call";
    public static final String KEY_MSG = "_msg";
    public static final String KEY_NUM = "_num";
    private final Context BaseContext;
    private DBHelper Helper;
    private SQLiteDatabase mainDB;
    public ArrayList<Bundle> result;

    public String getDbTableName() {
        return DATABASE_TABLE;
    }

    public String[] columnName() {
        return new String[]{KEY_NUM, KEY_MSG, KEY_CALL};
    }

    public static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, Database_Class.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE numberTable (_num TEXT, _msg BOOLEAN, _call BOOLEAN);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS numberTable");
            onCreate(sQLiteDatabase);
        }
    }

    public Database_Class(Context context) throws SQLiteException {
        this.BaseContext = context;
    }

    public Database_Class open() throws SQLiteException {
        DBHelper dBHelper = new DBHelper(this.BaseContext);
        this.Helper = dBHelper;
        this.mainDB = dBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.Helper.close();
    }

    public void createEntry(String str, boolean z, boolean z2) {
        String replaceAll = str.replaceAll("\\s+", "");
        System.out.println(z2);
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NUM, replaceAll);
        contentValues.put(KEY_MSG, Boolean.valueOf(z));
        contentValues.put(KEY_CALL, Boolean.valueOf(z2));
        this.mainDB.insert(DATABASE_TABLE, (String) null, contentValues);
    }

    public Cursor getData() {
        return this.mainDB.query(DATABASE_TABLE, new String[]{KEY_NUM, KEY_MSG, KEY_CALL}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
    }

    public void deleteAll() {
        this.mainDB.delete(DATABASE_TABLE, (String) null, (String[]) null);
    }

    public void DeleteData(String str) {
        String replaceAll = str.replaceAll("\\s+", "");
        SQLiteDatabase sQLiteDatabase = this.mainDB;
        sQLiteDatabase.delete(DATABASE_TABLE, "_num=" + replaceAll, (String[]) null);
    }

    public void EditMSG(String str, Boolean bool) {
        String replaceAll = str.replaceAll("\\s+", "");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MSG, bool);
        SQLiteDatabase sQLiteDatabase = this.mainDB;
        sQLiteDatabase.update(DATABASE_TABLE, contentValues, "_num=" + replaceAll, (String[]) null);
    }

    public void EditCALL(String str, Boolean bool) {
        String replaceAll = str.replaceAll("\\s+", "");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CALL, bool);
        SQLiteDatabase sQLiteDatabase = this.mainDB;
        sQLiteDatabase.update(DATABASE_TABLE, contentValues, "_num=" + replaceAll, (String[]) null);
    }
}
