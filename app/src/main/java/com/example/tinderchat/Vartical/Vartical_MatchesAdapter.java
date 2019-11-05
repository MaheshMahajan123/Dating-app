package com.example.tinderchat.Vartical;

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

public class Vartical_MatchesAdapter extends RecyclerView.Adapter<Vertical_ViewHolder> {
    private String TAG = "Vartical_MatchesAdapter";
    private List<virtical_Objectmatches> matchesLis;
    public Context contex;
    public Vartical_MatchesAdapter(List<virtical_Objectmatches> matchesList, Context context)
    {
        this.matchesLis=matchesList;
        this.contex=context;
    }
    @NonNull
    @Override
    public Vertical_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Layoutview= LayoutInflater.from(contex).inflate(R.layout.matche_vertical,null,false);
        Vertical_ViewHolder rev= new Vertical_ViewHolder((Layoutview));
        return  rev;
    }

    @Override
    public void onBindViewHolder(@NonNull Vertical_ViewHolder holder, int position) {
        {
            holder.matchesid.setText(matchesLis.get(position).getUserId());
            holder.matchesname.setText(matchesLis.get(position).getName());
//holder.matchimage.setText(matchesList.get(position).getUserId());
            //  if(!matchesList.get(position).getImage().equals("defualt")) {
            Glide.with(contex).load(matchesLis.get(position).getImage()).into(holder.matchimage);
            Log.d(TAG,"image");
        }
    }
    @Override
    public int getItemCount() {
        return matchesLis.size();
    }
}
