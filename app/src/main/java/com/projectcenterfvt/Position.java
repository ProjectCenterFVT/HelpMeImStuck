package com.projectcenterfvt.helpmeimstuck;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Roman on 20.11.2017.
 */


public class Position extends AsyncTask<Void, Void, Location> implements LocationListener {

    static Location imHere; //самая последняя информация о местоположении
    static Context context;

    public Position(Context context) {
        Position.context = context;
    }

    public Position() {

    }

    @Override
    public void onLocationChanged(Location location) {
        imHere = location;
        if (location == null) {
            Log.d("Locational", " Не определилась позиция ");
        }
        Log.d("Locational", "Определилась позиция " + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("Locational", "Доступен GPS " + s);

    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("Locational", "Не доступен GPS " + s);
    }

    @Override
    protected Location doInBackground(Void... voids) {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new Position();
        Looper.prepare();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return imHere;
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);// время в мс, метраж в метрах
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("Locational", "статус : " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        return imHere;
    }

    @Override
    protected void onPostExecute(Location location) {
        super.onPostExecute(location);
        if (location != null)
            Log.d("Locational", "Определилась позиция: широта  = " + location.getLatitude() + " долгота = " + location.getLongitude());
    }
}
