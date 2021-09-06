package net.znordic.findmyringscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyScanner();
    }

    public void notifyScanner() {


        Toast.makeText(getApplicationContext(), "Finding ring scanner...", Toast.LENGTH_SHORT).show();



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLEDtoRed();
                shutdownApp();
            }
        }, 4000);


        Intent i = new Intent();
        Bundle bundleNotify = new Bundle();
        Bundle bundleNotificationConfig = new Bundle();
        i.setAction("com.symbol.datawedge.api.ACTION");

        bundleNotificationConfig.putString("DEVICE_IDENTIFIER", "BLUETOOTH_RS5100");
        bundleNotificationConfig.putIntArray("NOTIFICATION_SETTINGS", new int[]{58,57});
        bundleNotify.putBundle("NOTIFICATION_CONFIG", bundleNotificationConfig);

        i.putExtra("com.symbol.datawedge.api.notification.NOTIFY", bundleNotify);
        this.sendBroadcast(i);




    }

    public void setLEDtoRed(){
        Intent i = new Intent();
        Bundle bundleNotify = new Bundle();
        Bundle bundleNotificationConfig = new Bundle();
        i.setAction("com.symbol.datawedge.api.ACTION");

        bundleNotificationConfig.putString("DEVICE_IDENTIFIER", "BLUETOOTH_RS5100");
        bundleNotificationConfig.putIntArray("NOTIFICATION_SETTINGS", new int[]{60});
        bundleNotify.putBundle("NOTIFICATION_CONFIG", bundleNotificationConfig);

        i.putExtra("com.symbol.datawedge.api.notification.NOTIFY", bundleNotify);
        this.sendBroadcast(i);
    }

    public void shutdownApp(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                moveTaskToBack(false);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        }, 1000);
    }


}
