package com.noida.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linew);

        ImageView imageView =(ImageView)findViewById(R.id.imageview);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
            }
        });


          ///View v =   LayoutInflater.from(getBaseContext()).inflate(R.layout.content_drawer,null);
         ///  LayoutInflater layoutInflater = getLayoutInflater();
        ///View vv=layoutInflater.inflate(R.layout.content_drawer,null);
       /// linearLayout.addView(v);



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getloction(View view) {
        startActivity(new Intent(getApplicationContext(), AndroidFromLocation.class));
    }

    public void GoMap(View view) {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }

    public void startZoomInAnimation(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
        imageView.startAnimation(animation);
    }
}
