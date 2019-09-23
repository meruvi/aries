package com.aries;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.aries.controller.ConfiguracionController;
import com.aries.entity.Personal;
import com.aries.util.AsyncServiceJSON;
import com.aries.util.AsyncServiceJSON.AsyncTaskListener;
import com.aries.util.ServiceInf;
import com.aries.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SincronizacionConfigFragment extends Fragment {

    private Button sincronizacionDatosGenerales;
//    public Button sincronizacion;
    private Button btnAjustesServidor;
//    public Button sincronizacionOfertas;
//    public Button sincronizacionCuentasxCobrar;
//    public Button sincronizacionUbicacionClientes;
//    public Button sincronizacionSeguimientoOfertaAcumulada;
//    private Button exportData, importData;
//    String mensaje="";
//    BackupData backup;
//
//    public Button sincronizacionSeguimientoCampania;
//
//    public Button boletas;
//
    View view;
//    Gson gson = new Gson();
    ProgressDialog pDialog;
    private Personal personal;
    private Intent intent;
    private ConfiguracionController configController;
//
//    BoletaVisitaController boletaVisitaController;
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup rootView, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sincronizacion_config, rootView, false);
        btnAjustesServidor = (Button) view.findViewById(R.id.btnAjustesServidor);
//        Button sincronizacion = (Button) view.findViewById(R.id.sincronizacion);
//
//        exportData = (Button) view.findViewById(R.id.exportData);
//        importData = (Button) view.findViewById(R.id.importData);
//        backup = new BackupData();
//
//        sincronizacionSeguimientoOfertaAcumulada = (Button) view.findViewById(R.id.sincronizacionSeguimientoOfertaAcumulada);
//        sincronizacionSeguimientoCampania = (Button) view.findViewById(R.id.sincronizacionSeguimientoCampania);
//
        personal = (Personal) getArguments().getSerializable("personal");
//        boletaVisitaController = new BoletaVisitaController(getContext());
//        boletaVisitaController.crearTablasBoletaVisitaCopia();
//        int filas = boletaVisitaController.getListadoVisitaMedicoCopiaCount();
//
//        boletas = (Button) view.findViewById(R.id.boletas);
//        if (filas > 0) {
//            boletas.setVisibility(View.GONE);
//        }
//
        configController = new ConfiguracionController(view.getContext());
        sincronizacionDatosGenerales = (Button) view.findViewById(R.id.sincronizacionDatosGenerales);
//        Button sincronizacionCatalogo = (Button) view.findViewById(R.id.sincronizacionCatalogo);
//        sincronizacionUbicacionClientes = (Button) view.findViewById(R.id.sincronizacionUbicacionClientes);
//
//        boletas.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                generarCopiaBoleta();
//            }
//        });
//
//        sincronizacionUbicacionClientes.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                actualizarClienteUbicacion();
//            }
//        });
//
//        sincronizacionCuentasxCobrar = (Button) view.findViewById(R.id.sincronizacionCuentasxCobrar);
//        sincronizacionOfertas = (Button) view.findViewById(R.id.sincronizacionOfertas);
//
        intent = new Intent(view.getContext(), AjustesActivity.class);
        intent.putExtra("personal", personal);
//
        btnAjustesServidor.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                startActivity(intent);
            }
        });
//
//        StrictMode.ThreadPolicy obj = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
//        StrictMode.setThreadPolicy(obj);
//
        pDialog = new ProgressDialog(view.getContext());
//
//        sincronizacion.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                    pDialog.setMessage("Actualizando Informacion...");
//                                    pDialog.setCancelable(false);
//                                    pDialog.show();
//
//                                    new Thread(new Runnable() {
//                                        public void run() {
//                                            sincronizarPedidos();
//                                            pDialog.dismiss();
//                                        }
//                                    }).start();
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro Sincronizar Pedidos?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        sincronizacionDatosGenerales.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                if (Util.isOnline(view.getContext())) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    ArrayList<String> data = configController.getIpConfiguracion();
                                    if(data.size() > 0) {
                                        sincronizarDatosGenerales2();
                                    }else{
                                        Toast.makeText(view.getContext(), "Debe llenar los datos de Ajustes del Servidor", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(view.getContext(), AjustesActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Estas Seguro de Sincronizar ?")
                            .setPositiveButton("Si", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                } else {
                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                }
            }
        });
//
//        sincronizacionCatalogo.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    sincronizarCatalogoPrecios();
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro de Sincronizar Catalogo?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        sincronizacionOfertas.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    sincronizarOfertas();
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro de Sincronizar Ofertas?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        sincronizacionCuentasxCobrar.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    actualizarCuenasxCobrar();
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro de Sincronizar Cuentas x Cobrar?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        sincronizacionSeguimientoOfertaAcumulada.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    actualizarOfertaAcumulada();
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro de Sincronizar Seguimiento Oferta Acumulada?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        sincronizacionSeguimientoCampania.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                if (Util.isOnline(view.getContext())) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    sincronizarSeguimientoCampania();
//
//                                    ConfiguracionController c = new ConfiguracionController(view.getContext());
//                                    String data[] = c.getIpConfiguracion();
//                                    String ip = data[0];
//                                    String codAreaEmpresa = data[2];
//
//                                    AsyncServiceJSON actualizarInformacion = new AsyncServiceJSON(new AsyncTaskListener() {
//                                        public void onInit() {
//                                            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                            pDialog.setMessage("Descargando Seguimiento Campañas");
//                                            pDialog.setCancelable(false);
//                                            pDialog.show();
//                                        }
//
//                                        public void onProgressUpdate() {
//                                        }
//                                        public void onCancel() {
//                                            Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
//                                            pDialog.cancel();
//                                        }
//                                        public void onFinish(JSONObject json) {
//                                            pDialog.cancel();
//                                            Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//                                        }
//                                    }, view, AsyncServiceJSON.ACTUALIZACION_SEGUIMIENTO_CAMPANIAS);
//
//                                    ServiceInf serviceInf[] = new ServiceInf[2];
//
//                                    String url = "http://" + ip + "/zeus/service/serviceOferta/listadoSeguimientoCampania/46";
//                                    serviceInf[0] = new ServiceInf("listadoSeguimientoCampania", url);
//
//                                    url = "http://" + ip + "/zeus/service/serviceCampania/listadocampania/" + codAreaEmpresa;
//                                    serviceInf[1] = new ServiceInf("listadocampania", url);
//
//                                    actualizarInformacion.execute(serviceInf);
//                                    break;
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setMessage("Estas Seguro de Sincronizar Seguimiento Oferta Acumulada?")
//                            .setPositiveButton("Si", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                } else {
//                    Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        exportData.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final EditText password = new EditText(getContext());
//                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case DialogInterface.BUTTON_POSITIVE:
//                                final String pwd = String.valueOf(password.getText());
//                                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                pDialog.setMessage("Exportando Datos...");
//                                pDialog.setCancelable(false);
//                                pDialog.show();
//
//                                new Thread(new Runnable() {
//                                    public void run() {
//                                        if(personal.getContraseniaUsuario().equals(pwd)){
//                                            mensaje = backup.exportToSD();
//                                        }else{
//                                            mensaje = "Contraseña incorrecta";
//                                        }
//                                        pDialog.dismiss();
//
//                                        getActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                }).start();
//                                break;
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                break;
//                        }
//                    }
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setMessage("Estas Seguro de exportar sus datos?\n\nLos datos guardados anteriormente se perderan.\n\nIngresa tu contraseña para confirmar")
//                        .setView(password)
//                        .setPositiveButton("Exportar", dialogClickListener)
//                        .setNegativeButton("Cancelar", dialogClickListener).show();
//            }
//        });
//
//        importData.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final EditText password = new EditText(getContext());
//                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case DialogInterface.BUTTON_POSITIVE:
//                                final String pwd = String.valueOf(password.getText());
//                                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                pDialog.setMessage("Importando Datos...");
//                                pDialog.setCancelable(false);
//                                pDialog.show();
//
//                                new Thread(new Runnable() {
//                                    public void run() {
//                                        if(personal.getContraseniaUsuario().equals(pwd)){
//                                            mensaje = backup.importFromSD();
//                                        }else{
//                                            mensaje = "Contraseña incorrecta";
//                                        }
//                                        pDialog.dismiss();
//
//                                        getActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                }).start();
//
//                                break;
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                break;
//                        }
//                    }
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setMessage("Estas Seguro de Importar datos?\n\nSus datos actuales se perderan.\n\nIngresa tu contraseña para confirmar")
//                        .setView(password)
//                        .setPositiveButton("Importar", dialogClickListener)
//                        .setNegativeButton("Cancelar", dialogClickListener).show();
//            }
//        });
//
        return view;
    }
//
//    public void alertMessage() {
//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case DialogInterface.BUTTON_POSITIVE:
//                        break;
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        break;
//                }
//            }
//        };
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
//        builder.setMessage("Estas Seguro de Salir?")
//                .setPositiveButton("Si", dialogClickListener)
//                .setNegativeButton("No", dialogClickListener).show();
//    }
//
//    public void sincronizar() {
//        try {
//            HttpClient client = new DefaultHttpClient();
//            ConfiguracionController c = new ConfiguracionController(view.getContext());
//            String data[] = c.getIpConfiguracion();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sincronizarDatosGenerales() {
//        try {
//            HttpClient client = new DefaultHttpClient();
//
//            ConfiguracionController c = new ConfiguracionController(view.getContext());
//            String data[] = c.getIpConfiguracion();
//            String ip = data[0];
//            String codAreaEmpresa = data[2];
//
//            String url = "http://" + ip + "/zeus/service/servicePersonal/listadopersonal/" + codAreaEmpresa;
//            Log.i("url:", url);
//
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = client.execute(request);
//            if (response.getStatusLine().getStatusCode() == 200) {
//            }
//
//            Reader reader = new InputStreamReader(response.getEntity().getContent());
//
//            Type collectionType = new TypeToken<Collection<Personal>>() {}.getType();
//            Collection<Personal> listPersonal = gson.fromJson(reader, collectionType);
//            PersonalController personalController = new PersonalController(view.getContext());
//            personalController.actualizarPersonal(listPersonal);
//
//            url = "http://" + ip + "/zeus/service/serviceCliente/listadoclientes/" + codAreaEmpresa;
//            Log.i("urlClientes:", url);
//
//            request = new HttpGet(url);
//            response = client.execute(request);
//            reader = new InputStreamReader(response.getEntity().getContent());
//            collectionType = new TypeToken<Collection<Cliente>>() {}.getType();
//            Collection<Cliente> listCliente = gson.fromJson(reader, collectionType);
//
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            presentacionesProductoController.actualizarClientes(listCliente);
//
//            url = "http://" + ip + "/zeus/service/serviceProducto/listadoProductos/" + codAreaEmpresa;
//
//            request = new HttpGet(url);
//            response = client.execute(request);
//            reader = new InputStreamReader(response.getEntity().getContent());
//
//            collectionType = new TypeToken<Collection<PresentacionesProductos>>() {}.getType();
//
//            Collection<PresentacionesProductos> listProductos = gson.fromJson(reader, collectionType);
//
//            presentacionesProductoController.actualizarPresentacionesProducto(listProductos);
//
//            url = "http://" + ip + "/zeus/service/serviceOferta/listadoOfertas/" + codAreaEmpresa;
//
//            request = new HttpGet(url);
//            response = client.execute(request);
//
//            reader = new InputStreamReader(response.getEntity().getContent());
//
//            Type collectionType2 = new TypeToken<Collection<CampaniasOfertas>>() {}.getType();
//            Collection<CampaniasOfertas> listadoOfertas = gson.fromJson(reader, collectionType2);
//
//            OfertaController ofertaController = new OfertaController(view.getContext());
//
//            ofertaController.actualizarCampaniasOfertas(listadoOfertas);
//
//            url = "http://" + ip + "/zeus/service/serviceCliente/cuentaxcobrar/" + codAreaEmpresa;
//
//            request = new HttpGet(url);
//
//            response = client.execute(request);
//            reader = new InputStreamReader(response.getEntity().getContent());
//
//            Type collectionType4 = new TypeToken<Collection<CuentaCobrar>>() {}.getType();
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            Collection<CuentaCobrar> listadoCuentas = gsonCuentas.fromJson(reader, collectionType4);
//            personalController.actualizarCuenta(listadoCuentas);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*Metodo que realiza la sincronizacion de datos desde el servidor hacia el telefono
//     * Sincronizacion de datos generales
//     * */
    public void sincronizarDatosGenerales2() {
        ArrayList<String> data = configController.getIpConfiguracion();
        String ip = data.get(0);
        String codAreaEmpresa = data.get(2);
        try {
            AsyncServiceJSON a = new AsyncServiceJSON(new AsyncTaskListener() {
                public void onInit() {
                    pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pDialog.setMessage("Descargando");
                    pDialog.setCancelable(false);
                    pDialog.show();
                }
                public void onProgressUpdate() {
                }
                public void onCancel() {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    pDialog.cancel();
                }
                public void onFinish(JSONObject json) {
                    pDialog.cancel();
                    Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
                }
            }, view.getContext(), AsyncServiceJSON.ACTUALIZACION_DATOS_GENERALES);

            ServiceInf serviceInf[] = new ServiceInf[1];
            String url = "http://" + ip + "/zeus/service/servicePersonal/listadopersonal/" + codAreaEmpresa;
            serviceInf[0] = new ServiceInf("servicePersonal", url);
            Log.d("url:", url);

            a.execute(serviceInf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public void registrarJSON(JSONObject jsonObject) {
//        Log.i("registrarJSON", jsonObject.toString());
//        try {
//            Type collectionType = new TypeToken<Collection<Personal>>() {}.getType();
//            Collection<Personal> listPersonal = gson.fromJson((Reader) jsonObject.get("servicePersonal"), collectionType);
//
//            PersonalController personalController = new PersonalController(view.getContext());
//            personalController.actualizarPersonal(listPersonal);
//
//            collectionType = new TypeToken<Collection<Cliente>>() {}.getType();
//            Collection<Cliente> listCliente = gson.fromJson((Reader) jsonObject.get("serviceCliente"), collectionType);
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            presentacionesProductoController.actualizarClientes(listCliente);
//
//            collectionType = new TypeToken<Collection<PresentacionesProductos>>() {}.getType();
//            Collection<PresentacionesProductos> listProductos = gson.fromJson((Reader) jsonObject.get("serviceProducto"), collectionType);
//            presentacionesProductoController.actualizarPresentacionesProducto(listProductos);
//
//            Type collectionType2 = new TypeToken<Collection<CampaniasOfertas>>() {}.getType();
//            Collection<CampaniasOfertas> listadoOfertas = gson.fromJson((Reader) jsonObject.get("serviceOferta"), collectionType2);
//            OfertaController ofertaController = new OfertaController(view.getContext());
//            ofertaController.actualizarCampaniasOfertas(listadoOfertas);
//
//            Type collectionType4 = new TypeToken<Collection<CuentaCobrar>>() {}.getType();
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            Collection<CuentaCobrar> listadoCuentas = gsonCuentas.fromJson((Reader) jsonObject.get("cuentaxcobrar"), collectionType4);
//            personalController.actualizarCuenta(listadoCuentas);
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sincronizarPedidos() {
//        try {
//            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            HttpClient client = new DefaultHttpClient();
//            ConfiguracionController c = new ConfiguracionController(view.getContext());
//            String data[] = c.getIpConfiguracion();
//            String ip = data[0];
//            PedidoController pedidoController = new PedidoController(view.getContext());
//            List<Pedido> listado = pedidoController.listadoPedido(1, 0);
//            for (Pedido p : listado) {
//                String obs = " Fecha Entrega:" + p.getFechaEntregaPedido();
//                if (!p.getRazonSocial().equals("")) {
//                    obs += " Razon Social:" + p.getRazonSocial();
//                }
//                if (!p.getNitPedido().equals("")) {
//                    obs += " NIT:" + p.getNitPedido();
//                }
//                p.setObsPedido(p.getObsPedido() + " " + obs);
//                List<PresentacionesProductos> listado2 = pedidoController.getListPedidoDetalleActualizacion(String.valueOf(p.getCodPedido()));
//                List<PedidoDetallePremio> listado3 = pedidoController.getListPedidoPremios(String.valueOf(p.getCodPedido()));
//                p.setDetalle(listado2);
//                p.setDetallePremios(listado3);
//
//                List<PedidoGrupoFidelidad> lst = pedidoController.getPedidoGrupoFidelidadList(p.getCodPedido(), ip, p.getCodCliente());
//
//                String jsonA = gson.toJson(lst);
//
//                System.out.println();
//                System.out.println();
//                System.out.println();
//                System.out.println("=============>==========>=======>");
//                p.setPedidoGrupoFidelidadList(lst);
//                System.out.println(jsonA);
//
//            }
//            System.out.println("size:" + listado.size());
//
//            String nombrePer = personal.getApPaternoPersonal() + personal.getApMaternoPersonal() + personal.getNombresPersonal();
//            nombrePer = nombrePer.replaceAll(" ", "");
//            int versionGoogle = 0;
//            int versionZeus = 0;
//
//            try {
//                versionGoogle = this.getActivity().getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
//                versionZeus = this.getActivity().getPackageManager().getPackageInfo("com.cofar", 0).versionCode;
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            nombrePer = nombrePer + ":@" + versionGoogle + ":@" + versionZeus;
//
//            String urlPedidos = "http://" + ip + "/zeus/service/servicePedido/savePedido/" + personal.getCodPersonal() + "/" + nombrePer;
//
//            Log.i("urlPedido", urlPedidos);
//
//            HttpPost sendPedidos = new HttpPost(urlPedidos);
//
//            String json = gson.toJson(listado);
//
//            StringEntity entity = new StringEntity(json);
//            sendPedidos.setEntity(entity);
//
//            sendPedidos.setHeader("Accept", "application/json");
//            sendPedidos.setHeader("Content-type", "application/json");
//
//            HttpResponse responseSendPedido = client.execute(sendPedidos);
//            String a = convertStreamToString(responseSendPedido.getEntity().getContent());
//
//            Log.i("json: ", a);
//
//            Type collectionType10 = new TypeToken<Collection<Pedido>>() {
//            }.getType();
//            Collection<Pedido> listadoRegistrado = gson.fromJson(a, collectionType10);
//            for (Pedido pe : listadoRegistrado) {
//                Log.i("reg:", pe.getCodPedidoAndroid() + ":" + pe.getCodEstadoPedido());
//                pedidoController.updatePedido(pe);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String convertStreamToString(InputStream is) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
//
//    public void sincronizarCatalogoPrecios() {
//        ConfiguracionController c = new ConfiguracionController(view.getContext());
//        String data[] = c.getIpConfiguracion();
//        String ip = data[0];
//
//        AsyncServiceJSON actualizarInformacion = new AsyncServiceJSON(new AsyncTaskListener() {
//            public void onInit() {
//                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                pDialog.setMessage("Descargando");
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//            public void onProgressUpdate() {
//            }
//            public void onCancel() {
//                Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
//                pDialog.cancel();
//            }
//            public void onFinish(JSONObject json) {
//                pDialog.cancel();
//                Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//            }
//        }, view, AsyncServiceJSON.ACTUALIZACION_CATALOGO);
//
//        ServiceInf serviceInf[] = new ServiceInf[2];
//
//        String url = "http://" + ip + "/zeus/service/serviceProducto/listadoOfertaCatalogo/";
//        serviceInf[0] = new ServiceInf("listadoOfertaCatalogo", url);
//        url = "http://" + ip + "/zeus/service/serviceProducto/listadoOfertaCatalogoDetalle/";
//        serviceInf[1] = new ServiceInf("listadoOfertaCatalogoDetalle", url);
//
//        actualizarInformacion.execute(serviceInf);
//    }
//
//    public void sincronizarOfertas() {
//        ConfiguracionController c = new ConfiguracionController(view.getContext());
//        String data[] = c.getIpConfiguracion();
//        String ip = data[0];
//        String codAreaEmpresa = data[2];
//        AsyncServiceJSON actualizarInformacion = new AsyncServiceJSON(new AsyncTaskListener() {
//            public void onInit() {
//                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                pDialog.setMessage("Descargando Ofertas");
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//            public void onProgressUpdate() {
//            }
//            public void onCancel() {
//                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
//                pDialog.cancel();
//            }
//            public void onFinish(JSONObject json) {
//                pDialog.cancel();
//                Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//            }
//        }, view, AsyncServiceJSON.ACTUALIZACION_OFERTAS);
//        ServiceInf serviceInf[] = new ServiceInf[2];
//        String url = "http://" + ip + "/zeus/service/serviceOferta/listadoOfertas/" + codAreaEmpresa + "/" + personal.getCodPersonal();
//        serviceInf[0] = new ServiceInf("listadoOfertas", url);
//        url = "http://" + ip + "/zeus/service/serviceOferta/listadoMaterial/" + codAreaEmpresa;
//        serviceInf[1] = new ServiceInf("listadoMaterial", url);
//        actualizarInformacion.execute(serviceInf);
//    }
//
//    public void actualizarCuenasxCobrar() {
//        ConfiguracionController c = new ConfiguracionController(view.getContext());
//        String data[] = c.getIpConfiguracion();
//        String ip = data[0];
//        String codAreaEmpresa = data[2];
//        AsyncServiceJSON actualizarInformacion = new AsyncServiceJSON(new AsyncTaskListener() {
//            public void onInit() {
//                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                pDialog.setMessage("Descargando Cuentas x Cobrar");
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//            public void onProgressUpdate() {
//            }
//            public void onCancel() {
//                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
//                pDialog.cancel();
//            }
//            public void onFinish(JSONObject json) {
//                pDialog.cancel();
//                Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//            }
//        }, view, AsyncServiceJSON.ACTUALIZACION_CUENTAS_X_COBRAR);
//        ServiceInf serviceInf[] = new ServiceInf[1];
//
//        String url = "http://" + ip + "/zeus/service/serviceCliente/cuentaxcobrar/" + codAreaEmpresa;
//        serviceInf[0] = new ServiceInf("cuentaxcobrar", url);
//        actualizarInformacion.execute(serviceInf);
//    }
//
//    public void actualizarOfertaAcumulada() {
//        ConfiguracionController c = new ConfiguracionController(view.getContext());
//        String data[] = c.getIpConfiguracion();
//        String ip = data[0];
//        String codAreaEmpresa = data[2];
//
//        AsyncServiceJSON actualizarInformacion = new AsyncServiceJSON(new AsyncTaskListener() {
//            public void onInit() {
//                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                pDialog.setMessage("Descargando Oferta Acumulada");
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//            public void onProgressUpdate() {
//            }
//            public void onCancel() {
//                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
//                pDialog.cancel();
//            }
//            public void onFinish(JSONObject json) {
//                pDialog.cancel();
//                Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//            }
//        }, view, AsyncServiceJSON.ACTUALIZACION_SEGUIMIENTO_OFERTA_ACUMULADA);
//        ServiceInf serviceInf[] = new ServiceInf[1];
//
//        String url = "http://" + ip + "/zeus/service/serviceOferta/listadoOfertaAcumulada/" + codAreaEmpresa;
//        serviceInf[0] = new ServiceInf("listadoOfertaAcumulada", url);
//
//        actualizarInformacion.execute(serviceInf);
//    }
//
//    public void actualizarClienteUbicacion() {
//        if (Util.isOnline(view.getContext())) {
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which) {
//                        case DialogInterface.BUTTON_POSITIVE:
//                            sincronizarUbicacionClientes();
//                            break;
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            break;
//                    }
//                }
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//            builder.setMessage("Estas Seguro de Sincronizar ?")
//                    .setPositiveButton("Si", dialogClickListener)
//                    .setNegativeButton("No", dialogClickListener).show();
//        } else {
//            Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void actualizarSeguimientoCampania() {
//        if (Util.isOnline(view.getContext())) {
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which) {
//                        case DialogInterface.BUTTON_POSITIVE:
//                            sincronizarUbicacionClientes();
//                            break;
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            break;
//                    }
//                }
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//            builder.setMessage("Estas Seguro de Actualizar Seguimiento Campaña ?")
//                    .setPositiveButton("Si", dialogClickListener)
//                    .setNegativeButton("No", dialogClickListener).show();
//        } else {
//            Toast.makeText(view.getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void sincronizarUbicacionClientes() {
//        ClienteUbicacionController cUbicacion = new ClienteUbicacionController(view.getContext());
//        cUbicacion.actualizarCodPersonal(personal);
//
//        ConfiguracionController c = new ConfiguracionController(view.getContext());
//        String data[] = c.getIpConfiguracion();
//        String ip = data[0];
//        String codAreaEmpresa = data[2];
//
//        String url = "http://" + ip + "/zeus/service/serviceClienteUbicacion/";
//        Log.i("url:", url);
//
//        SincronizacionZeus sincronizacionZeus = new SincronizacionZeus(view.getContext(), pDialog);
//        ServiceInf serviceInf[] = new ServiceInf[1];
//        serviceInf[0] = new ServiceInf("serviceClienteUbicacion", url);
//        sincronizacionZeus.execute(serviceInf);
//    }
//
//    public void sincronizarClienteMaterial() {
//        CampaniaClienteMaterialController cam = new CampaniaClienteMaterialController(view.getContext());
//        List<CampaniaClienteMaterial> listado = cam.getClienteCampaniaMaterial();
//        cam.closeDB();
//        SeguimientoCampaniaController ofertaAcumuladaController = new SeguimientoCampaniaController(view.getContext());
//        try {
//            HttpClient client = new DefaultHttpClient();
//            ConfiguracionController c = new ConfiguracionController(view.getContext());
//            String data[] = c.getIpConfiguracion();
//            String ip = data[0];
//            String urlPedidos = "http://" + ip + "/zeus/service/serviceCampania/registroClienteMaterial/46";
//
//            Log.i("urlPedido", urlPedidos);
//
//            HttpPost sendPedidos = new HttpPost(urlPedidos);
//
//            String json = gson.toJson(listado);
//
//            StringEntity entity = new StringEntity(json);
//            sendPedidos.setEntity(entity);
//            sendPedidos.setHeader("Accept", "application/json");
//            sendPedidos.setHeader("Content-type", "application/json");
//            HttpResponse responseSendPedido = client.execute(sendPedidos);
//            if (responseSendPedido.getStatusLine().getStatusCode() == 200) {
//                HttpEntity httpEntity = responseSendPedido.getEntity();
//                if (httpEntity != null) {
//                    InputStreamReader readerPedidosReg = new InputStreamReader(entity.getContent());
//                    Type collectionType10 = new TypeToken<Collection<SeguimientoCampaniaCliente>>() {}.getType();
//                    Collection<SeguimientoCampaniaCliente> listadoSeguimiento = gson.fromJson(readerPedidosReg, collectionType10);
//                    ofertaAcumuladaController.guardarSeguimientoCampaniaCliente(listadoSeguimiento);
//                    ofertaAcumuladaController.closeDB();
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(getActivity().getApplicationContext(), "Error Actualizar", Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//    }
//
//    public void sincronizarSeguimientoCampania() {
//        new AsyncTask<String, Void, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                sincronizarClienteMaterial();
//                return "";
//            }
//
//            @Override
//            protected void onCancelled() {
//                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
//                pDialog.cancel();
//                super.onCancelled();
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                pDialog.cancel();
//                Toast.makeText(getActivity().getApplicationContext(), "Termino la descarga de informacion", Toast.LENGTH_LONG).show();
//                super.onPostExecute(result);
//            }
//
//            @Override
//            protected void onPreExecute() {
//                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                pDialog.setMessage("Descargando Seguimiento Campañas");
//                pDialog.setCancelable(false);
//                pDialog.show();
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                // TODO Auto-generated method stub
//                super.onProgressUpdate(values);
//            }
//        }.execute("");
//    }
//
//    public void generarCopiaBoleta() {
//        boletas.setVisibility(View.GONE);
//        boletaVisitaController = new BoletaVisitaController(getContext());
//        boletaVisitaController.crearTablasBoletaVisitaCopia();
//        boletaVisitaController.getListadoVisitaMedicoCopia();
//        int filas = boletaVisitaController.getListadoVisitaMedicoCopiaCount();
//        if (filas > 0) {
//            boletas.setVisibility(View.GONE);
//        } else {
//            boletas.setVisibility(View.VISIBLE);
//        }
//    }
}
