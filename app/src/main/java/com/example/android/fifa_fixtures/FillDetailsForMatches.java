package com.example.android.fifa_fixtures;

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
import java.util.List;

public class FillDetailsForMatches extends AppCompatActivity {
    MyDataBase md =new MyDataBase(this);
    DataBaseForFixture df = new DataBaseForFixture(this);
    DataBaseForMainFixture dm = new DataBaseForMainFixture(this);
    ListView lv;
    Button submitFixture;
    GlobalData g = new GlobalData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details_for_matches);
        ArrayList<ModelForMatches> list= new ArrayList<ModelForMatches>();
        int i= g.getId();
        i=i-16;
        while(i<i+16)
        {
            if(i%4==1) {
                md.openRead();
                Team tm1 = md.read(i);
                Team tm2 = md.read(i + 1);
                Team tm3 = md.read(i + 2);
                Team tm4 = md.read(i + 3);
                md.closeRead();
                ModelForMatches m1 = new ModelForMatches();
                ModelForMatches m2 = new ModelForMatches();
                ModelForMatches m3 = new ModelForMatches();
                m1.setTeam1(tm1.getTeam_name());
                m1.setTeam2(tm2.getTeam_name());
                m1.setImage1(tm1.getImage_url());
                m1.setImage2(tm2.getImage_url());
                m1.setTeam1id(tm1.getTeam_id());
                m1.setTeam2id(tm2.getTeam_id());
                list.add(m1);
                m2.setTeam1(tm1.getTeam_name());
                m2.setTeam2(tm3.getTeam_name());
                m2.setImage1(tm1.getImage_url());
                m2.setImage2(tm3.getImage_url());
                m2.setTeam1id(tm1.getTeam_id());
                m2.setTeam2id(tm3.getTeam_id());
                list.add(m2);
                m3.setTeam1(tm1.getTeam_name());
                m3.setTeam2(tm4.getTeam_name());
                m3.setImage1(tm1.getImage_url());
                m3.setImage2(tm4.getImage_url());
                m3.setTeam1id(tm1.getTeam_id());
                m3.setTeam2id(tm4.getTeam_id());
                list.add(m3);
                i++;
            }
            else if(i%4==2)
            {
                md.openRead();
                Team tm1=md.read(i);
                Team tm2=md.read(i+1);
                Team tm3=md.read(i+2);
                md.closeRead();
                ModelForMatches m1 = new ModelForMatches();
                ModelForMatches m2 = new ModelForMatches();
                m1.setTeam1(tm1.getTeam_name());
                m1.setTeam2(tm2.getTeam_name());
                m1.setImage1(tm1.getImage_url());
                m1.setImage2(tm2.getImage_url());
                m1.setTeam1id(tm1.getTeam_id());
                m1.setTeam2id(tm2.getTeam_id());
                list.add(m1);
                m2.setTeam1(tm1.getTeam_name());
                m2.setTeam2(tm3.getTeam_name());
                m2.setImage1(tm1.getImage_url());
                m2.setImage2(tm3.getImage_url());
                m2.setTeam1id(tm1.getTeam_id());
                m2.setTeam2id(tm3.getTeam_id());
                list.add(m2);
                i++;
            }
            else if (i%4==3)
            {
                md.openRead();
                Team tm1=md.read(i);
                Team tm2=md.read(i+1);
                md.closeRead();
                ModelForMatches m1 = new ModelForMatches();
                m1.setTeam1(tm1.getTeam_name());
                m1.setTeam2(tm2.getTeam_name());
                m1.setImage1(tm1.getImage_url());
                m1.setImage2(tm2.getImage_url());
                m1.setTeam1id(tm1.getTeam_id());
                m1.setTeam2id(tm2.getTeam_id());
                list.add(m1);
                i++;
            }
            else if(i%4==0)
            {
                i=i+2;
            }
        }
        lv= findViewById(R.id.listForMatches);
        lv.setAdapter(new AdapterForMatches(FillDetailsForMatches.this,list));
        submitFixture=findViewById(R.id.save_fixture_button);
        submitFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid())
                {
                    int id=g.getId_for_fixtures();
                    id++;
                    dm.openWrite();
                    ModelShowFixture m = new ModelShowFixture();
                    m.setFixture_name("FIXTURE" +(id-2000) );
                    m.setFixture_id(id);
                    dm.insert(m);
                    dm.closeWrite();
                    g.setId_for_fixtures(id);
                    int j=g.getId_for_matches();
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
                        df.insert(tm);
                        df.closeWrite();
                    }
                    g.setId_for_matches(j+23);
                }
                else
                {
                    Toast.makeText(FillDetailsForMatches.this, "Fill ALL the Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isValid(){
        int j=g.getId_for_matches();
        j++;
        for(int i=j;i<j+24;i++)
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
