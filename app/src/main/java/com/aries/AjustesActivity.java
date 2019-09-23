package com.aries;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aries.controller.ConfiguracionController;
import com.aries.entity.Personal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class AjustesActivity extends AppCompatActivity {

    private Button buttonConfiguracion;
    private EditText ipRegional;
    private EditText ipExterna;
    private Map<String,Integer> regionales=new HashMap<String,Integer>();
    private Map<Integer,String>  regionales2=new HashMap<Integer,String>();
    private int codRegionalSeleccionada=0;
    private Personal personal;
    private ConfiguracionController c;
    private Spinner comboRegional;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        personal=(Personal)getIntent().getExtras().getSerializable("personal");

        setRegionales();

        c=new ConfiguracionController(this);
        String valuesIP[]=c.getModificacion();
        buttonConfiguracion=(Button)findViewById(R.id.btnGuardarConfig);
        comboRegional = (Spinner) findViewById(R.id.comboRegional);
        ipExterna=(EditText)findViewById(R.id.ipExterna);
        ipRegional=(EditText)findViewById(R.id.ipRegional);
        ipExterna.setFilters(new InputFilter[]{filter});
        ipRegional.setFilters(new InputFilter[]{filter});

        ipExterna.setText(valuesIP[0]);
        ipRegional.setText(valuesIP[1]);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.regionales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboRegional.setAdapter(adapter);

        Collection<Integer> indi=regionales.values();
        valuesIP[2]=(valuesIP[2]==null)?"0":valuesIP[2];
        valuesIP[2]=(valuesIP[2].equals(""))?"0":valuesIP[2];
        String nombreRegional="";
        for(int codigoArea:indi){
            if( Integer.valueOf( valuesIP[2] )==codigoArea ){
                nombreRegional=regionales2.get(  codigoArea  );
                break;
            }
        }
        ArrayAdapter myAdap = (ArrayAdapter) comboRegional.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(nombreRegional);

        comboRegional.setSelection(spinnerPosition);

        buttonConfiguracion.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                if(codRegionalSeleccionada != 0) {
                    if(!ipExterna.getText().toString().equals("")) {
                        if(!ipRegional.getText().toString().equals("")) {
                            ConfiguracionController c=new ConfiguracionController(AjustesActivity.this);
                            c.actualizarConfiguracion(ipExterna.getText().toString(), ipRegional.getText().toString(), codRegionalSeleccionada);
                            finish();
                        }else{
                            ipRegional.requestFocus();
                            Toast.makeText(getApplicationContext(), "Por favor ingrese IP Regional", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        ipExterna.requestFocus();
                        Toast.makeText(getApplicationContext(), "Por favor ingrese IP Externa", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Por favor seleccione su AGENCIA REGIONAL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        comboRegional.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View arg1,int posicion, long arg3) {
                TypedArray arrayLocalidades = getResources().obtainTypedArray(R.array.regionales);
                System.out.println("RRRRRR:"+arrayLocalidades.getText(posicion));
                codRegionalSeleccionada=regionales.get(arrayLocalidades.getText(posicion));
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    public void setRegionales(){
        regionales.put("Seleccionar Agencia",0);
        regionales.put("AGENCIA REGIONAL LA PAZ",46);
        regionales.put("AGENCIA REGIONAL EL ALTO",47);
        regionales.put("AGENCIA REGIONAL POTOSI",48);
        regionales.put("AGENCIA REGIONAL COCHABAMBA",49);
        regionales.put("AGENCIA REGIONAL QUILLACOLLO",51);
        regionales.put("AGENCIA REGIONAL RIBERALTA",52);
        regionales.put("AGENCIA REGIONAL SANTA CRUZ",53);
        regionales.put("AGENCIA REGIONAL SUCRE",54);
        regionales.put("AGENCIA REGIONAL TRINIDAD",55);
        regionales.put("AGENCIA REGIONAL TARIJA",56);
        regionales.put("AGENCIA REGIONAL ORURO",63);
        regionales.put("AGENCIA REGIONAL MONTERO",1013);

        regionales2.put(0,"Seleccionar Agencia");
        regionales2.put(46,"AGENCIA REGIONAL LA PAZ");
        regionales2.put(47,"AGENCIA REGIONAL EL ALTO");
        regionales2.put(48,"AGENCIA REGIONAL POTOSI");
        regionales2.put(49,"AGENCIA REGIONAL COCHABAMBA");
        regionales2.put(51,"AGENCIA REGIONAL QUILLACOLLO");
        regionales2.put(52,"AGENCIA REGIONAL RIBERALTA");
        regionales2.put(53,"AGENCIA REGIONAL SANTA CRUZ");
        regionales2.put(54,"AGENCIA REGIONAL SUCRE");
        regionales2.put(55,"AGENCIA REGIONAL TRINIDAD");
        regionales2.put(56,"AGENCIA REGIONAL TARIJA");
        regionales2.put(63,"AGENCIA REGIONAL ORURO");
        regionales2.put(1013,"AGENCIA REGIONAL MONTERO");
    }

}
