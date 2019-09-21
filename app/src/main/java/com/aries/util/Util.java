package com.aries.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
    public static final int DATA_BASE_VERSION_ZEUS = 1;
//    public static final String ipServer = "http://";
//    public static final String urlServer = "/zeus/service/";
//
//    public static final String NAME_SERVER = "zeus/service";
//
//    static String ip = "172.16.10.119:8084/ventas";
//    public static RegionalClienteUbicacion regional_lapaz = new RegionalClienteUbicacion(-16.50715156866754d, -68.12510658055544d, 1621206, 46);
//
//    public static String pathServer(String ip, String urlService) {
//        StringBuilder stringBuffer = new StringBuilder();
//        stringBuffer.append(ipServer);
//        stringBuffer.append(ip);
//        stringBuffer.append(urlServer);
//        stringBuffer.append(urlService);
//        return stringBuffer.toString();
//    }
//
//    public static InputStream responseJSON(String url) {
//        DefaultHttpClient client = new DefaultHttpClient();
//        try {
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = client.execute(request);
//            return response.getEntity().getContent();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
    public static void copiarBD(Context context) {
        //PackageInstaller p;
        try {
            String destPath = "/data/data/" + context.getPackageName() + "/databases";
            System.out.println("destPath:" + destPath);
            File f = new File(destPath);
            System.out.println("Existe:" + f.exists());
            if (!f.exists()) {
                boolean creado = f.mkdirs();
                System.out.println("creado:" + creado);
                System.out.println("creado 2:" + creado);
                CopyDB(context.getAssets().open("aries"), new FileOutputStream(destPath + "/aries"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
//
//    public static void requestJSON(String url, List listado) {
//        DefaultHttpClient client = new DefaultHttpClient();
//        try {
//            HttpPost response = new HttpPost(url);
//
//            String json = new GsonBuilder().create().toJson(listado, List.class);
//
//            StringEntity entity = new StringEntity(json);
//            response.setEntity(entity);
//
//            response.setHeader("Accept", "application/json");
//            response.setHeader("Content-type", "application/json");
//
//            ResponseHandler responseHandler = new BasicResponseHandler();
//
//            client.execute(response, responseHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnected()) {
//            return true;
//        }
//        return false;
//    }
//
//    public static String convertInputStreamToString2(InputStream is) {
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//        String line;
//        try {
//            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        String text = "[" + sb.substring(4, sb.length());
//        Log.i("Json", text);
//        return text;
//    }
//
//    public static String convertInputStreamToString(InputStream is) {
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//        String line;
//        try {
//            br = new BufferedReader(new InputStreamReader(is));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//
    public static String getVersionName(Context context) {
        try {
            ComponentName comp = new ComponentName(context, Util.class);
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(comp.getPackageName(), 0);
            return pinfo.versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
//
//    public static int getVersionCode(Context context) {
//        try {
//            ComponentName comp = new ComponentName(context, Util.class);
//            PackageInfo pinfo = context.getPackageManager().getPackageInfo(comp.getPackageName(), 0);
//            return pinfo.versionCode;
//        } catch (NameNotFoundException e) {
//            return 0;
//        }
//    }
}
