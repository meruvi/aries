package com.aries;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AriesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if ( isExternalStorageWritable() ) {

            File appDirectory = new File( Environment.getExternalStorageDirectory() + "/MyAriesFolder" );
            File logDirectory = new File( appDirectory + "/log" );

            //SimpleDateFormat  f=new SimpleDateFormat("yyyyMMdd_HHmmss");
            SimpleDateFormat  f=new SimpleDateFormat("yyyyMMdd");
            String fileName = "logcat" + f.format(new Date()) + ".txt";

            File logFile = new File( logDirectory, fileName );

            // create app folder
            if ( !appDirectory.exists() ) {
                appDirectory.mkdir();
            }

            // create log folder
            if ( !logDirectory.exists() ) {
                logDirectory.mkdir();
            }

            //File theDir = new File(filePath+"Process/");
            cleanDirectory(logDirectory, fileName);

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec( "logcat -c");
                //process = Runtime.getRuntime().exec( "logcat -f " + logFile + " *:S MyActivity:D MyActivity2:D");
                process = Runtime.getRuntime().exec(new String[]{"logcat", "-f", logFile+ "", "MyAppTAG:V", "*:E"});
                //process = Runtime.getRuntime().exec(new String[]{"logcat", "-f", logFile+ "", "MyAppTAG:V", "*:V"});
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else if ( isExternalStorageReadable() ) {
            // only readable
        } else {
            // not accessible
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals( state ) ) {
            return true;
        }
        return false;
    }

    /* Eliminar anteriores archivos logs */
    public void cleanDirectory(File dir, String fName) {
        for (File file: dir.listFiles()) {
            if (file.getName().equals(fName)) {
                //do nothing
            } else {
                //delete file
                file.delete();
            }
        }
    }
}