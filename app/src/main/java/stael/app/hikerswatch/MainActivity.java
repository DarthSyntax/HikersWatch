package stael.app.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView locationInfo;
    Location currentLocation;
    Geocoder geocoder;
    String locationDisplay;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                String address = "";
                try {
                    List<Address> listAddresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                    if(listAddresses != null && listAddresses.size() > 0) {
                        address = "";

                        //Street
                        if (listAddresses.get(0).getThoroughfare() != null) {
                            address += listAddresses.get(0).getThoroughfare() +  " ";
                        }

                        //City
                        if(listAddresses.get(0).getLocality() != null) {
                            address += listAddresses.get(0).getLocality() + "\n";
                        }

                        //Zip Code
                        if(listAddresses.get(0).getPostalCode() != null) {
                            address += listAddresses.get(0).getPostalCode() + " ";
                        }

                        //State
                        if(listAddresses.get(0).getAdminArea() != null) {
                            address += listAddresses.get(0).getAdminArea() + "\n";
                        }

                        if(listAddresses.get(0).getPhone() != null) {
                            address += listAddresses.get(0).getPhone();
                        }
                    }
                    locationDisplay = "Latitude: " + currentLocation.getLatitude() +
                            "\n\nLongitude: " + currentLocation.getLongitude() +
                            "\n\nAltitude: " + currentLocation.getAltitude() +
                            "\n\nAccuracy: " + currentLocation.getAccuracy() +
                            "\n\nAddress: " + address;
                    locationInfo.setText(locationDisplay);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch(Exception e){}
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        locationInfo = (TextView) findViewById(R.id.locationInfo);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String addressChanged = "";
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addressList != null && addressList.size() > 0) {

                        if(addressList.get(0).getThoroughfare() != null) {
                            addressChanged += addressList.get(0).getThoroughfare() + " ";
                        }

                        if(addressList.get(0).getLocality() != null) {
                            addressChanged += addressList.get(0).getLocality() + "\n";
                        }

                        if(addressList.get(0).getPostalCode() != null) {
                            addressChanged += addressList.get(0).getPostalCode() + " ";
                        }

                        if(addressList.get(0).getAdminArea() != null) {
                            addressChanged += addressList.get(0).getAdminArea() + "\n";
                        }

                        if(addressList.get(0).getPhone() != null) {
                            addressChanged += addressList.get(0).getPhone();
                        }
                    }

                    locationDisplay = "Latitude: " + location.getLatitude() +
                            "\n\nLongitude: " + location.getLongitude() +
                            "\n\nAltitude: " + location.getAltitude() +
                            "\n\nAccuracy: " + location.getAccuracy() +
                            "\n\nAddress: " + addressChanged;
                    locationInfo.setText(locationDisplay);

                }catch (Exception e) {
                    e.printStackTrace();
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
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            String address = "";
            try {
                List<Address> listAddresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                if(listAddresses != null && listAddresses.size() > 0) {
                    address = "";

                    //Street
                    if (listAddresses.get(0).getThoroughfare() != null) {
                        address += listAddresses.get(0).getThoroughfare() +  " ";
                    }

                    //City
                    if(listAddresses.get(0).getLocality() != null) {
                        address += listAddresses.get(0).getLocality() + "\n";
                    }

                    //Zip Code
                    if(listAddresses.get(0).getPostalCode() != null) {
                        address += listAddresses.get(0).getPostalCode() + " ";
                    }

                    //State
                    if(listAddresses.get(0).getAdminArea() != null) {
                        address += listAddresses.get(0).getAdminArea() + "\n";
                    }

                    if(listAddresses.get(0).getPhone() != null) {
                        address += listAddresses.get(0).getPhone();
                    }

                }

                locationDisplay = "Latitude: " + currentLocation.getLatitude() +
                        "\n\nLongitude: " + currentLocation.getLongitude() +
                        "\n\nAltitude: " + currentLocation.getAltitude() +
                        "\n\nAccuracy: " + currentLocation.getAccuracy() +
                        "\n\nAddress: " + address;
                locationInfo.setText(locationDisplay);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
