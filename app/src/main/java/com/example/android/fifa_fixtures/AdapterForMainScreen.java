package com.example.android.fifa_fixtures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 15-06-2018.
 */

public class AdapterForMainScreen extends BaseAdapter {
    ArrayList<ModelShowFixture> list;
    Context context;
    public AdapterForMainScreen(Context context,ArrayList<ModelShowFixture> list)
    {
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
        View v = LayoutInflater.from(context).inflate(R.layout.item_main_screen_fixture, null);
        TextView t1 = v.findViewById(R.id.fixture_name);
        ModelShowFixture mo = list.get(i);
        //t1.setText("FIXTURE" + (mo.getFixture_id()-2000));
        t1.setText(mo.getFixture_name());
        v.setId(mo.getFixture_id());
        return v;
    }
}
