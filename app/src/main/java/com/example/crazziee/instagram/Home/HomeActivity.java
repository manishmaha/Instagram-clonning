package com.example.crazziee.instagram.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crazziee.instagram.R;
import com.example.crazziee.instagram.Util.BottomNavigationViewHelper;
import com.example.crazziee.instagram.Util.SectionPagerAdapter;
import com.example.crazziee.instagram.Util.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mcontext=HomeActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate:starting");
        ViewPager viewPager = (ViewPager)  findViewById(R.id.container);
        initImageLoader();
        setupBottomNavigationView();
        setupViewPager(viewPager);

    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mcontext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    public void setupViewPager(ViewPager viewPager){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());//Tab at 0 position
        adapter.addFragment(new HomeFragment());//Tab at 1 position
        adapter.addFragment(new MessageFragment());//Tab at 2 position
        viewPager.setAdapter(adapter);

        //setting up viewPager adapter with tablayout
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

     tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_instagram);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_arrow);

    }


    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx =(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}

