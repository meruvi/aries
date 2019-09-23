package com.aries.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.aries.controller.PersonalController;
import com.aries.entity.Personal;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class AsyncServiceJSON extends AsyncTask<ServiceInf, Void, JSONObject> {

    private View view;
    private Gson gson = new Gson();
    private int tipoOperacion;
//    public static int ACTUALIZACION_CATALOGO = 1;
    public static int ACTUALIZACION_DATOS_GENERALES = 2;
//    public static int ACTUALIZACION_OFERTAS = 3;
//    public static int ACTUALIZACION_CUENTAS_X_COBRAR = 4;
//    public static int ACTUALIZACION_SEGUIMIENTO_OFERTA_ACUMULADA = 5;
//    public static int ACTUALIZACION_SEGUIMIENTO_CAMPANIAS = 6;
//    public static int ACTUALIZACION_DATOS_GENERALES_DESPACHADOR = 7;
//    public static int ACTUALIZACION_STOCK = 8;
//    public static int ACTUALIZACION_LIMITE_CREDITO = 9;
//
    public interface AsyncTaskListener {
        void onInit();
        void onProgressUpdate();
        void onCancel();
        void onFinish(JSONObject json);
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
//
    private AsyncTaskListener asyncTaskListener;
//
    Context context;
    public AsyncServiceJSON(AsyncTaskListener asyncTaskListener, Context context, int tipoOperacion) {
        this.asyncTaskListener = asyncTaskListener;
        this.context = context;
        this.tipoOperacion = tipoOperacion;
    }
//
//    /**
//     * Metodo que es llamado asincronamente cuando termina de responderse la peticion
//     * @param serviceInf
//     * @return
//     */
    @Override
    public JSONObject doInBackground(ServiceInf... serviceInf) {
        JSONObject json = new JSONObject();
        for (ServiceInf service : serviceInf) {
            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 70000);

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(service.getUrl());
            httpget.setParams(params);

            HttpResponse response;
            try {
                response = httpclient.execute(httpget);
                Log.i("Respuesta-g:", response.getStatusLine().toString());
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = entity.getContent();
                        json.put(service.getNameService(), convertStreamToString(instream));
                        instream.close();
                    } else {
                        cancel(true);
                    }
                } else {
                    cancel(true);
                }
            } catch (Exception e) {
                cancel(true);
            }
        }
        if (tipoOperacion == ACTUALIZACION_DATOS_GENERALES) {
            registrarDatosGeneralesJSON(json);
        }/* else if (tipoOperacion == ACTUALIZACION_OFERTAS) {
            registrarOfertaJSON(json);
        } else if (tipoOperacion == ACTUALIZACION_CATALOGO) {
            registrarCatalogoPreciosJSON(json);
        } else if (tipoOperacion == ACTUALIZACION_CUENTAS_X_COBRAR) {
            registrarCuentasxCobrarJSON(json);
        } else if (tipoOperacion == ACTUALIZACION_SEGUIMIENTO_OFERTA_ACUMULADA) {
            registrarOfertaAcumulada(json);
        } else if (tipoOperacion == ACTUALIZACION_SEGUIMIENTO_CAMPANIAS) {
            registrarSeguimientoCampania(json);
        } else if (tipoOperacion == ACTUALIZACION_DATOS_GENERALES_DESPACHADOR) {
            registrarDatosGeneralesDespachador(json);
        } else if (tipoOperacion == ACTUALIZACION_STOCK) {
            registrarStock(json);
        }*/
        Log.i("JSON", "___________FIN____________");
        return json;
    }
//
//    public void registrarCatalogoPreciosJSON(JSONObject jsonObject) {
//        try {
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//
//            Type collectionType = new TypeToken<Collection<OfertaCatalogoPrecioCliente>>() {}.getType();
//            Collection<OfertaCatalogoPrecioCliente> listadoOferta = gsonCuentas.fromJson((String) jsonObject.get("listadoOfertaCatalogo"), collectionType);
//            presentacionesProductoController.actualizarCatalogoOferta(listadoOferta);
//
//            collectionType = new TypeToken<Collection<OfertaCatalogoPrecioClienteDetalle>>() {}.getType();
//            Collection<OfertaCatalogoPrecioClienteDetalle> listadoOfertaDetalle = gson.fromJson((String) jsonObject.get("listadoOfertaCatalogoDetalle"), collectionType);
//            presentacionesProductoController.actualizarCatalogoOfertaDetalle(listadoOfertaDetalle);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarOfertaJSON(JSONObject jsonObject) {
//        try {
//            Type collectionType2 = new TypeToken<Collection<CampaniasOfertas>>() {}.getType();
//            Collection<CampaniasOfertas> listadoOfertas = gson.fromJson((String) jsonObject.get("listadoOfertas"), collectionType2);
//            OfertaController ofertaController = new OfertaController(view.getContext());
//            ofertaController.actualizarCampaniasOfertas(listadoOfertas);
//            listadoOfertas.clear();
//
//            collectionType2 = new TypeToken<Collection<MatPromocional>>() {}.getType();
//            Collection<MatPromocional> listadoMaterial = gson.fromJson((String) jsonObject.get("listadoMaterial"), collectionType2);
//
//            ofertaController.actualizarMaterial(listadoMaterial);
//            listadoMaterial.clear();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarCuentasxCobrarJSON(JSONObject jsonObject) {
//        try {
//            PersonalController personalController = new PersonalController(view.getContext());
//            Type collectionType4 = new TypeToken<Collection<CuentaCobrar>>() {}.getType();
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            Collection<CuentaCobrar> listadoCuentas = gsonCuentas.fromJson((String) jsonObject.get("cuentaxcobrar"), collectionType4);
//            personalController.actualizarCuenta(listadoCuentas);
//        } catch (Exception e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarOfertaAcumulada(JSONObject jsonObject) {
//        try {
//            OfertaAcumuladaController ofertaAcumuladaController = new OfertaAcumuladaController(view.getContext());
//
//            Type collectionType4 = new TypeToken<Collection<OfertaAcumulada>>() {}.getType();
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            Collection<OfertaAcumulada> listado = gsonCuentas.fromJson((String) jsonObject.get("listadoOfertaAcumulada"), collectionType4);
//            ofertaAcumuladaController.actualizarOfertaAcumulada(listado);
//        } catch (Exception e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarSeguimientoCampania(JSONObject jsonObject) {
//        try {
//            SeguimientoCampaniaController ofertaAcumuladaController = new SeguimientoCampaniaController(view.getContext());
//
//            Type collectionType4 = new TypeToken<Collection<SeguimientoCampaniaCliente>>() {}.getType();
//            Gson gsonCuentas = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//            Collection<SeguimientoCampaniaCliente> listado = gsonCuentas.fromJson((String) jsonObject.get("listadoSeguimientoCampania"), collectionType4);
//            ofertaAcumuladaController.guardarSeguimientoCampaniaCliente(listado);
//
//            listado.clear();
//
//            CampaniaPremioController campaniaPremioController = new CampaniaPremioController(view.getContext());
//
//            Type collectionType5 = new TypeToken<Collection<Campania>>() {}.getType();
//
//            Collection<Campania> listadoCampania = gsonCuentas.fromJson((String) jsonObject.get("listadocampania"), collectionType5);
//
//            campaniaPremioController.registrarCampania(listadoCampania);
//            listadoCampania.clear();
//        } catch (Exception e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
    public void registrarDatosGeneralesJSON(JSONObject jsonObject) {
        try {
            Type collectionType;
            collectionType= new TypeToken<Collection<Personal>>() {}.getType();
            ArrayList<Personal> listPersonal = gson.fromJson((String) jsonObject.get("servicePersonal"), collectionType);

            PersonalController personalController = new PersonalController(context);
            personalController.actualizarPersonal(listPersonal);
            //listPersonal.clear();

            Log.e("GONZALO-JSON: ", jsonObject.getString("servicePersonal"));
            Log.e("PERSONAL SIZE: ", "" + listPersonal.size());

//            collectionType = new TypeToken<Collection<Cliente>>() {}.getType();
//            Collection<Cliente> listCliente = gson.fromJson((String) jsonObject.get("serviceCliente"), collectionType);
//
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            presentacionesProductoController.actualizarClientes(listCliente);
//            listCliente.clear();
//
//            Log.e("GONZALO-JSON: ", jsonObject.getString("serviceCliente"));
//            Log.e("GONZALO-JSON: ", "" + listPersonal.size());
//
//            collectionType = new TypeToken<Collection<PresentacionesProductos>>() {}.getType();
//            Collection<PresentacionesProductos> listProductos = gson.fromJson((String) jsonObject.get("serviceProducto"), collectionType);
//            presentacionesProductoController.actualizarPresentacionesProducto(listProductos);
//            listProductos.clear();
//
//            Log.e("GONZALO-JSON: ", jsonObject.getString("serviceProducto"));
//            Log.e("GONZALO-JSON: ", "" + listPersonal.size());
//
//
//
//            //collectionType = new TypeToken<Collection<PoliticaPrecio>>() {}.getType();
//            //Collection<PoliticaPrecio> listPolPrice = gson.fromJson((String) jsonObject.get("servicePolPriceFide"), collectionType);
//            //PoliticaPreciosController politicaPreciosController = new PoliticaPreciosController(view.getContext());
//            //politicaPreciosController.actualizarPoliticaPrecios(listPolPrice);
//            //listPolPrice.clear();
//            ArrayList<PoliticaPrecio> listPolPrice= new ArrayList<>();
//            String cad = jsonObject.getString("servicePolPriceFide");
//            Log.e("GONZALO-RESPONSE:", "" + cad);
//            JSONArray m_jArry = new JSONArray(cad);
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                PoliticaPrecio item = new PoliticaPrecio();
//                item.setId(jo_inside.getInt("id"));
//                item.setCodRegional(jo_inside.getInt("codregional"));
//                item.setCodPromotorVenta(jo_inside.getInt("codPromotorVenta"));
//                item.setCodCliente(jo_inside.getInt("codCliente"));
//                item.setIdgrupoPoliticaPrecio(jo_inside.getInt("idGrupoPoliticaPrecio"));
//                item.setVentaBolivianos(jo_inside.getDouble("ventaBolivianos"));
//                item.setDescuentoComercial(jo_inside.getDouble("descuentoComercial"));
//                item.setDescuento(jo_inside.getDouble("descuento"));
//                item.setMontoAcceso(jo_inside.getDouble("montoAcceso"));
//                item.setCodMes(jo_inside.getInt("codMes"));
//                item.setCodGestion(jo_inside.getInt("codGestion"));
//                item.setEstado(jo_inside.getInt("estado"));
//                item.setFechaSistema( jo_inside.getString("fechaSistema"));
//                listPolPrice.add(item);
//            }
//            PoliticaPreciosController politicaPreciosController = new PoliticaPreciosController(view.getContext());
//            politicaPreciosController.actualizarPoliticaPrecios(listPolPrice);
//            listPolPrice.clear();
//
//
//            collectionType = new TypeToken<Collection<ComGrupoFidelidad>>() {}.getType();
//            Collection<ComGrupoFidelidad> listComGrupoFidelidad = gson.fromJson((String) jsonObject.get("serviceComGrupoFidelidad"), collectionType);
//            ComGrupoFidelidadController comGrupoFidelidadController = new ComGrupoFidelidadController(view.getContext());
//            comGrupoFidelidadController.actualizarComGrupoFidelidad(listComGrupoFidelidad);
//            listComGrupoFidelidad.clear();

            //Log.e("GONZALO-JSON: ", jsonObject.getString("servicePolPriceFide"));
            //Log.e("GONZALO-JSON: ", "" + listPersonal.size());
            //Log.e("GONZALO-JSON: ", "" + listPersonal.size());


        } catch (JsonIOException e) {
            cancel(true);
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            cancel(true);
            e.printStackTrace();
        } catch (JSONException e) {
            cancel(true);
            e.printStackTrace();
        }
    }
//
//    public void registrarStock(JSONObject jsonObject) {
//        try {
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//
//            Type collectionType = new TypeToken<Collection<PresentacionesProductos>>() {}.getType();
//            Collection<PresentacionesProductos> listProductos = gson.fromJson((String) jsonObject.get("serviceProducto"), collectionType);
//            presentacionesProductoController.actualizarPresentacionesProducto(listProductos);
//            listProductos.clear();
//
//            LimiteCreditoClienteController limiteCreditoClienteController = new LimiteCreditoClienteController(view.getContext());
//
//            Type collectionType2 = new TypeToken<Collection<LimiteCreditoCliente>>() {}.getType();
//            Collection<LimiteCreditoCliente> listProductos2 = gson.fromJson((String) jsonObject.get("serviceLimiteCredito"), collectionType2);
//            limiteCreditoClienteController.registroLimiteCreditoCliente(listProductos2);
//
//            listProductos.clear();
//        } catch (JsonIOException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JSONException e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
//    public void actualizarLimiteCredito(JSONObject jsonObject) {
//        try {
//            LimiteCreditoClienteController limiteCreditoClienteController = new LimiteCreditoClienteController(view.getContext());
//
//            Type collectionType = new TypeToken<Collection<LimiteCreditoCliente>>() {}.getType();
//            Collection<LimiteCreditoCliente> listProductos = gson.fromJson((String) jsonObject.get("serviceLimiteCredito"), collectionType);
//            limiteCreditoClienteController.registroLimiteCreditoCliente(listProductos);
//
//            listProductos.clear();
//        } catch (JsonIOException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JSONException e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarDatosGeneralesDespachador(JSONObject jsonObject) {
//        try {
//            Type collectionType = new TypeToken<Collection<Personal>>() {}.getType();
//            Collection<Personal> listPersonal = gson.fromJson((String) jsonObject.get("servicePersonal"), collectionType);
//
//            PersonalController personalController = new PersonalController(view.getContext());
//
//            ClienteUbicacionController clienteUbicacionController = new ClienteUbicacionController(view.getContext());
//            personalController.actualizarPersonal(listPersonal);
//            listPersonal.clear();
//
//            collectionType = new TypeToken<Collection<Cliente>>() {}.getType();
//            Collection<Cliente> listCliente = gson.fromJson((String) jsonObject.get("serviceCliente"), collectionType);
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            presentacionesProductoController.actualizarClientes(listCliente);
//            listCliente.clear();
//
//            collectionType = new TypeToken<Collection<ClienteUbicacion>>() {}.getType();
//            Collection<ClienteUbicacion> listClienteUbicacion = gson.fromJson((String) jsonObject.get("serviceClienteUbicacion"), collectionType);
//
//            clienteUbicacionController.registroClienteUbicacion(listClienteUbicacion);
//
//            listClienteUbicacion.clear();
//        } catch (JsonIOException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JSONException e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
//    public void registrarDatosGeneralesJSON2(JSONObject jsonObject) {
//        try {
//            Collection<Personal> listPersonal = (Collection<Personal>) jsonObject.get("servicePersonal");
//            PersonalController personalController = new PersonalController(view.getContext());
//            personalController.actualizarPersonal(listPersonal);
//            listPersonal.clear();
//
//            Collection<Cliente> listCliente = (Collection<Cliente>) jsonObject.get("serviceCliente");
//            PresentacionesProductoController presentacionesProductoController = new PresentacionesProductoController(view.getContext());
//            presentacionesProductoController.actualizarClientes(listCliente);
//            listCliente.clear();
//
//            Collection<PresentacionesProductos> listProductos = (Collection<PresentacionesProductos>) jsonObject.get("serviceProducto");
//            presentacionesProductoController.actualizarPresentacionesProducto(listProductos);
//            listProductos.clear();
//
//        } catch (JsonIOException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            cancel(true);
//            e.printStackTrace();
//        } catch (JSONException e) {
//            cancel(true);
//            e.printStackTrace();
//        }
//    }
//
    @Override
    protected void onPreExecute() {
        asyncTaskListener.onInit();
    }

    protected void onProgressUpdate() {
        asyncTaskListener.onProgressUpdate();
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        asyncTaskListener.onFinish(json);
    }

    @Override
    protected void onCancelled() {
        asyncTaskListener.onCancel();
    }
//
//    public static <T> T load(final InputStream inputStream, final Type typeOf) {
//        try {
//            if (inputStream != null) {
//                final Gson gson = new Gson();
//                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                return gson.fromJson(reader, typeOf);
//            }
//        } catch (final Exception e) {
//        }
//        return null;
//    }
}

