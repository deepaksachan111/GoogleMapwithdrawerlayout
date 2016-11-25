package com.noida.googlemapdemo;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
      /*  Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses  = null;
        try {
            addresses = geocoder.getFromLocation(26.442105,80.297769, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String zip = addresses.get(0).getPostalCode();
        String country = addresses.get(0).getCountryName();*/
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

  public void findlocation(View v ){

      EditText editText =(EditText)findViewById(R.id.edt_map_search);
      String location = editText.getText().toString();
      List<Address> addressList = null;
      if(location != null || !location.equals("")){

          Geocoder geocoder = new Geocoder(this);
          try {
          addressList= geocoder.getFromLocationName(location, 1);
          } catch (IOException e) {
              e.printStackTrace();
          }

          Address address = addressList.get(0);

          LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
          mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
          mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

          String city = addressList.get(0).getLocality();
          String state = addressList.get(0).getAdminArea();
          String zip = addressList.get(0).getPostalCode();
          String country = addressList.get(0).getCountryName();

      }
  }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
      LatLng india =  new LatLng(26.442105, 80.297769);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker"));

       // LatLng kanpur =  new LatLng(26.444805, 80.300199);
       // mMap.addPolyline(new PolylineOptions().add(india,new LatLng(26.442211, 80.297334),new LatLng(26.441898, 80.299056),kanpur).width(10).color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 18));
       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMyLocationEnabled(true);



    }


}
