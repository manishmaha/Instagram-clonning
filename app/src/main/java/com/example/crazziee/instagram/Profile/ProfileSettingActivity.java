package com.example.crazziee.instagram.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.crazziee.instagram.R;
import com.example.crazziee.instagram.Util.BottomNavigationViewHelper;
import com.example.crazziee.instagram.Util.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by Crazziee on 12/27/2017.
 */

class ProfileSettingActivity extends AppCompatActivity{
    public static final String TAG = "ProfileSettingActivity";
    private Context mcontext=ProfileSettingActivity.this;
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private static final int ACTIVITY_NUM = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        Log.d(TAG, "onCreate: started");
        mViewPager =(ViewPager)findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout)findViewById(R.id.relLayout1);

        setupSettingList();
        setupBottomNavigationView();
        setupFragments();

        //setting for backarrow
        ImageView backarrow = (ImageView)findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: backarrow is clicked");
                finish();
            }
        });
    }



   private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.Edit_Profile));
       pagerAdapter.addFragment(new SignOutFragment(),getString(R.string.Sign_Out));

    }

    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment"+fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingList() {
        Log.d(TAG, "setupSettingList: initializing account setting list");
        ListView listView= (ListView)findViewById(R.id.lvAccounSettings);
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.Edit_Profile));
        options.add(getString(R.string.Sign_Out));

        ArrayAdapter adapter = new ArrayAdapter(mcontext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment"+position);
                setViewPager(position);
            }
        });
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
