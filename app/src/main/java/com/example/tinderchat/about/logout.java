package com.example.tinderchat.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.tinderchat.MainActivity;
import com.example.tinderchat.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

//https://developers.google.com/admob/android/banner?hl=en-US
public class logout extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    CardView card242;
private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        card242 = findViewById(R.id.card242);
        try {
            MobileAds.initialize(this, "ca-app-pub-4437106626767375~7711435491");
        }
        catch (Exception e)
        {

        }
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

        sharedPreferences =getSharedPreferences("Blood",MODE_PRIVATE);
        String a=(sharedPreferences.getString("my_username",null));
        if(a==null)
        {
            Intent i=new Intent(logout.this, MainActivity.class);
            startActivity(i);
        }


        card242.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("Blood", MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(logout.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
