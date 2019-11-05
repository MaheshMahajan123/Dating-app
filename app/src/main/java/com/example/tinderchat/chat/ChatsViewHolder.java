package com.example.tinderchat.chat;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinderchat.R;

public class ChatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private String TAG = "ChatsViewHolder";
TextView sender,recive;
public ConstraintLayout mContainer;

    public ChatsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        sender=itemView.findViewById(R.id.sender);
        recive=itemView.findViewById(R.id.reciver);
        mContainer=itemView.findViewById(R.id.cont);
    }

    @Override
    public void onClick(View view) {
    }
}
