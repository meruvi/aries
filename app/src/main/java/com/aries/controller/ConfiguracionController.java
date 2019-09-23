package com.aries.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aries.util.Util;

import java.util.ArrayList;

public class ConfiguracionController extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = Util.DATA_BASE_NAME;
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    Context context;
//
//    // private static final String
//    // CREATE_TABLE_CLIENTE="create table clientes(cod_cliente integer primary key ,nombre_cliente text,cod_area_empresa numeric,nit_cliente text)";
    public ConfiguracionController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context=context;
        //
    }
//

//
//    public void crearColumnaTabla(String nombreColumna, String nombreTabla, String tipoColumna) {
//        if (!verificarColumnTabla(nombreColumna, nombreTabla)) {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String sqlColumn = " ALTER TABLE " + nombreTabla + " ADD COLUMN " + nombreColumna + " " + tipoColumna + " ";
//            db.execSQL(sqlColumn);
//            db.close();
//        }
//    }
//    //
//    public boolean verificarColumnTabla(String nombreColumnaVerificar, String nombreTabla) {
//        String selectQuery = "PRAGMA table_info(" + nombreTabla + ")";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                String nombreColumna = c.getString(c.getColumnIndex("name"));
//                if (nombreColumnaVerificar.equals(nombreColumna)) {
//                    return true;
//                }
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return false;
//    }
//
    public long actualizarConfiguracion( String ipExterno,String ipRegional,int codAreaEmpresa) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("configuracion_aplicacion_ip", null, null);

        long cantidadInsertada = 0;

        ContentValues values = new ContentValues();
        values.put("ip_externo", ipExterno);
        values.put("ip_regional", ipRegional);
        values.put("cod_area_empresa", codAreaEmpresa);
        cantidadInsertada = db.insert("configuracion_aplicacion_ip", null,values);

        return cantidadInsertada;
    }
//
    public ArrayList<String> getIpConfiguracion() {

        String selectQuery = "SELECT ip_externo, ip_regional, cod_area_empresa FROM configuracion_aplicacion_ip ";
        ArrayList<String> data = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                data.add(0, c.getString(c.getColumnIndex("ip_externo")));
                data.add(1, c.getString(c.getColumnIndex("ip_regional")));
                data.add(2, c.getString(c.getColumnIndex("cod_area_empresa")));

            } while (c.moveToNext());
            c.close();
        }
        db.close();

        return data;
    }
//
//
    public String[] getModificacion() {

        String selectQuery = "SELECT ip_externo, ip_regional, cod_area_empresa FROM configuracion_aplicacion_ip";
        String[] data= {"","",""};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                data[0]=c.getString(c.getColumnIndex("ip_externo"));
                data[1]=c.getString(c.getColumnIndex("ip_regional"));
                data[2]=c.getString(c.getColumnIndex("cod_area_empresa"));
            } while (c.moveToNext());
        }
        db.close();

        return data;
    }
//
//
    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL(CREATE_TABLE_CLIENTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }
//
//    public void closeDB() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db != null && db.isOpen())
//            db.close();
//    }

}
