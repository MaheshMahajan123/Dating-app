package com.example.tinderchat.Match;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tinderchat.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    private String TAG = "MatchesAdapter";
    private List<Horizontal_object_chat> matchesListt;
    public Context context;
    public MatchesAdapter(List<Horizontal_object_chat> matchesList, Context context)
    {
        this.matchesListt=matchesList;
        this.context= context;
    }

    @NonNull

    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Layoutview= LayoutInflater.from(context).inflate(R.layout.matches_item_horizontal,null,false);
        RecyclerView.LayoutParams lp=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Layoutview.setLayoutParams(lp);
        MatchViewHolder  rev= new MatchViewHolder((Layoutview));
        return  rev;

    }
    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.id.setText(matchesListt.get(position).getUserId());
        holder.chatname.setText(matchesListt.get(position).getName());
        Glide.with(context).load(matchesListt.get(position).getImage()).into(holder.chatimage);
        Log.d(TAG, "image");
    }
    @Override
    public int getItemCount() {
        return matchesListt.size();
    }
}