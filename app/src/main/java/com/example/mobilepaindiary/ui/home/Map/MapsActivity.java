package com.example.mobilepaindiary.ui.home.Map;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mobilepaindiary.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.example.mobilepaindiary.MyApplication.getContext;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        String address = intent.getStringExtra("extra String");


        LatLng lat = getLocationFromAddress(address);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        if(lat == null){
            Toast.makeText(getContext() ,"failed" , Toast.LENGTH_SHORT).show();

        }
        else{
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lat));
            mMap.addMarker(new MarkerOptions()
                    .position(lat)
                    .title("Marker on  the input")
                    .snippet("Latitude: " + lat.latitude + ", Longitude: " + lat.longitude));


            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }

    }

    private LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(MapsActivity.this);
        List<Address> address;
        LatLng p1 = new LatLng(-37.9161872, 145.140213);;
        try {
            address = coder.getFromLocationName(strAddress, 5);

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p1;


    }

}