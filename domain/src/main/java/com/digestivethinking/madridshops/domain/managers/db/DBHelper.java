package com.digestivethinking.madridshops.domain.managers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_FILENAME = "madridshops.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory NO_CURSOR_FACTORY = null;
    public static final long INVALID_ID = -1;

    public DBHelper(Context context) {
        this(context, DATABASE_FILENAME, NO_CURSOR_FACTORY, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String script : DBConstants.CREATE_DATABASE_SCRIPTS) {
            db.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);


        db.setForeignKeyConstraintsEnabled(true); //API level > 16

        //db.execSQL("PRAGMA foreign_keys = ON"); API level <= 16

    }

    // utility methods

    public static int booleanToInt(boolean b) {
        return b ? 1 : 0;
    }

    public static boolean intToBoolean(int i) {
        return i != 0;
    }


}
