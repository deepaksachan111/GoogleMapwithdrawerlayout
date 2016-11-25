package com.noida.googlemapdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.noida.googlemapdemo.pagersliding.ViewPagerAdapter;

/**
 * Created by intex on 10/4/2016.
 */
public class DrawerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    int[] mImageResources;

    LinearLayout lnear_home_appliance;
    boolean flag = true;



    public Integer[] mImageIds = new Integer[]{R.mipmap.elecchu2, R.mipmap.eltronic_ic, R.mipmap.key_highligts_electronics, R.mipmap.ac_repai1, R.mipmap.ac_repai2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();
        showIds();
       mImageResources = new int[] {R.mipmap.elecchu2, R.mipmap.eltronic_ic, R.mipmap.key_highligts_electronics, R.mipmap.ac_repai1, R.mipmap.ac_repai2};
        setReference();


    /*    Gallery gallery = (Gallery) findViewById(R.id.gallery);
       // selectedImage=(ImageView)findViewById(R.id.imageView);
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this,0,mImageIds);
        gallery.setAdapter(galleryImageAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int text = (int) parent.getItemAtPosition(position);


                Toast.makeText(getApplicationContext(), "selected Toast:"+ text,Toast.LENGTH_LONG).show();

            }
        });*/


    }

    private void showIds(){

        final LinearLayout linearLayout_visible =(LinearLayout)findViewById(R.id.linear_visibleheader);
        final TextView textView_vislblemore =(TextView)findViewById(R.id.tv_viewmore);
        final TextView textView_visible_less=(TextView)findViewById(R.id.tv_less);
              LinearLayout lnear_home_appliance =(LinearLayout)findViewById(R.id.linearhomecleaning);
               final ImageView imageview_beauty=(ImageView)findViewById(R.id.imageview_beauty);

        lnear_home_appliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
                imageview_beauty.startAnimation(animation);
                startActivity(new Intent(getApplicationContext(),BookServiceActivity.class));
                Toast.makeText(getApplicationContext(),"Your are selecthome appliance",Toast.LENGTH_LONG).show();
            }
        });

        FrameLayout frame_layout =(FrameLayout)findViewById(R.id.framelayot_viewmore_less);

        frame_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == true) {

                    textView_vislblemore.setVisibility(View.GONE);
                    textView_visible_less.setVisibility(View.VISIBLE);
                    linearLayout_visible.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    linearLayout_visible.setAnimation(animation);
                    linearLayout_visible.animate();
                    animation.start();
                    sendScroll();
                    flag = false;
                } else {

                    textView_vislblemore.setVisibility(View.VISIBLE);
                    textView_visible_less.setVisibility(View.GONE);
                    linearLayout_visible.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    linearLayout_visible.setAnimation(animation);
                    linearLayout_visible.animate();
                    flag = true;
                }

            }
        });
    }

    private void sendScroll(){

       final ScrollView scrollView_main =(ScrollView)findViewById(R.id.scrollView_main);
        final Handler handler = new Handler();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView_main.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        }).start();*/

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView_main.fullScroll(View.FOCUS_DOWN);
            }
        },100);

    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
     /*   navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(), "Trash", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        finish();

                }
                return true;
            }
        });*/
      View header = navigationView.getHeaderView(0);
        TextView tvlogin = (TextView)header.findViewById(R.id.tv_location);
        tvlogin.setText("Currently location is Noida");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }



    public void setReference() {


        intro_images = (ViewPager)findViewById(R.id.pager_introduction);
        btnNext = (ImageButton)findViewById(R.id.btn_next);
        btnFinish = (ImageButton)findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);

              btnNext.setOnClickListener(this);
                     btnFinish.setOnClickListener(this);

        mAdapter = new ViewPagerAdapter(DrawerActivity.this, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);

        setUiPageViewController();

    }







    private void setUiPageViewController() {

         Integer[] mImageIds ;

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i],params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

         for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

                if (position + 1 == dotsCount) {
                    btnNext.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.GONE);
                }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount)
                        ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_finish:
                      intro_images.setCurrentItem((intro_images.getCurrentItem() >  dotsCount)
                              ? intro_images.getCurrentItem() -1 :0 );

                break ;
        }

    }





    public class GalleryImageAdapter extends ArrayAdapter
    {
        private Context mContext;
        public Integer[] mImageIds ;
        public GalleryImageAdapter(Context context, int res ,Integer img[])
        {
             super(context,res,img);
            mContext = context;
            mImageIds = img;

        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup)

        {
          //  view = LayoutInflater.from(getContext()).inflate(R.layout.gallery_adapter,null);
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.gallery_adapter,null);

            ImageView imageView =(ImageView)view.findViewById(R.id.gallery_adapter_iv);
            imageView.setImageResource(mImageIds[index]);

           /* // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[index]);
            i.setLayoutParams(new Gallery.LayoutParams(200, 200));

            i.setScaleType(ImageView.ScaleType.FIT_XY);
*/



            return view;
        }



    }


}
