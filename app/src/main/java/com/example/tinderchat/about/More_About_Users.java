package com.example.tinderchat.about;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tinderchat.Add_images.All_Images;
import com.example.tinderchat.R;
import com.example.tinderchat.Start_tab;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class More_About_Users extends AppCompatActivity {
ImageView image1,Image,Image3,Image4,Image5,Image6,Image7,Image8,Image2,Image9;
ImageView cross_red1,cross_red2,cross_red3,cross_red4,cross_red5,cross_red6,cross_red7,cross_red8,cross_red9,backarrow;
EditText edittext_About,edittext_jontitle,edittext_companey,editText_school,editText_address;
Button buttonupdate;
    String currentuserId;
    private String G = "moreAbouts";
    public int a;
    DatabaseReference mDatabaseRef,Updatettt;
    TextView textviewm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more__about__users);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Updatettt = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuserId);

        image1 = findViewById(R.id.Image1);Image2 = findViewById(R.id.Image2);Image4 = findViewById(R.id.Image4);Image5 = findViewById(R.id.Image5);Image3 = findViewById(R.id.Image3);Image6 = findViewById(R.id.Image6);Image7 = findViewById(R.id.Image7);Image8 = findViewById(R.id.Image8);Image9 = findViewById(R.id.Image9);
        ////////////////////////////////////////////////////////////////////
        cross_red1 = findViewById(R.id.cross_red1);cross_red2 = findViewById(R.id.cross_red2);cross_red3 = findViewById(R.id.cross_red3);cross_red4 = findViewById(R.id.cross_red4);cross_red5 = findViewById(R.id.cross_red5);cross_red6 = findViewById(R.id.cross_red6);cross_red7 = findViewById(R.id.cross_red7);cross_red8 = findViewById(R.id.cross_red8);cross_red9 = findViewById(R.id.cross_red9);
/****************************************************************************/
        edittext_About=findViewById(R.id.edittext_About);
        edittext_jontitle=findViewById(R.id.edittext_jontitle);
        edittext_companey=findViewById(R.id.edittext_companey);
        editText_school=findViewById(R.id.editText_school);
        editText_address=findViewById(R.id.editText_address);
        buttonupdate=findViewById(R.id.buttonupdate);

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });
        Updatettt.addListenerForSingleValueEvent(new ValueEventListener() {
                                                             @Override
                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                 if (dataSnapshot.exists()) {
                                                                     Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                                     if (map.get("Today_Status") != null) {
                                                                         String cit = map.get("Today_Status").toString();
                                                                         edittext_About.setText(cit);
                                                                     }
                                                                     else
                                                                     {

                                                                     }
                                                                     if (map.get("Job") != null) {
                                                                         String h = map.get("Job").toString();
                                                                         edittext_jontitle.setText(h);
                                                                     } else
                                                                     {

                                                                     }
                                                                     if (map.get("companey") != null) {
                                                                         String a = map.get("companey").toString();
                                                                         edittext_companey.setText(a);
                                                                     } else
                                                                     {

                                                                     }
                                                                     if (map.get("school") != null) {
                                                                         String d = map.get("school").toString();
                                                                         editText_school.setText(d);
                                                                     } else
                                                                     {

                                                                     }
                                                                     if (map.get("address") != null) {
                                                                         String n = map.get("address").toString();
                                                                         editText_address.setText(n); } else
                                                                     {

                                                                     } }
                                                             else
                                                                 {
                                                                 }
                                                             }
            @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

/*****************************************************************************************/

        textviewm=findViewById(R.id.textviewmore);
        backarrow=findViewById(R.id.backarrow);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId).child("All_Images");


        textviewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(More_About_Users.this, About_User.class);
                startActivity(i);
            }
        });
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(More_About_Users.this, Start_tab.class);
startActivity(i);
            }
        });

      /////////////////////////////////////////////////////////////////////////////////////////////////////////
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a = 1;
                    Log.d(G,"onclick");
                    clickcheck();                }
            });
                Image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 2;
                        clickcheck();
                    }
                });Image3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 3;
                        clickcheck();}
                });
                Image4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 4;
                        clickcheck();
                    }
                });Image5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 5;
                        clickcheck();
                    }
                });Image6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 6;
                        clickcheck();
                    }
                });Image7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 7;
                        clickcheck();}
                });Image8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 8;
                        clickcheck();
                    }
                });Image9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a = 9;
                        clickcheck();
                    }});
            cross_red1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabaseRef.child("1").removeValue();
                    Toast.makeText(More_About_Users.this, "Delete 1", Toast.LENGTH_SHORT).show();
                    cross_red1.setVisibility(View.INVISIBLE);
                    Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(image1);
               }});

               cross_red2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(More_About_Users.this, "Delete 2", Toast.LENGTH_SHORT).show();
                    mDatabaseRef.child("2").removeValue();
                    cross_red2.setVisibility(View.INVISIBLE);
                    Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image2);
               }});
        cross_red3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 3", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("3").removeValue();
                cross_red3.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image3);
            }});
        cross_red4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 4", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("4").removeValue();
                cross_red4.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image4);
            }});
        cross_red5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 5", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("5").removeValue();
                cross_red5.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image5);
            }});
        cross_red6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 6", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("6").removeValue();
                cross_red6.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image6);
            }});
        cross_red7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 7", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("7").removeValue();
                cross_red7.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image7);
            }});
        cross_red8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 8", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("8").removeValue();
                cross_red8.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image8);
            }});
        cross_red9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More_About_Users.this, "Delete 9", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("9").removeValue();
                cross_red9.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.backgrond).into(Image9);
            }});
    }



            /************************************************************************/
    private void saveUserInformation()
    {
        String status=edittext_About.getText().toString();
        String jobt=edittext_jontitle.getText().toString();
        String companeye=edittext_companey.getText().toString();
        String schooll=editText_school.getText().toString();
        String mDisplayDatee=editText_address.getText().toString();

        Map userInfoo=new HashMap();
        userInfoo.put("Today_Status",status);
        userInfoo.put("Job",jobt);
        userInfoo.put("companey",companeye);
        userInfoo.put("school",schooll);
        userInfoo.put("address",mDisplayDatee);

        Toast.makeText(this, "update sucessfull", Toast.LENGTH_SHORT).show();
        Updatettt.updateChildren(userInfoo);
    }
/***************************************************************************************/
    @Override
    protected void onResume() {
        //isInFront = true;
        super.onResume();
        position1();
        position2();
        position3();
        position3();
        position4();
        position5();
        position6();
        position7();
        position8();
        position9();
    }
               public void clickcheck()
               {
                   Intent i = new Intent(More_About_Users.this, All_Images.class);
                   startActivity(i);
}
    private void position1() {
        mDatabaseRef.child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image).into(image1);
                        cross_red1.setVisibility(View.VISIBLE);
                    } }
            else
                {
                }
            }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position2() {
        mDatabaseRef.child("2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image2 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image2).into(Image2);
                        cross_red2.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }

    private void position3() {
        mDatabaseRef.child("3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image3 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image3).into(Image3);
                        cross_red3.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position4() {
        mDatabaseRef.child("4").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image4 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image4).into(Image4);
                        cross_red4.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position5() {
        mDatabaseRef.child("5").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image5 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image5).into(Image5);
                        cross_red5.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position6() {
        mDatabaseRef.child("6").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image6 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image6).into(Image6);
                        cross_red6.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position7() {
        mDatabaseRef.child("7").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image7 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image7).into(Image7);
                        cross_red7.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }
    private void position8() {
        mDatabaseRef.child("8").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image8 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image8).into(Image8);
                        cross_red8.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }

    private void position9() {
        mDatabaseRef.child("9").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("imageUrl") != null) {
                        String image9 = map.get("imageUrl").toString();
                        Glide.with(getApplicationContext()).load(image9).into(Image9);
                        cross_red9.setVisibility(View.VISIBLE);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

    }










}
