package com.noida.googlemapdemo;

/**
 * Created by intex on 10/4/2016.
 */

import android.app.Activity;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AndroidFromLocation extends Activity {
double LATITUDE = 26.441898;
        double LONGITUDE =80.299056;
        private GoogleMap mMap;
         TextView myAddress;
        Handler handler = new Handler();
        List<Address> addresses = null;
        String dsdsfdsdf;
        String indiStr ;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_location);
        TextView myLatitude = (TextView)findViewById(R.id.mylatitude);
        TextView myLongitude = (TextView)findViewById(R.id.mylongitude);
        myAddress = (TextView)findViewById(R.id.myaddress);

        myLatitude.setText("Latitude: " + String.valueOf(LATITUDE));
        myLongitude.setText("Longitude: " + String.valueOf(LONGITUDE));






                new Thread(){
                        public void run(){
                                try {
                                        addresses = getStringFromLocation(LATITUDE, LONGITUDE);

                                    if(addresses != null) {

                                        String city = addresses.get(0).getLocality();
                                        String state = addresses.get(0).getAdminArea();
                                        String zip = addresses.get(0).getPostalCode();
                                        String country = addresses.get(0).getCountryName();
                                    }

                                } catch (IOException e) {
                                        e.printStackTrace();
                                } catch (JSONException e) {
                                        e.printStackTrace();
                                }
                        }

                }.start();




}





        public  List<Address> getStringFromLocation(double lat, double lng)
                throws ClientProtocolException, IOException, JSONException {

                String address = String
                        .format(Locale.ENGLISH, "http://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&sensor=true&language="
                                + Locale.getDefault().getCountry(), lat, lng);
                HttpGet httpGet = new HttpGet(address);
                HttpClient client = new DefaultHttpClient();
                HttpResponse response;
                StringBuilder stringBuilder = new StringBuilder();

                List<Address> retList = null;

                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                stringBuilder.append(EntityUtils.toString(entity));
              /*
                InputStream stream = entity.getContent();
                int b;
                while ((b = stream.read()) != -1) {
                        stringBuilder.append((char) b);
                }*/


                JSONObject jsonObject = new JSONObject(stringBuilder.toString());

                retList = new ArrayList<Address>();

                if ("OK".equalsIgnoreCase(jsonObject.getString("status"))) {
                        JSONArray results = jsonObject.getJSONArray("results");
                        JSONObject Jsonres1   = results.getJSONObject(0);
                        String ss = Jsonres1.getString("formatted_address");

                        final String dsdsfdsdf = ss;
                        for (int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);
                                 indiStr = result.getString("formatted_address");
                                 Address addr = new Address(Locale.getDefault());
                                addr.setAddressLine(0, indiStr);
                               handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                myAddress.setText( dsdsfdsdf);
                                        }
                                });



                                retList.add(addr);


                        }


                }


                return retList;
        }

        }