package com.example.crazziee.instagram.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.crazziee.instagram.R;
import com.example.crazziee.instagram.Util.UniversalImageLoader;

/**
 * Created by Crazziee on 12/27/2017.
 */

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ImageView mProfilePhoto;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
       mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);

        setProfileImage();

        ImageView backarrow = (ImageView) view.findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigate to profileActivity");
                getActivity().finish();
            }
        });
        return view;
    }



    private void setProfileImage(){
        Log.d(TAG, "setProfileImage:setting profile image");
        String imgURL = "https://www.google.com.np/search?q=android+image&tbm=isch&source=iu&ictx=1&fir=vbD2HWlnuLGbeM%253A%252CdOHIK-V94Am4vM%252C_&usg=__--8iYP7w0cHGkE9i1qw7LE8k_ME%3D&sa=X&ved=0ahUKEwi5qd6e6dHYAhXBs48KHZrXC1MQ9QEIMTAE#imgrc=vbD2HWlnuLGbeM:";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null,"");
    }
}

