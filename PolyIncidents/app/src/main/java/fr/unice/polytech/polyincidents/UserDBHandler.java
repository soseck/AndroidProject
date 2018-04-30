package fr.unice.polytech.polyincidents;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by user on 30/04/2018.
 */

public class UserDBHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "";
    public static final String USER_TABLE = "";
    public static final String COLUMN_NAME = "";
    public static final String COLUMN_ID = "";

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

