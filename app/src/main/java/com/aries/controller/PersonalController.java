package com.aries.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aries.entity.Personal;
import com.aries.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonalController extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME="aries";
    private static final int DATA_BASE_VERSION= Util.DATA_BASE_VERSION_ZEUS;
//    SimpleDateFormat f=new SimpleDateFormat("yyy-MM-dd");
//
//    //com.google.
//
//    public boolean verificarColumnTabla(String nombreColumnaVerificar,String nombreTabla){
//        String selectQuery = "PRAGMA table_info("+nombreTabla+")";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//                String nombreColumna =c.getString(c.getColumnIndex("name")  ) ;
//
//                if( nombreColumnaVerificar.equals( nombreColumna  ) ){
//                    Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "EXISTE");
//                    return true;
//                }
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "NO EXISTE");
//        return false;
//    }
//    public void crearColumnaTabla(String nombreColumna,String nombreTabla,String tipoColumna){
//        if( !verificarColumnTabla(nombreColumna,nombreTabla) ){
//            SQLiteDatabase db = this.getReadableDatabase();
//            String sqlColumn=" ALTER TABLE "+nombreTabla+" ADD COLUMN "+nombreColumna+" "+tipoColumna+" ";
//            db.execSQL(sqlColumn);
//            Log.i("CREANDO  TABLA: "+nombreTabla+" COLUMNA :"+nombreColumna, sqlColumn);
//            db.close();
//
//        }
//
//    }
//
//
//
    public PersonalController(Context context) {


        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        //	getWritableDatabase().setLocale(new Locale("es", "BO"));


    }
//
//
//    public ArrayList<CuentaCobrar> getCuentasCliente(int codCliente) {
//
//
//
//
//        ArrayList<CuentaCobrar> listado=new ArrayList<CuentaCobrar>();
//        String selectQuery = "SELECT  fecha_salidaventa,abrev_tipodoc_venta,nro_factura,  monto_total,monto_cancelado,total_cobranza,nombre_cliente,esta_enmora FROM cuentas_porcobrar where cod_cliente="+codCliente;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                CuentaCobrar bean = new CuentaCobrar();
//                try{
//
//                    bean.setFechaSalidaVenta(  f.parse( c.getString(   c.getColumnIndex("fecha_salidaventa")  ) ) );
//
//                }catch(Exception e){
//                    e.printStackTrace();
//
//                }
//                bean.setAbrevTipoDocVenta( c.getString(   c.getColumnIndex("abrev_tipodoc_venta")  )  );
//                bean.setNroFactura( c.getInt(   c.getColumnIndex("nro_factura")  )  );
//                bean.setMontoTotal( c.getDouble(   c.getColumnIndex("monto_total")  )  );
//                bean.setMontoCancelado( c.getDouble(   c.getColumnIndex("monto_cancelado")  )  );
//                bean.setNombreCliente( c.getString(   c.getColumnIndex("nombre_cliente")  )  );
//
//                bean.estaEnMora=c.getString(   c.getColumnIndex("esta_enmora")  );
//
//
//                if( c.getString(   c.getColumnIndex("esta_enmora")  ).equals("SI")  ){
//                    bean.estaEnMora="Mora";
//                }else{
//                    bean.estaEnMora="";
//                }
//
//
//                listado.add(bean);
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//
//
//	    /*
//	    String selectQuery = "SELECT  nro_factura,  monto_total,monto_cancelado,total_cobranza FROM cuentas_porcobrar where cod_cliente="+codCliente ;
//	   System.out.println(selectQuery);
//	   // Log.e(LOG, selectQuery);
//
//	    SQLiteDatabase db = this.getReadableDatabase();
//	    Cursor c = db.rawQuery(selectQuery, null);
//
//
//
//
//
//	        	CuentaCobrarBean td = new CuentaCobrarBean();
//	            td.nroFactura=c.getInt( c.getColumnIndex("nro_factura"));
//	            td.montoTotal=c.getDouble( c.getColumnIndex("monto_total"));
//	            td.montoCancelado=c.getDouble( c.getColumnIndex("monto_cancelado"));
//	            td.totalCobranza=c.getDouble( c.getColumnIndex("total_cobranza"));
//
//
//	    */
//
//
//    }
//
//    public void actualizarCuenta(Collection<CuentaCobrar> listado) {
//        SQLiteDatabase db=this.getWritableDatabase();
//
//        try{
//            db.beginTransaction();
//            db.delete("cuentas_porcobrar", null, null);
//            ContentValues values = new ContentValues();
//            for(CuentaCobrar bean:listado){
//                values.put("cod_salidaventa", bean.codSalidaVenta);
//                values.put("nro_salidaventa", bean.nroSalidaVenta);
//                values.put("cod_estado_salidaventa", bean.codEstadoSalidaVenta);
//                values.put("fecha_salidaventa", f.format(bean.fechaSalidaVenta));
//                values.put("nombre_estado_salidaventa", bean.nombreEstadoSalidaVenta);
//                values.put("cod_cliente", bean.codCliente);
//                values.put("nombre_cliente", bean.nombreCliente);
//                values.put("cod_tipodoc_venta", bean.codTipoDocVenta);
//                values.put("abrev_tipodoc_venta", bean.abrevTipoDocVenta);
//                values.put("nro_factura", bean.nroFactura);
//                values.put("monto_total", bean.montoTotal);
//                values.put("monto_cancelado", bean.montoCancelado);
//                values.put("total_cobranza", bean.totalCobranza);
//                values.put("cod_area_empresa", bean.codAreaEmpresa);
//                values.put("nombre_area_empresa", bean.nombreAreaEmpresa);
//                values.put("cod_tipocliente", bean.codTipoCliente);
//                values.put("esta_enmora", bean.estaEnMora);
//                db.insert("cuentas_porcobrar", null, values);
//            }
//            db.setTransactionSuccessful();
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//            db.close();
//
//        }
//
//
//    }
//
//    public void actualizarMontoCancelado(Collection<CuentaCobrar> listado) {
//        SQLiteDatabase db=this.getWritableDatabase();
//
//        try{
//            db.beginTransaction();
//            //	db.delete("cuentas_porcobrar", null, null);
//            //ContentValues values = new ContentValues();
//            for(CuentaCobrar bean:listado){
//					/*values.put("cod_salidaventa", bean.codSalidaVenta);
//					values.put("nro_salidaventa", bean.nroSalidaVenta);
//					values.put("cod_estado_salidaventa", bean.codEstadoSalidaVenta);
//					values.put("fecha_salidaventa", f.format(bean.fechaSalidaVenta));
//					values.put("nombre_estado_salidaventa", bean.nombreEstadoSalidaVenta);
//					values.put("cod_cliente", bean.codCliente);
//					values.put("nombre_cliente", bean.nombreCliente);
//					values.put("cod_tipodoc_venta", bean.codTipoDocVenta);
//					values.put("abrev_tipodoc_venta", bean.abrevTipoDocVenta);
//					values.put("nro_factura", bean.nroFactura);
//					values.put("monto_total", bean.montoTotal);
//					values.put("monto_cancelado", bean.montoCancelado);
//					values.put("total_cobranza", bean.totalCobranza);
//					values.put("cod_area_empresa", bean.codAreaEmpresa);
//					values.put("nombre_area_empresa", bean.nombreAreaEmpresa);
//					values.put("cod_tipocliente", bean.codTipoCliente);
//					values.put("esta_enmora", bean.estaEnMora);
//					db.insert("cuentas_porcobrar", null, values);*/
//                String sqlUpdateCuentas=" update cuentas_porcobrar set monto_cancelado=monto_cancelado+(  select COALESCE(sum(monto_cobranza) ,0)   from cobranza c,cobranza_detalle cd where cd.cod_cobranza=c.cod_cobranza and c.cod_estado_cobranza=3     and cd.cod_salidaventa="+bean.getCodSalidaVenta()+" and cd.cod_area_empresa="+bean.getCodAreaEmpresa()+"   ) where cod_salidaventa="+bean.getCodSalidaVenta()+" and cod_area_empresa="+bean.getCodAreaEmpresa();
//                Log.i("anularCobranza", sqlUpdateCuentas);
//                db.execSQL(sqlUpdateCuentas);
//
//            }
//            db.setTransactionSuccessful();
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//            db.close();
//
//        }
//
//
//    }
//
//
//
    public int actualizarPersonal(ArrayList<Personal> lista){

        SQLiteDatabase db = this.getWritableDatabase();
        int cantidadPersonal=0;
        try{
            db.beginTransaction();
            db.delete("personal", null, null);

            for(Personal personal:lista){

                ContentValues data = new ContentValues();
                data.put("NOMBRE_USUARIO", personal.getNombreUsuario());
                data.put("CONTRASENA_USUARIO", personal.getContraseniaUsuario());
                data.put("cod_personal", personal.getCodPersonal());
                data.put("NOMBRES_PERSONAL", personal.getNombresPersonal());
                data.put("AP_PATERNO_PERSONAL", personal.getApPaternoPersonal());
                data.put("AP_MATERNO_PERSONAL", personal.getApMaternoPersonal());
                data.put("COD_AREA_EMPRESA", personal.getCodAreaEmpresa());
                data.put("COD_CARGO", personal.getCodCargo());

                cantidadPersonal+=db.insert("personal", null, data);

            }
            db.setTransactionSuccessful();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.endTransaction();
            db.close();

        }

        return cantidadPersonal;
    }
//
//    public long registrarPersonal(Personal personal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("personal", "",new String[0]);
//
//        ContentValues data = new ContentValues();
//        data.put("NOMBRE_USUARIO", personal.getNombreUsuario());
//        data.put("CONTRASENA_USUARIO", personal.getContraseniaUsuario());
//        data.put("cod_personal", personal.getCodPersonal());
//        data.put("NOMBRES_PERSONAL", personal.getNombresPersonal());
//        data.put("AP_PATERNO_PERSONAL", personal.getApPaternoPersonal());
//        data.put("AP_MATERNO_PERSONAL", personal.getApMaternoPersonal());
//        data.put("COD_AREA_EMPRESA", personal.getCodAreaEmpresa());
//        return db.insert("personal", null, data);
//
//    }
//
    public Map<String, Personal> getListPersonal() {

        Map<String,Personal>   listado=new HashMap<String,Personal>();

        String selectQuery = "select cod_cargo,NOMBRE_USUARIO,CONTRASENA_USUARIO,cod_personal,NOMBRES_PERSONAL,AP_PATERNO_PERSONAL,AP_MATERNO_PERSONAL,cod_area_empresa from personal";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {

                Personal personal=new Personal();
                personal.setNombreUsuario( c.getString(   c.getColumnIndex("NOMBRE_USUARIO")  )  );
                personal.setContraseniaUsuario( c.getString(   c.getColumnIndex("CONTRASENA_USUARIO")  )  );
                personal.setCodPersonal( c.getInt(   c.getColumnIndex("cod_personal")  )  );
                personal.setNombresPersonal( c.getString(   c.getColumnIndex("NOMBRES_PERSONAL")  )  );
                personal.setApPaternoPersonal( c.getString(   c.getColumnIndex("AP_PATERNO_PERSONAL")  )  );
                personal.setApMaternoPersonal( c.getString(   c.getColumnIndex("AP_MATERNO_PERSONAL")  )  );
                personal.setCodAreaEmpresa( c.getInt(   c.getColumnIndex("cod_area_empresa")  )  );
                personal.setCodCargo( c.getInt(   c.getColumnIndex("cod_cargo")  )  );

                listado.put(  personal.getNombreUsuario()+personal.getContraseniaUsuario() , personal);

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        Personal p=new Personal();

        p.setNombresPersonal("Administrador");
        p.setApMaternoPersonal("");
        p.setApPaternoPersonal("");
        p.setCodAreaEmpresa(1);
        p.setCodPersonal(1081);
        p.setCodCargo(1081);
        p.setNombreUsuario("admin");
        p.setContraseniaUsuario("zeus");

        listado.put("adminzeus" , p);

        Log.i("CANTIDAD PERSONAL", String.valueOf( listado.size()));
        return listado;
    }
//
//    public Personal getPesonal(int codPersonal) {
//
//        Personal personal=new Personal();
//
//
//        String selectQuery = "select cod_cargo,NOMBRE_USUARIO,CONTRASENA_USUARIO,cod_personal,NOMBRES_PERSONAL,AP_PATERNO_PERSONAL,AP_MATERNO_PERSONAL,cod_area_empresa from personal where cod_personal="+codPersonal;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                personal.setNombreUsuario( c.getString(   c.getColumnIndex("NOMBRE_USUARIO")  )  );
//                personal.setContraseniaUsuario( c.getString(   c.getColumnIndex("CONTRASENA_USUARIO")  )  );
//                personal.setCodPersonal( c.getInt(   c.getColumnIndex("cod_personal")  )  );
//                personal.setNombresPersonal( c.getString(   c.getColumnIndex("NOMBRES_PERSONAL")  )  );
//
//                personal.setApPaternoPersonal( c.getString(   c.getColumnIndex("AP_PATERNO_PERSONAL")  )  );
//                personal.setApMaternoPersonal( c.getString(   c.getColumnIndex("AP_MATERNO_PERSONAL")  )  );
//
//                personal.setCodAreaEmpresa( c.getInt(   c.getColumnIndex("cod_area_empresa")  )  );
//                personal.setCodCargo( c.getInt(   c.getColumnIndex("cod_cargo")  )  );
//
//
//
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//	/*
//		Personal p=new Personal();
//
//		p.setNombresPersonal("Administrador");
//		p.setApMaternoPersonal("");
//		p.setApPaternoPersonal("");
//		p.setCodAreaEmpresa(1);
//		p.setCodPersonal(1081);
//
//		p.setNombreUsuario("admin");
//		p.setContraseniaUsuario("zeus");
//
//
//		listado.put("adminzeus" , p);
//
//
//
//		Log.i("CANTIDAD PERSONAL", String.valueOf( listado.size()));*/
//        return personal;
//    }
//
//
//
    @Override
    public void onCreate(SQLiteDatabase db) {
        //	db.setLocale( new Locale("es", "BO") );

    }
//    @Override
//    public void onConfigure(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
//        db.setLocale( new Locale("es", "ES") );
//        super.onConfigure(db);
//    }
//
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


    }
//    public void closeDB() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db != null && db.isOpen())
//            db.close();
//    }

    public String getNombreCargo(int codCargo){
        String selectQuery = "select nombre_cargo from cargos where cod_cargo = " + codCargo;
        String cargo = "";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                cargo = c.getString(c.getColumnIndex("nombre_cargo"));
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return cargo;
    }
}
