package com.aries;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.aries.util.Util;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    //private static final long SPLASH_SCREEN_DELAY = 3000;
    private static final long SPLASH_SCREEN_DELAY = 2000;

    private final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            //Manifest.permission.PERSISTENT_ACTIVITY,
            Manifest.permission.ACCESS_FINE_LOCATION,//
            Manifest.permission.ACCESS_COARSE_LOCATION,//
            //Manifest.permission.VIBRATE,
            //Manifest.permission.GET_ACCOUNTS,//
            //Manifest.permission.READ_PHONE_STATE,//
            //Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.READ_EXTERNAL_STORAGE//
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        Util.copiarBD(getApplicationContext());

        //Pregunta si tiene todos los permisos
        if(!hasPermissions(this, PERMISSIONS)){
            //Se procede a pedir todos o algun permiso que no se haya concedido
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            //Se inicia la aplicacion si se concedio todos los permisos
            iniciarAplicacion();
        }
    }

    public void iniciarAplicacion(){
        TimerTask task;
        try {
            //MainV7 mainV7 = new MainV7();
            //mainV7.init(getApplicationContext());

            task = new TimerTask() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            };
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Se produjo un error inesperado. Codigo: 1001. ", Toast.LENGTH_LONG).show();
            task = new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            };
        }

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(hasPermissions(this, permissions)){
            //Se inicia la aplicacion si se concedio todos los permisos
            iniciarAplicacion();
        }else{
            //Se cierra la aplicacion si no se concedio algun permiso
            finish();
        }

    }
}
