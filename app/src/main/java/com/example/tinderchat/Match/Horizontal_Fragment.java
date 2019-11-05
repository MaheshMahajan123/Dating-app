package com.example.tinderchat.Match;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tinderchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Horizontal_Fragment extends Fragment {

    private RecyclerView mRecyclerView,vartical;
    private String currentuserId;
    private String TAG = "Horizontal";
//    private ArrayList<Horizontal_object_chat> resultMatches=new ArrayList<Horizontal_object_chat>();
//
//    private List<Horizontal_object_chat> getDatasetMatches() {
//        return resultMatches;
//    }

    private RecyclerView.Adapter mMatchesAdapter;
    public Horizontal_Fragment() { }
    private RecyclerView.LayoutManager mMatchesLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View vie=inflater.inflate(R.layout.fragment_horizontal_, container, false);

       return vie;
    }



}
