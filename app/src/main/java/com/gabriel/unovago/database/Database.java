package com.gabriel.unovago.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "RetroViagens", null, 6);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE cliente_autenticado (id INTEGER PRIMARY KEY, createdAt TEXT ,updatedAt TEXT ,nome TEXT ,email TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS cliente_autenticado";
        db.execSQL(sql);
        onCreate(db);
    }
}
