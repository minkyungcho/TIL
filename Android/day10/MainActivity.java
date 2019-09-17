package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker, marker;

    TextView textView, textView2;

    double lat, lon;
    double pointLat, pointLon;
    ArrayList<LatLng> points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = new ArrayList<>();

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String str = intent.getStringExtra("ID");
        textView.setText(str+"님 환영합니다.");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;
                map.setMyLocationEnabled(true);
                startLocationService();
            }
        });
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
        };
        ActivityCompat.requestPermissions(this, permissions, 101);
        int check =
                PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(check == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,
                    "ACCEPT", Toast.LENGTH_SHORT).show();

//            return;
        }else{
            Toast.makeText(this,
                    "DENY", Toast.LENGTH_SHORT).show();
//            return;
        }
    } // end onCreate

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        showMyLocationMarker(curPoint);
//        getNewPoint(latitude, longitude);
    }

    private void showMyLocationMarker(LatLng curPoint) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(curPoint);
        }
    }


    public void startLocationService() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근위치-> Lat : " + latitude + "\nLong : " + longitude;
                showCurrentLocation(latitude,longitude);
                textView2.setText(message);
                pointLat = latitude;
                pointLon = longitude;
//                getNewPoint(latitude, longitude);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 1;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);

            Toast.makeText(getApplicationContext(), "내 위치 확인 요청함", Toast.LENGTH_SHORT).show();

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            showCurrentLocation(latitude,longitude);
            String message = "내 위치-> Lat : "+ latitude + "\nLong : "+ longitude;
            textView2.setText(message);
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }
    public void clickPressBt(View view){
        // 버튼 누르면 현재 위치 주변으로 특정 위치 마커로 표시하기
//        startLocationService();

        getNewPoint(pointLat, pointLon);
    }

    private void getNewPoint(double lat, double lon) {
        String url =  "http://70.12.60.111/webview/point.jsp?lat="+lat+"&lon="+lon;
        MainActivity.HttpTask httpTask = new MainActivity.HttpTask(url);
        httpTask.execute();
    }

    class HttpTask extends AsyncTask<String, Void, String> {
        String url;

        public HttpTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i=0; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    double latt = jo.getDouble("lat");
                    double lonn = jo.getDouble("lon");
                    LatLng latlng = new LatLng(latt, lonn);
                    points.add(latlng);
                    Log.i("####JSONtest", latlng.latitude+" "+latlng.longitude);
                }
                LatLng curpoint = new LatLng(lat, lon);
                showLocationLists(points);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void showLocationLists(ArrayList<LatLng> points) {
        int p = points.size();
        map.clear();
        for(int i=0; i<p; i++){
            marker = new MarkerOptions();
            marker.position(points.get(i));
            marker.title("주변 위치");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapflag));
            map.addMarker(marker);
        }
    }
}
