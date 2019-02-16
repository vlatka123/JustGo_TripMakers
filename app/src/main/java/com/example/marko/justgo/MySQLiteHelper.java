package com.example.marko.justgo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table trips"
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "country TEXT NOT NULL,"
            + "city TEXT NOT NULL,"
            + "from_date TEXT NOT NULL,"
            + "to_date TEXT NOT NULL);";

    public MySQLiteHelper(Context context) {
        super(context, "mytripdatabase.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version"
                + oldVersion + "to" + newVersion + ",which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
