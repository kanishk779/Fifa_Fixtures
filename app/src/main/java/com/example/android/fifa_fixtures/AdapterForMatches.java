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
 * Created by hp on 14-06-2018.
 */

public class AdapterForMatches extends BaseAdapter {
    GlobalData g = new GlobalData();
    int id=g.getId_for_matches();
    ArrayList<ModelForMatches> list;
    Context context;
    public AdapterForMatches(Context context, ArrayList<ModelForMatches> list) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemforaddingmatch, null);
        TextView t1 = v.findViewById(R.id.firstTeam);
        TextView t2 = v.findViewById(R.id.secondTeam);
        ImageView i1=v.findViewById(R.id.team1Icon);
        ImageView i2=v.findViewById(R.id.team2Icon);
        TextView t3 = v.findViewById(R.id.id_of_team1);
        TextView t4 = v.findViewById(R.id.id_of_team2);
        //ListIterator<ModelForMatches> it=list.listIterator();
        ModelForMatches mo=list.get(i);
        t1.setText(mo.getTeam1());
        t2.setText(mo.getTeam2());
        t3.setText(mo.getTeam1id());
        t4.setText(mo.getTeam2id());
        byte[] b1=mo.getImage1();
        byte[] b2=mo.getImage2();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(b1, 0, b1.length);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
        i1.setImageBitmap(bitmap1);
        i2.setImageBitmap(bitmap2);
        v.setId(id);
        id++;
        return v;
    }
}
