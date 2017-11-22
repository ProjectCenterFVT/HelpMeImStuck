package com.projectcenterfvt.helpmeimstuck;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Roman on 20.11.2017.
 */


public class Position2 {

    static Context context;
    Location imHere; //самая последняя информация о местоположении
    private LocationManager locationManager;
    private TextView textView;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            imHere = location;
            Log.d("Locational", "Определилась позиция: широта  = " + location.getLatitude() + "  долгота = " + location.getLongitude());
            textView.setText("Определилась позиция: широта  = " + location.getLatitude() + " долгота = " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d("Locational", "status недоступно:" + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d("Locational", "status доступно :" + s);

        }
    };

    public Position2(Context context, TextView textView) {
        Position2.context = context;
        this.textView = textView;
    }

    public void setUp() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        Log.d("Locational", "проверка : " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }

}
