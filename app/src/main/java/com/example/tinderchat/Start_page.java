package com.example.tinderchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class Start_page extends Fragment {

    private StartPageAarrayAdapter arrayAdapter;
    private FirebaseAuth mAuth;
    private String TAG = "Start_activity";
    private String currentUid;
    List<Cards> rowItems;
    TextView like,empty;
    ImageView button;
    LikeButton heart_button;
    private DatabaseReference userdb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.activity_start_page, container, false);
    userdb = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();
        checkusersex();
        heart_button =view. findViewById(R.id.heart_button);
        button =view. findViewById(R.id.button);

             like = view.findViewById(R.id.like);

        rowItems = new ArrayList<Cards>();
       heart_button.setUnlikeDrawableRes(R.drawable.ic_favorite_heart);
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView)view.findViewById(R.id.frame);


        arrayAdapter = new StartPageAarrayAdapter(getActivity(), R.layout.item, rowItems);
        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                String userId = obj.getUserId();
                userdb.child(currentUid).child("connection").child("I_Dislike_To").child(userId).setValue(true);
                userdb.child(userId).child("connection").child("Dislike").child(currentUid).setValue(true);
                Toast.makeText(getContext(), "๖ۣۜƊᎥຮlͥᎥkͣeͫ❅!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (dataObject != null) {
                    Cards obj = (Cards) dataObject;
                    String userId = obj.getUserId();
                    userdb.child(currentUid).child("connection").child("I_Like_To").child(userId).setValue(true);
                    userdb.child(userId).child("connection").child("Like_By").child(currentUid).setValue(true);
                    isconnectionMatch(userId);
                    Toast.makeText(getContext(), "꧁༒LIKE༒꧂!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float v) {
            }
        });
        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                // Toast.makeText(Start_page.this, "LIKE sucessfull!", Toast.LENGTH_SHORT).show();
                Cards obj = (Cards) dataObject;
                String userId = obj.getUserId();
                Intent intent = new Intent(getActivity(), Card_Click.class);
                intent.putExtra("E", userId);
                startActivity(intent);
            }
        });heart_button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                try{
                flingContainer.getTopCardListener().selectRight();
                        }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Data is Empty", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void unLiked(LikeButton likeButton) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    flingContainer.getTopCardListener().selectLeft();
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Data is Empty", Toast.LENGTH_SHORT).show();
                }}
        });
  return view;  }
        static void makeToast(Context ctx, String s){
            Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
        }

    private void isconnectionMatch(final String userId) {
 DatabaseReference currentuserconnection=userdb.child(currentUid).child("connection").child("Like_By").child(userId);
        currentuserconnection.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d(TAG, "matchhhh");
                    String key = FirebaseDatabase.getInstance().getReference().child("chats").push().getKey();
                    userdb.child(dataSnapshot.getKey()).child("connection").child("Matches").child(currentUid).child("ChatId").setValue(key);
                    userdb.child(currentUid).child("connection").child("Matches").child(dataSnapshot.getKey()).child("ChatId").setValue(key);
                    userdb.child(currentUid).child("connection").child("Chat").child(userId).setValue(true);
                    userdb.child(userId).child("connection").child("Chat").child(currentUid).setValue(true);
                    Toast.makeText(getContext(), "Matched", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String usersex;
    private String oppositusersex;
    public  void  checkusersex() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userd = userdb.child(user.getUid());
        userd.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists()) {
                     if (dataSnapshot.child("sex").getValue() != null) {
                            usersex = dataSnapshot.child("sex").getValue().toString();
                             switch (usersex) {
                                case "Male":
                                    oppositusersex = "Female";
                                    break;
                                case "Female":
                                    oppositusersex = "Male";
                                    break;
                            }
                            getnotusersex();
                        } } }           @Override
                public void onCancelled (@NonNull DatabaseError databaseError){
            }}); }
    public void getnotusersex()
    {
        userdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              //  if(dataSnapshot.exists())
                if(dataSnapshot.child("sex").getValue()!=null && dataSnapshot.child("city").getValue()!=null)
                {
                if(dataSnapshot.exists() && !dataSnapshot.child("connection").child("Dislike").hasChild(currentUid)
                        && !dataSnapshot.child("connection").child("Like_By").hasChild(currentUid)
                        && dataSnapshot.child("sex").getValue().toString().equals(oppositusersex))
                {
                    Cards item=new Cards(dataSnapshot.getKey(), (String) dataSnapshot.child("name").getValue(),(String) dataSnapshot.child("Image").getValue(), (String) dataSnapshot.child("date").getValue(), (String) dataSnapshot.child("city").getValue());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }}
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        }); }
}
