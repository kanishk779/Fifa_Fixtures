package com.example.android.fifa_fixtures;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    FloatingActionButton myfab;
    ArrayList<ModelShowFixture> list = new ArrayList<>();
    DataBaseForMainFixture db = new DataBaseForMainFixture(this);
    MyDataBase md = new MyDataBase(this);
    DataBaseForFixture df = new DataBaseForFixture(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db.openRead();
        list = db.getAllFixtures();
        db.closeRead();
        if(list.size()==0)
            setContentView(R.layout.activity_main);
        else
        {
            setContentView(R.layout.main_page_fixture);
            lv = findViewById(R.id.listForFixtures);
            lv.setAdapter(new AdapterForMainScreen(this,list));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView t =view.findViewById(R.id.fixture_name);
                    String s = t.getText().toString().trim();
                    String s1 = s.substring(7);
                    //int id1 = Integer.valueOf(s1);
                    Intent in = new Intent(MainActivity.this,ShowFixtureActivity.class);
                    in.putExtra("Id_ofFixture",s1);
                    startActivity(in);
                }
            });
            for(int i=0;i<list.size();i++)
            {
                ModelShowFixture mf = list.get(i);
                final int id=mf.getFixture_id();
                final int a =id-2000;
                View v = findViewById(id);
                Button b1 = (Button)v.findViewById(R.id.updatebtn);
                Button b2 = (Button)v.findViewById(R.id.deletebtn);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(MainActivity.this,FillDetailsMatchesUpdate.class);
                        in.putExtra("Id_of_Fixture",a);
                        startActivity(in);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.openWrite();
                        db.delete(id);                   //delete needs to be checked!!!
                        db.closeWrite();
                        md.openWrite();
                        int i = a*16-16;
                        for(int k=i;k<i+16;k++)
                        {
                            md.delete(k+1);
                        }
                        df.delete(i+1,i+2);
                        df.delete(i+1,i+3);

                        df.delete(i+1,i+4);

                        df.delete(i+2,i+3);

                        df.delete(i+2,i+4);

                        df.delete(i+3,i+4);

                        df.delete(i+5,i+6);

                        df.delete(i+5,i+7);

                        df.delete(i+5,i+8);

                        df.delete(i+6,i+7);

                        df.delete(i+6,i+8);

                        df.delete(i+7,i+8);

                        df.delete(i+9,i+10);

                        df.delete(i+9,i+11);

                        df.delete(i+9,i+12);

                        df.delete(i+10,i+11);

                        df.delete(i+10,i+12);

                        df.delete(i+11,i+12);

                        df.delete(i+13,i+14);

                        df.delete(i+13,i+15);

                        df.delete(i+13,i+16);

                        df.delete(i+14,i+15);

                        df.delete(i+14,i+16);

                        df.delete(i+15,i+16);
                        Intent in = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(in);
                    }
                });

            }
        }
        myfab=(FloatingActionButton)findViewById(R.id.fab);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTeamActivity.class);
                startActivity(intent);
            }
        });

    }

}
