package com.example.android.fifa_fixtures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by hp on 11-06-2018.
 */

public class AdapterForFixture extends BaseAdapter {
    ArrayList<Team1> list;
    Context context;

    public AdapterForFixture(Context context, ArrayList<Team1> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemforfixture, null);
        TextView t1 = v.findViewById(R.id.firstTeam);
        TextView t2 = v.findViewById(R.id.secondTeam);
        ImageView i1=v.findViewById(R.id.team1Icon);
        ImageView i2=v.findViewById(R.id.team2Icon);
        TextView t3=v.findViewById(R.id.matchVenue);
        TextView t4=v.findViewById(R.id.matchTime);
        //ListIterator<Team1> it=list.listIterator();
        Team1 tm=list.get(position);
        t1.setText(tm.getTeam_name1());
        t2.setText(tm.getTeam_name2());
        byte[] b1=tm.getImage_url1();
        byte[] b2=tm.getImage_url2();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(b1, 0, b1.length);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
        i1.setImageBitmap(bitmap1);
        i2.setImageBitmap(bitmap2);
        t3.setText(tm.getVenue());
        t4.setText(tm.getTime());
        return v;
    }
}
