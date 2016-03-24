package csi.fhict.org.csi_week_1;

import android.Manifest;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView list;
    List<Criminal> criminals;
    LocationManager locationManager;
    Intent intentForPending;
    PendingIntent pIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        CriminalProvider cp = new CriminalProvider(getApplicationContext());
        criminals = cp.GetCriminals();

//        setWebAndImages();

        CustomList adapter = new
                CustomList(ListActivity.this, criminals);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                goToNextScreen(criminals.get(+position));

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intentForPending = new Intent(this, ReportActivity.class);
        pIntent = PendingIntent.getActivity(getBaseContext(), 0, intentForPending, 0);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            System.out.println("CHECK DIT HIER!!!;   " + locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER));
        } catch (SecurityException se) {
            System.out.println("Security Exception!");
        }
        for (Criminal c : criminals) {
            try {
                //If the location manager detects to be in or leaving the area, go to an intent
                //The 100 is the radius and the -1 is the expiration, -1 means it never expires.
                locationManager.addProximityAlert(c.latitude, c.longitude, 100, -1, pIntent);
            }
            catch (SecurityException se) {
                System.out.println("Oops");
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 25, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                System.out.println("Location changed!");
                System.out.println("New longitude: " + location.getLongitude());
                System.out.println("New Latitude: " + location.getLatitude() + System.lineSeparator());

                for (Criminal c : criminals) {
                    Location criminalLocation = new Location("");
                    criminalLocation.setLatitude(c.latitude);
                    criminalLocation.setLongitude(c.longitude);
                    if (location.distanceTo(criminalLocation) < 100) {
                        System.out.println("You are within 100 meters of the last know location of the criminal: " + c.name);
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    public void goToNextScreen(Criminal criminal) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("criminal", criminal);
        startActivity(intent);
    }


}
