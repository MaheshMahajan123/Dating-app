package com.example.tinderchat.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tinderchat.OnChat_image_click;
import com.example.tinderchat.R;
import com.example.tinderchat.Start_tab;
import com.example.tinderchat.about.More_About_Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://www.youtube.com/watch?v=f1HKTg2hyf0&list=PLzLFqCABnRQftQQETzoVMuteXzNiXmnj8&index=7
//chatting design
public class Chats extends AppCompatActivity {
    private String currentuserId,matchId,chatId;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mChatsAdapter;
    private RecyclerView.LayoutManager mChatsLayoutManager;
    EditText typing;
    TextView name,textView9;
    ImageView send,imagee,delet,chatback;
    private String TAG = "Chats";
    private DatabaseReference userdb; private ArrayList<ObjectChats> resultChats=new ArrayList<ObjectChats>();
    private List<ObjectChats> getDatasetChats() {
        return resultChats;
    }

    DatabaseReference mDatabaseUser,mDatabaseChats,ref,refff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        typing = findViewById(R.id.typing);//edittext
        send = findViewById(R.id.send);
        matchId = getIntent().getExtras().getString("matchid");

        Log.d(TAG, "MATCHID" + matchId);
        mRecyclerView = findViewById(R.id.recyclerView);
        chatback = findViewById(R.id.chatback);
        userdb = FirebaseDatabase.getInstance().getReference().child("Users");

        imagee = (ImageView) findViewById(R.id.profileimage);
        name = findViewById(R.id.matchesname);
        delet = findViewById(R.id.deletall);
        final String nam = getIntent().getExtras().getString("matchidname");
        name.setText(nam);
        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(matchId);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
        mDatabaseChats = FirebaseDatabase.getInstance().getReference().child("chats");
        mChatsLayoutManager = new LinearLayoutManager(Chats.this);
        mRecyclerView.setLayoutManager(mChatsLayoutManager);
        mChatsAdapter = new ChatsAdapter(getDatasetChats(), Chats.this);
        mRecyclerView.setAdapter(mChatsAdapter);
        mDatabaseUser = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(currentuserId).child("connection").child("Matches").child(matchId).child("ChatId");

/////////////////////////////////////////////////////////////////////////////////////////
        chatback .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Chats.this, Start_tab.class);
                String str = "Mahajan";
                i.putExtra("Chats", str);
                startActivity(i);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
delet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Chats.this);
        builder.setMessage("ARE YOU SURE  you Delete All Chat").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                refff = FirebaseDatabase.getInstance().getReference()
                        .child("chats").child(chatId);
                refff.removeValue();
                mChatsAdapter.notifyDataSetChanged();
                Toast.makeText(Chats.this, "delete", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancle",null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
});
         ///////////////////////////////////////////////////////////////////////////////////////////
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
                Log.d(TAG, "sendmessage");
            }
        });
        getChatId();
        ///////////////////////////////////////////////////////////////////////////////////////////
        imagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Chats.this, OnChat_image_click.class);
                Bundle b=new Bundle();
                b.putString("matchiddd",matchId);
                i.putExtras(b);
                startActivity(i);
            }
        });
        ////////////////////////////////////////////////////////////////////////
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("Image") != null) {
                        String imag = map.get("Image").toString();
                        String im = new String(imag);
                        Glide.with(getApplicationContext()).load(im).into(imagee);
                        // image.(R.drawable.ic_red_eye24dp)
                        } } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }}); }
        //////////////////////////////////////////////////////////////////////////////////////////////
    private void sendMessage() {
        String sendmesage = typing.getText().toString();
        if (!sendmesage.isEmpty()) {
            DatabaseReference newmessage = mDatabaseChats.push();
            Map newtyping = new HashMap();
            newtyping.put("creatByUser", currentuserId);
            newtyping.put("Send_To", matchId);
            newtyping.put("Text", sendmesage);
            newmessage.setValue(newtyping);
            userdb.child(matchId).child("connection").child("Real_chat").child(currentuserId).setValue(true);
            userdb.child(currentuserId).child("connection").child("Real_chat").child(matchId).setValue(true);
      DatabaseReference mama= userdb.child(currentuserId).child("connection").child("Chat").child(matchId);
      DatabaseReference mamaa= userdb.child(matchId).child("connection").child("Chat").child(currentuserId);
           mama.removeValue();
           mamaa.removeValue();
            mChatsAdapter.notifyDataSetChanged();
            typing.setText(null);
        } else {
            Toast.makeText(this, "Type anything", Toast.LENGTH_SHORT).show();
        }

    }
    public Void getChatId() {
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    chatId = dataSnapshot.getValue().toString();
                    mDatabaseChats = mDatabaseChats.child(chatId);
                    getchatmessages();
                    mChatsAdapter.notifyDataSetChanged();
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return null;
    }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getchatmessages() {
 mDatabaseChats.addChildEventListener(new ChildEventListener() {
     @Override
     public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
         if(dataSnapshot.exists())
         {
             String message=null;
             String createdbyuser= null;
             if(dataSnapshot.child("Text").getValue()!=null)
             {
                 message=dataSnapshot.child("Text").getValue().toString();
             }
             if(dataSnapshot.child("creatByUser").getValue()!=null)
             {
                 createdbyuser=dataSnapshot.child("creatByUser").getValue().toString();
             }
             if(message!=null && createdbyuser!=null)
             {
                 Boolean currentUserBoolean=false;
                 if(createdbyuser.equals(currentuserId))
                 {
                     currentUserBoolean=true;
                 }
                 ObjectChats newmessage=new ObjectChats(message,currentUserBoolean);
                 resultChats.add(newmessage);
                 mChatsAdapter.notifyDataSetChanged(); } } }
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
     }}); }}