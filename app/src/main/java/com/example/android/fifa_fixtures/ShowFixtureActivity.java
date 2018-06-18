package com.example.android.fifa_fixtures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowFixtureActivity extends AppCompatActivity {
    ListView lv;
    Team1 tm= new Team1();
    DataBaseForFixture df = new DataBaseForFixture(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fixture);
        ArrayList<Team1> list = new ArrayList<>();
        Intent in = getIntent();
        String s = in.getStringExtra("Id_ofFixture");
        int idoffixture = Integer.valueOf(s);
        int i = idoffixture*16 - 16;
        df.openRead();
        tm=df.read(i+1,i+2);
        list.add(tm);
        tm=df.read(i+1,i+3);
        list.add(tm);
        tm=df.read(i+1,i+4);
        list.add(tm);
        tm=df.read(i+2,i+3);
        list.add(tm);
        tm=df.read(i+2,i+4);
        list.add(tm);
        tm=df.read(i+3,i+4);
        list.add(tm);
        tm=df.read(i+5,i+6);
        list.add(tm);
        tm=df.read(i+5,i+7);
        list.add(tm);
        tm=df.read(i+5,i+8);
        list.add(tm);
        tm=df.read(i+6,i+7);
        list.add(tm);
        tm=df.read(i+6,i+8);
        list.add(tm);
        tm=df.read(i+7,i+8);
        list.add(tm);
        tm=df.read(i+9,i+10);
        list.add(tm);
        tm=df.read(i+9,i+11);
        list.add(tm);
        tm=df.read(i+9,i+12);
        list.add(tm);
        tm=df.read(i+10,i+11);
        list.add(tm);
        tm=df.read(i+10,i+12);
        list.add(tm);
        tm=df.read(i+11,i+12);
        list.add(tm);
        tm=df.read(i+13,i+14);
        list.add(tm);
        tm=df.read(i+13,i+15);
        list.add(tm);
        tm=df.read(i+13,i+16);
        list.add(tm);
        tm=df.read(i+14,i+15);
        list.add(tm);
        tm=df.read(i+14,i+16);
        list.add(tm);
        tm=df.read(i+15,i+16);
        list.add(tm);
        lv= findViewById(R.id.listViewForMatches);
        //Prepare the itemforfixture of matches (you have to use AddTeamActivity);
        lv.setAdapter(new AdapterForFixture(this,list));
    }
}
