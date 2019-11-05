package com.example.tinderchat.Vartical;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinderchat.Match.Horizontal_object_chat;
import com.example.tinderchat.Match.MatchesAdapter;
import com.example.tinderchat.R;
import com.example.tinderchat.Match.Horizontal_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Vertical_Matches extends Fragment {
private RecyclerView vartical,mRecyclerView;
private String currentuserId;
ImageView i;
TextView count;
int ccount=0;
    private String TAG = "Vartical";
    private ArrayList<Horizontal_object_chat> resultMatches=new ArrayList<Horizontal_object_chat>();
    private ArrayList<virtical_Objectmatches>result=new ArrayList<virtical_Objectmatches>();

    private List<virtical_Objectmatches> getDatasetMatche() {
        return result;
    }
        private List<Horizontal_object_chat> getDatasetMatches() {
            return resultMatches;
    }
private RecyclerView.Adapter verticalAdapter,mMatchesAdapter;
private RecyclerView.LayoutManager Varticalmak,mMatchesLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_vertical_matches, container, false);
        currentuserId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        count=view.findViewById(R.id.total);

        mRecyclerView=view.findViewById(R.id.Hori);
        mMatchesLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mMatchesLayoutManager);
        mMatchesAdapter=new MatchesAdapter(getDatasetMatches(),getContext());
        mRecyclerView.setAdapter(mMatchesAdapter);

        vartical=view.findViewById(R.id.varti);
        Varticalmak=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
       vartical.setLayoutManager(Varticalmak);
/////////////////
       verticalAdapter=new Vartical_MatchesAdapter(getDatasetMatche(),getContext());
        vartical.setAdapter(verticalAdapter);

        DatabaseReference frendsref= FirebaseDatabase.getInstance().getReference().child("Users")
                .child(currentuserId).child("connection").child("Matches");
        frendsref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                 ccount = (int) dataSnapshot.getChildrenCount();
                    count.setText("You Have  "+Integer.toString(ccount) + "  Friends");
                    //
                } else {
                    count.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
   return view;
    }
    @Override
    public void onStart() {

        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        getuserNatchesI();
        chatid();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void chatid() {
result.clear();
        DatabaseReference matchdb= FirebaseDatabase.getInstance().getReference().child("Users").child(currentuserId)
                .child("connection").child("Real_chat");
        matchdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot match : dataSnapshot.getChildren()) {
                        DatabaseReference userdb = FirebaseDatabase.getInstance().getReference().child("Users").child(match.getKey());
                        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String userid = dataSnapshot.getKey();
                                    String name = "";
                                    String Image = "";

                                    if (dataSnapshot.child("name").getValue() != null) {
                                        name = dataSnapshot.child("name").getValue().toString();

                                    }
                                    if (dataSnapshot.child("Image").getValue() != null) {
                                        Image = (String) dataSnapshot.child("Image").getValue();
                                    }
                                    virtical_Objectmatches ob = new virtical_Objectmatches(userid, name, Image);
                                    result.add(ob);
                                    verticalAdapter.notifyDataSetChanged(); } }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }}); } } else { } }
            @Override public void onCancelled(@NonNull DatabaseError databaseError) { }}); }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getuserNatchesI() {
       resultMatches.clear();
        DatabaseReference matchdb= FirebaseDatabase.getInstance().getReference().child("Users").child(currentuserId)
                .child("connection").child("Chat");
        matchdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot match : dataSnapshot.getChildren())
                    {
                        fetchmatchInformation(match.getKey());// vo match krta ha 1st usrt fr match ka andr aata hai
                    } } else { } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }}); }

    private void fetchmatchInformation(String key) {
        DatabaseReference userdb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userid = dataSnapshot.getKey();
                    String name = "";
                    String Image = "";
                    if (dataSnapshot.child("name").getValue() != null) {
                        name = dataSnapshot.child("name").getValue().toString();
                    }
                    if (dataSnapshot.child("Image").getValue() != null) {
                        Image = (String) dataSnapshot.child("Image").getValue();
                    }
                    Horizontal_object_chat obj = new Horizontal_object_chat(userid, name, Image);
                    resultMatches.add(obj);
                    mMatchesAdapter.notifyDataSetChanged();

                } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
