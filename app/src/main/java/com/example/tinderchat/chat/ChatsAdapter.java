package com.example.tinderchat.chat;

import android.content.Context;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinderchat.R;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsViewHolder> {

    private String TAG = "ChatsAdapter";
    private List<ObjectChats>chatsList;
    private Context context;
    public ChatsAdapter(List<ObjectChats> chatsList, Context context)
    {
        this.chatsList=chatsList;
        this.context=context;
    }
    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Layoutvie= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chats,parent,false);
       // RecyclerView.LayoutParams lp=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      //  Layoutvie.setLayoutParams(lp);
        ChatsViewHolder rev= new ChatsViewHolder(Layoutvie);
        return (rev) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int positionm) {
if(chatsList.get(positionm).getCurrentUser())
{
    holder.sender.setText(chatsList.get(positionm).getMessage());
   holder.sender.setGravity(Gravity.END);
   holder.recive.setVisibility(View.INVISIBLE);

    Log.d(TAG,"senderrrrr");
//    holder.sender.setTextColor(Color.parseColor("#FF0A090A"));
//    holder.mContainer.setBackgroundColor(Color.parseColor("#FFA9A9A9"));
}
else
{
    holder.recive.setText(chatsList.get(positionm).getMessage());
    holder.recive.setGravity(Gravity.START);
   holder.sender.setVisibility(View.INVISIBLE);
//    holder.recive.setTextColor(Color.parseColor("#FF0A090A"));
//    holder.mContainer.setBackgroundColor(Color.parseColor("#FFFFF8DC"));
    Log.d(TAG,"receverrrr");
}
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }
}
