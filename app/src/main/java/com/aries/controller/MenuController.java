package com.aries.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aries.util.Util;

import java.util.ArrayList;

public class MenuController extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME="aries";
    private static final int DATA_BASE_VERSION= Util.DATA_BASE_VERSION_ZEUS;

    public MenuController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        //	getWritableDatabase().setLocale(new Locale("es", "BO"));
    }

    public ArrayList<Integer> getListMenuByCargo(int cod_cargo) {

        String selectQuery = "select cod_menuitem FROM menuitem_cargo WHERE cod_cargo = " + cod_cargo;
        ArrayList<Integer> codigos = new ArrayList<>();
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cod = c.getInt(c.getColumnIndex("cod_menuitem"));

                codigos.add(cod);
            } while (c.moveToNext());
        }
        return codigos;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
