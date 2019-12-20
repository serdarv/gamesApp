package com.vladimir.gamesapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBInit extends SQLiteOpenHelper {

    public DBInit(@Nullable Context context) {
        super(context, DBConst.DB_NAME, null, DBConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_GAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_USER =
            "CREATE TABLE IF NOT EXISTS " + DBConst.USER_TABLE_NAME + " (" +
                    DBConst.USER_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DBConst.USER_COLUMN_FIRST_NAME + " TEXT," +
                    DBConst.USER_COLUMN_LAST_NAME + " TEXT," +
                    DBConst.USER_COLUMN_EMAIL + " TEXT," +
                    DBConst.USER_COLUMN_PASSWORD + " TEXT," +
                    DBConst.USER_COLUMN_AGE + " TEXT)";

    private static final String SQL_CREATE_GAME =
            "CREATE TABLE IF NOT EXISTS " + DBConst.GAME_TABLE_NAME + " (" +
                    DBConst.GAME_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DBConst.GAME_COLUMN_NAME+ " TEXT," +
                    DBConst.GAME_COLUMN_STORYLINE+ " TEXT," +
                    DBConst.GAME_COLUMN_SUMMARY + " TEXT," +
                    DBConst.GAME_COLUMN_SCREENSHOTS + " TEXT," +
                    DBConst.GAME_COLUMN_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBConst.USER_TABLE_NAME;

}
