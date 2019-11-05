package com.example.tinderchat.Match;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tinderchat.chat.Chats;
import com.example.tinderchat.R;

import java.io.ByteArrayOutputStream;

public class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private String TAG = "MatchViewHolder";
    TextView chatname,id;
    ImageView chatimage;
    public MatchViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        id=itemView.findViewById(R.id.matchidd);
        chatimage=itemView.findViewById(R.id.matchimagee);
        chatname=itemView.findViewById(R.id.matchesnamee);

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(view.getContext(), Chats.class);
        Bundle b=new Bundle();
        b.putString("matchid",id.getText().toString());
        b.putString("matchidname",chatname.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);






    }
}
