package com.example.tinderchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tinderchat.Add_images.All_Images;
import com.example.tinderchat.Add_images.ImageAdapter;
import com.example.tinderchat.Add_images.Image_Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Card_Click extends AppCompatActivity {
TextView name,city,hobies,birthdate,status,about;
String Idname;
ImageView image,ImageView_back;
DatabaseReference userdb;


    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private List<Image_Upload> mUploads;
    private StorageReference mStorage;
    private ValueEventListener mDBListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card__click);
        name=findViewById(R.id.matchesname);
        about=findViewById(R.id.about);
        city=findViewById(R.id.city);
        status=findViewById(R.id.status);
        image=findViewById(R.id.image);
        ImageView_back=findViewById(R.id.ImageView_back);
       hobies=findViewById(R.id.hobies);
        birthdate=findViewById(R.id.Bityhdate);
        Idname=getIntent().getExtras().getString("E");
        userdb= FirebaseDatabase.getInstance().getReference().child("Users").child(Idname);
       DatabaseReference user= FirebaseDatabase.getInstance().getReference().child("Users").child(Idname).child("All_Images");

        ImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Card_Click.this,Start_tab.class);
                startActivity(i);
            }
        });

        mRecyclerView = findViewById(R.id.click_recycler_vieww);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);


        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter(Card_Click.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mDBListener=user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Image_Upload upload = postSnapshot.getValue(Image_Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Card_Click.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //   mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
                                                  @Override
                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                      if (dataSnapshot.exists()) {
                                                          Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                          if (map.get("name") != null) {
                                                              String n = map.get("name").toString();
                                                              name.setText(n);
                                                          }
                                                          if (map.get("city") != null) {
                        String   cit = map.get("city").toString();
                        city.setText(cit);

                    } if (map.get("hobies") != null) {
                        String h = map.get("hobies").toString();
                        hobies.setText(h);
                    } if (map.get("about") != null) {
                        String a = map.get("about").toString();
                        about.setText(a);
                    } if (map.get("date") != null) {
                        String d = map.get("date").toString();
                        birthdate.setText(d);

                    } if (map.get("status") != null) {
                        String d = map.get("status").toString();
                        status.setText(d);
                    } if (map.get("Image") != null) {
                        String imag = map.get("Image").toString();
                        Glide.with(getApplicationContext()).load(imag).into(image);
                        // image.(R.drawable.ic_red_eye24dp)
                    } } }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}


