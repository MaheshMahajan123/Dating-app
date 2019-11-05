package com.example.tinderchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tinderchat.chat.Chats;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class OnChat_image_click extends AppCompatActivity {
ImageView chatprofileImage;
TextView chatname,status,Birthhdate,editText_address,editText_school,edittext_companey,chatHobbies;
FloatingActionButton flootingbutton;
    private String matchId;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_chat_image_click);
        chatprofileImage=findViewById(R.id.chatprofileImage);
        chatname=findViewById(R.id.chatname);
        chatHobbies=findViewById(R.id.chatHobbies);
        edittext_companey=findViewById(R.id.edittext_companey);
        editText_school=findViewById(R.id.editText_school);
        editText_address=findViewById(R.id.editText_address);
        Birthhdate=findViewById(R.id.Birthhdate);
        status=findViewById(R.id.status);
        flootingbutton=findViewById(R.id.flootingbutton);

        matchId = getIntent().getExtras().getString("matchiddd");
        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(matchId);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("Image") != null) {
                        String imag = map.get("Image").toString();
                        String im = new String(imag);
                        Glide.with(getApplicationContext()).load(im).into(chatprofileImage);
                    }
                    if (map.get("city") != null) {
                        String cit = map.get("city").toString();
                        editText_address.setText(cit);
                    }
                    if (map.get("hobies") != null) {
                        String h = map.get("hobies").toString();
                        chatHobbies.setText(h);
                    }
                    if (map.get("status") != null) {
                        String a = map.get("status").toString();
                        status.setText(a);
                    }
                    if (map.get("date") != null) {
                        String d = map.get("date").toString();
                        Birthhdate.setText(d);
                    }
                    if (map.get("name") != null) {
                        String n = map.get("name").toString();
                        chatname.setText(n);
                    }
                    if (map.get("school") != null) {
                        String n = map.get("school").toString();
                        editText_school.setText(n);
                    }
                    if (map.get("companey") != null) {
                        String n = map.get("companey").toString();
                        edittext_companey.setText(n);
                    }

                } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});

        flootingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(OnChat_image_click.this, Chats.class);
                Bundle b=new Bundle();
                b.putString("matchid",matchId);
                b.putString("matchidname",chatname.getText().toString());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    }


