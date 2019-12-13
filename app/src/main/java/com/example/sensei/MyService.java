package com.example.sensei;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    int id;
    Thread tHilo;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        id = intent.getIntExtra("id", 0);
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                Log.w("onStart: ", "Hasta aquí si llegué");
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Log.w("prueba servicio: ", "id: " + id);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tHilo.start();
    }
}
