package com.example.android.fifa_fixtures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FillDetailsMatchesUpdate extends AppCompatActivity {

    MyDataBase md =new MyDataBase(this);
    DataBaseForFixture df = new DataBaseForFixture(this);
    DataBaseForMainFixture dm = new DataBaseForMainFixture(this);
    ListView lv;
    Button submitFixture;
    GlobalData g = new GlobalData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details_matches_update);
        ArrayList<Team1> list= new ArrayList<Team1>();
        Intent in = getIntent();
        final int idOfFixture  = in.getIntExtra("Id_of_Fixture",0);
       // final int idOfFixture = Integer.valueOf(id_of_fixture);
        //int i= g.getId();
        int i=idOfFixture*16;
        i=i-16;
        while(i<i+16)
        {
            if(i%4==1) {
                df.openRead();
                Team1 tm1 = df.read(i,i+1);
                Team1 tm2 = df.read(i,i + 2);
                Team1 tm3 = df.read(i,i + 3);
                df.closeRead();
                list.add(tm1);
                list.add(tm2);
                list.add(tm3);

                i++;
            }
            else if(i%4==2)
            {
                df.openRead();
                Team1 tm1=df.read(i,i+1);
                Team1 tm2=df.read(i,i+2);
                df.closeRead();
                list.add(tm1);
                list.add(tm2);
                i++;
            }
            else if (i%4==3)
            {
                df.openRead();
                Team1 tm1=df.read(i,i+1);
                df.closeRead();
                list.add(tm1);
                i++;
            }
            else if(i%4==0)
            {
                i=i+2;
            }
        }
        lv= findViewById(R.id.listForMatchesupdate);
        lv.setAdapter(new AdapterForUpdate(FillDetailsMatchesUpdate.this,list));
        submitFixture = findViewById(R.id.save_fixture_button_update);
        submitFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid())
                {
                    int j=idOfFixture*24-24;
                    j++;
                    for(int i=j;i<j+24;i++)
                    {
                        View v = findViewById(i);
                        EditText e1=(EditText)v.findViewById(R.id.matchVenue);
                        EditText e2=(EditText)v.findViewById(R.id.matchTime);
                        TextView t1 = v.findViewById(R.id.firstTeam);
                        TextView t2 = v.findViewById(R.id.secondTeam);
                        TextView t3 = v.findViewById(R.id.id_of_team1);
                        TextView t4 = v.findViewById(R.id.id_of_team2);
                        md.openRead();
                        Team tm1=md.read(Integer.parseInt(t3.getText().toString()));
                        Team tm2=md.read(Integer.parseInt(t4.getText().toString()));
                        md.closeRead();
                        byte[] b1=tm1.getImage_url();
                        byte[] b2=tm2.getImage_url();
                        String s1 = e1.getText().toString().trim();
                        String s2 = e2.getText().toString().trim();

                        Team1 tm = new Team1();
                        tm.setTime(s2);
                        tm.setVenue(s1);
                        tm.setImage_url1(b1);
                        tm.setImage_url2(b2);
                        tm.setTeam_id1(Integer.parseInt(t3.getText().toString()));
                        tm.setTeam_id2(Integer.parseInt(t4.getText().toString()));
                        tm.setTeam_name1(t1.getText().toString());
                        tm.setTeam_name2(t2.getText().toString());
                        df.openWrite();
                        df.update(Integer.parseInt(t3.getText().toString()),Integer.parseInt(t4.getText().toString()),tm);
                        df.closeWrite();
                    }
                }
                else
                {
                    Toast.makeText(FillDetailsMatchesUpdate.this, "Fill ALL the Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isValid(){
        for(int i=1000;i<1024;i++)
        {
            View v = findViewById(i);
            EditText e1=(EditText)v.findViewById(R.id.matchVenue);
            EditText e2=(EditText)v.findViewById(R.id.matchTime);
            String s1 = e1.getText().toString().trim();
            String s2 = e2.getText().toString().trim();
            if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2))
            {
                v.requestFocus();
                return false;
            }
        }
        return true;
    }
}
