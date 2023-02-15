package com.calleridname.calldetailcallhistory.numberlocation_activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static Cursor cursor;
    private static DataBaseHelper instance;
    private static SQLiteDatabase sqliteDb;
    private Context context;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DataBaseHelper(Context context2, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context2, str, cursorFactory, i);
        this.context = context2;
    }

    private static void initialize(Context context2, String str) {
        if (instance == null) {
            if (!checkDatabase(context2, str)) {
                try {
                    copyDataBase(context2, str);
                } catch (IOException unused) {
                    PrintStream printStream = System.out;
                    printStream.println(str + " does not exists ");
                }
            }
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context2, str, (SQLiteDatabase.CursorFactory) null, 1);
            instance = dataBaseHelper;
            sqliteDb = dataBaseHelper.getWritableDatabase();
            PrintStream printStream2 = System.out;
            printStream2.println("instance of  " + str + " created ");
        }
    }

    public static final DataBaseHelper getInstance(Context context2, String str) {
        initialize(context2, str);
        return instance;
    }

    public SQLiteDatabase getDatabase() {
        return sqliteDb;
    }

    private static void copyDataBase(Context context2, String str) throws IOException {
        InputStream open = context2.getAssets().open(str);
        String databasePath = getDatabasePath(context2, str);
        File file = new File("/data/data/" + context2.getPackageName() + "/databases/");
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(databasePath);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
                PrintStream printStream = System.out;
                printStream.println(str + " copied");
                return;
            }
        }
    }

    public static boolean checkDatabase(Context context2, String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(getDatabasePath(context2, str), (SQLiteDatabase.CursorFactory) null, 1);
            sQLiteDatabase.close();
        } catch (SQLiteException unused) {
            PrintStream printStream = System.out;
            printStream.println(str + " does not exists");
        }
        if (sQLiteDatabase != null) {
            return true;
        }
        return false;
    }

    private static String getDatabasePath(Context context2, String str) {
        return "/data/data/" + context2.getPackageName() + "/databases/" + str;
    }

    public static Cursor rawQuery(String str) {
        try {
            if (sqliteDb.isOpen()) {
                sqliteDb.close();
            }
            SQLiteDatabase writableDatabase = instance.getWritableDatabase();
            sqliteDb = writableDatabase;
            cursor = null;
            cursor = writableDatabase.rawQuery(str, (String[]) null);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("DB ERROR  " + e.getMessage());
            e.printStackTrace();
        }
        return cursor;
    }

    public static void execute(String str) {
        try {
            if (sqliteDb.isOpen()) {
                sqliteDb.close();
            }
            SQLiteDatabase writableDatabase = instance.getWritableDatabase();
            sqliteDb = writableDatabase;
            writableDatabase.execSQL(str);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("DB ERROR  " + e.getMessage());
            e.printStackTrace();
        }
    }
}
