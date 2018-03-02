package com.example.crazziee.instagram.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.crazziee.instagram.R;
import com.example.crazziee.instagram.Util.BottomNavigationViewHelper;
import com.example.crazziee.instagram.Util.GridImageAdapter;
import com.example.crazziee.instagram.Util.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by Crazziee on 12/26/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private Context mcontext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;
    private static final int NUM_GRID_COLUMN = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");

        setupBottomNavigationView();
        setupToolBar();
        setupActivityWidgets();
        setupProfileImage();
        tempGridSetup();
    }

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView)findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account sttings");
                Intent intent = new Intent(mcontext, ProfileSettingActivity.class);
                mcontext.startActivity(intent);
            }
        });
    }

    private void tempGridSetup(){
        ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("");
        //now add your image urls from desktop or from the net;
setupImageGrid(imgUrls);
    }

    private void setupImageGrid(ArrayList<String> ImgUrls){
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth  = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMN ;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mcontext, R.layout.layout_grid_imageview, "", ImgUrls);

        gridView.setAdapter(adapter);
    }

    private void setupProfileImage(){
        Log.d(TAG, "setupProfileImage: setting profile image");
        String imgURL = "https://www.google.com.np/search?q=android+image&tbm=isch&source=iu&ictx=1&fir=vbD2HWlnuLGbeM%253A%252CdOHIK-V94Am4vM%252C_&usg=__--8iYP7w0cHGkE9i1qw7LE8k_ME%3D&sa=X&ved=0ahUKEwi5qd6e6dHYAhXBs48KHZrXC1MQ9QEIMTAE#imgrc=vbD2HWlnuLGbeM:";
        UniversalImageLoader.setImage(imgURL, profilePhoto, null,"");

    }

    private void setupActivityWidgets(){
        mProgressBar=(ProgressBar)findViewById(R.id.prpofileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }


}
