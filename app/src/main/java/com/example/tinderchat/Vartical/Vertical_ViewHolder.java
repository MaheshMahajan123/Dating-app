package com.example.tinderchat.Vartical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinderchat.R;
import com.example.tinderchat.chat.Chats;

public class Vertical_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private String TAG = "MatchViewHolder";

    TextView matchesid,matchesname;
    ImageView matchimage;
    public Vertical_ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        matchesid=itemView.findViewById(R.id.iddd);
        matchimage=itemView.findViewById(R.id.chatimage);
        matchesname=itemView.findViewById(R.id.chatname);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(view.getContext(), Chats.class);
        Bundle b=new Bundle();
        b.putString("matchid",matchesid.getText().toString());
        b.putString("matchidname",matchesname.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}
