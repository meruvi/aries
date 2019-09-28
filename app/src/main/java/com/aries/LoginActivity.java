package com.aries;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aries.controller.PersonalController;
import com.aries.entity.Personal;
import com.aries.util.Util;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;

    private static final String TAG = LoginActivity.class.getSimpleName();

    TextView versionName;

    PersonalController personalController;
    EditText usuarioEdit;
    EditText passwordEdit;
    Button loginButton;

    Map<String, Personal> mapPersonal;
    Intent intent;

    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG, "--------------------INICIANDO-----------------");
        //Intent service = new Intent(this, LocationManagerZeus.class);
        //startService(service);
        Log.i(TAG, "--------------------FIN-----------------");
        usuarioEdit = (EditText) findViewById(R.id.usuarioEdit);
        usuarioEdit.setFilters(new InputFilter[]{filter});
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        passwordEdit.setFilters(new InputFilter[]{filter});
        loginButton = (Button) findViewById(R.id.loginButton);
        versionName = (TextView) findViewById(R.id.versionName);
        versionName.setText("Version " + Util.getVersionName(getApplicationContext()));

        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Información")
                .setMessage("Usuario o contraseña incorrecto")
                .setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        personalController = new PersonalController(this);
        Log.i(TAG, mapPersonal+"" );
        mapPersonal = personalController.getListPersonal();

        intent = new Intent(this, MainActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapPersonal != null) {
                    if (mapPersonal.containsKey(usuarioEdit.getText().toString() + passwordEdit.getText().toString())) {
                        Personal personal = mapPersonal.get(usuarioEdit.getText().toString() + passwordEdit.getText().toString());
                        intent.putExtra("personal", personal);
                        startActivity(intent);
                        finish();

                    } else {
                        alertDialog.show();
                    }
                } else {
                    alertDialog.setMessage("Tabla Personal no existe");
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        mapPersonal = personalController.getListPersonal();
        super.onStart();
    }

    //Filtro para no dejar ingresar caracteres especiales (espacios en blanco)
    private String blockCharacterSet = " ";
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            if (charSequence != null && blockCharacterSet.contains(("" + charSequence))) {
                return "";
            }
            return null;
        }
    };
}
