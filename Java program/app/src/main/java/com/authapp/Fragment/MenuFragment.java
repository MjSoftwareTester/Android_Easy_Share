package com.authapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.authapp.API.APIResource;
import com.authapp.LoginActivity;
import com.authapp.PasswordChangeActivity;
import com.authapp.R;
import com.authapp.UpdateDPActivity;
import com.authapp.UserProfileActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static android.content.Context.MODE_PRIVATE;
import static com.authapp.LoginActivity.Email;
import static com.authapp.LoginActivity.MyPREFERENCES;
import static com.authapp.LoginActivity.Name;
import static com.authapp.LoginActivity.UserImage;


public class MenuFragment extends Fragment
{
    LinearLayout tv1;
    LinearLayout tv2;
    LinearLayout tv5;

    Context context;

    ImageView imgProfile;
    TextView tvName,tvEmail;

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        tv1 = (LinearLayout) view.findViewById(R.id.lMenu1);
        tv2 = (LinearLayout) view.findViewById(R.id.lMenu2);
        tv5 = (LinearLayout) view.findViewById(R.id.lMenu5);

        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);


        tvName = (TextView) view.findViewById(R.id.tvName);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvEmail.setVisibility(View.GONE);
        imgProfile = (ImageView) view.findViewById(R.id.imgUser);

        tvName.setText("Welcome, \n"+sharedPreferences.getString(Name,"User"));

        Glide.with(getActivity())
                .load(APIResource.BASE_URL+ sharedPreferences.getString(UserImage,""))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.user_default)
                .into(imgProfile);

        tvEmail.setText(sharedPreferences.getString(Email,""));

        listener();
        android.util.Log.e("Rittz",APIResource.BASE_URL + sharedPreferences.getString(UserImage,""));

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        tv5.setVisibility(View.VISIBLE);

        tvName.setText("Welcome, \n"+sharedPreferences.getString(Name,"User"));
        Glide.with(getActivity())
                .load(APIResource.BASE_URL + sharedPreferences.getString(UserImage,""))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.user_default)
                .into(imgProfile);

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    private void listener()
    {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(context, UserProfileActivity.class);
                    startActivity(i);


            }
        });


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(context, PasswordChangeActivity.class);
                startActivity(i);
            }
        });



        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(context, UpdateDPActivity.class);
                startActivity(i);
            }
        });


        tv5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent Home = new Intent(getActivity(), LoginActivity.class);
                startActivity(Home);
                getActivity().finish();
            }
        });

    }


}
