package com.example.p369;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    String[] Permissions = {
            Manifest.permission.CALL_PHONE,
    };
    ActivityCompat.requestPermissions(this, Permissions, 101);


    BroadcastReceiver boBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            ConnectivityManager connectivityManager = null;
            NetworkInfo mobile = null;
            NetworkInfo wifi = null;
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobile != null && mobile.isConnected()) {
                    Toast.makeText(context, "mobile", Toast.LENGTH_SHORT).show();
                } else if (wifi != null && wifi.isConnected()) {
                    Toast.makeText(context, "wifi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "not Conn", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    registerReceiver(boBroadcastReceiver, intentFilter);
}
    // 권한 부여
    public void onClick(View v) {
        int permission = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent intent = new Intent();
        if(permission == PackageManager.PERMISSION_GRANTED){
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-6737-0122"));
            startActivity(intent);
        }else{
            Toast.makeText(this, "권한부여가 안되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
