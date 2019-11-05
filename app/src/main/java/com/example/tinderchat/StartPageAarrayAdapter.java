package com.example.tinderchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;


public class StartPageAarrayAdapter extends ArrayAdapter<Cards> {
private Context mContext;
private List<Cards> mUploads;

    public StartPageAarrayAdapter(Context context, int resource, List<Cards> item) {
        super(context, resource,item);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cards cards_item = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);

        TextView name,address,date;
        name = convertView.findViewById(R.id.matchesname);
        date = convertView.findViewById(R.id.date);
        address = convertView.findViewById(R.id.address);
        ImageView img = (ImageView) convertView.findViewById(R.id.image);
        name.setText(cards_item.getName());
        address.setText(cards_item.getCity());
        date.setText("  Birth Date   "+cards_item.getDate());
        Glide.with(getContext()).load(cards_item.getImage()).into(img);
        return convertView;

//    @Override
//    public StartPageAarrayAdapter onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
//        android.view.View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
//        StartPageAarrayAdapter va=new StartPageAarrayAdapter(v);
//        return  va;
//    }

    }
    }
