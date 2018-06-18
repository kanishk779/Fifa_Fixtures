package com.example.android.fifa_fixtures;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddTeamActivity extends AppCompatActivity {
    byte[] b1;
    byte[] b2;
    byte[] b3;
    byte[] b4;
    private static final int CAMERA_REQUEST1=1990;
    private static final int CAMERA_REQUEST2=1991;
    private static final int CAMERA_REQUEST3=1992;
    private static final int CAMERA_REQUEST4=1993;
    private static final int GALLERY_REQUEST1=1994;
    private static final int GALLERY_REQUEST2=1995;
    private static final int GALLERY_REQUEST3=1996;
    private static final int GALLERY_REQUEST4=1997;
    public EditText team1,team2,team3,team4;
    public TextView group;
    public ImageButton t1,t2,t3,t4;
    public ImageView i1,i2,i3,i4;
    public Button next_group;
    public String teamName1,teamName2,teamName3,teamName4;
    GlobalData g=new GlobalData();
    int count = g.getCount();
    int i=g.getId();
    MyDataBase md =new MyDataBase(AddTeamActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        next_group=findViewById(R.id.next_group);
        t1=findViewById(R.id.edit_image1);
        t2=findViewById(R.id.edit_image2);
        t3=findViewById(R.id.edit_image3);
        t4=findViewById(R.id.edit_image4);
        i1=findViewById(R.id.team_image1);
        i2=findViewById(R.id.team_image2);
        i3=findViewById(R.id.team_image3);
        i4=findViewById(R.id.team_image4);
        team1=findViewById(R.id.team_1);
        team2=findViewById(R.id.team_2);
        team3=findViewById(R.id.team_3);
        team4=findViewById(R.id.team_4);
        group=findViewById(R.id.group_name);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Box.showDailogBox(AddTeamActivity.this,CAMERA_REQUEST1,GALLERY_REQUEST1);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Box.showDailogBox(AddTeamActivity.this,CAMERA_REQUEST2,GALLERY_REQUEST2);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Box.showDailogBox(AddTeamActivity.this,CAMERA_REQUEST3,GALLERY_REQUEST3);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Box.showDailogBox(AddTeamActivity.this,CAMERA_REQUEST4,GALLERY_REQUEST4);
            }
        });
        next_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 long j=0;
                if(isValid())
                {
                    teamName1=team1.getText().toString().trim();
                    teamName2=team2.getText().toString().trim();
                    teamName3=team3.getText().toString().trim();
                    teamName4=team4.getText().toString().trim();
                    Team tm1= new Team();
                    Team tm2= new Team();
                    Team tm3= new Team();
                    Team tm4= new Team();
                    tm1.setTeam_name(teamName1);
                    tm1.setTeam_id(i);
                    tm1.setImage_url(b1);
                    md.openWrite();
                    j=md.insert(tm1);
                    md.closeWrite();
                    i++;
                    tm2.setTeam_name(teamName2);
                    tm2.setTeam_id(i);
                    tm2.setImage_url(b2);
                    md.openWrite();
                    j+=md.insert(tm2);
                    md.closeWrite();
                    i++;
                    tm3.setTeam_name(teamName3);
                    tm3.setTeam_id(i);
                    tm3.setImage_url(b3);
                    md.openWrite();
                    j+=md.insert(tm3);
                    md.closeWrite();
                    i++;
                    tm4.setTeam_name(teamName4);
                    tm4.setTeam_id(i);
                    tm4.setImage_url(b4);
                    md.openWrite();
                    j+=md.insert(tm4);
                    md.closeWrite();
                    i++;
                    count++;
                    if (j == 4) {
                        if (count <= 4) {
                            reset();
                        }else{
                            count=1;
                            Intent in=new Intent(AddTeamActivity.this,FillDetailsForMatches.class);
                            startActivity(in);
                            finish();
                        }

                    }
                    g.setCount(count);
                    g.setId(i);
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this,"Fill All The Necessary Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {

            if(requestCode==CAMERA_REQUEST1){
                Bitmap photo = (Bitmap)data.getExtras().get("data");
                i1.setImageBitmap(photo);
                ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                b1=imageStream.toByteArray();
            }
            else if(requestCode==GALLERY_REQUEST1) {
                if (requestCode == GALLERY_REQUEST1 && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bm=BitmapFactory.decodeFile(picturePath);
                    i1.setImageBitmap(bm);
                    ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                    b1=imageStream.toByteArray();
                }

            }
            else if(requestCode==CAMERA_REQUEST2){
                Bitmap photo = (Bitmap)data.getExtras().get("data");
                i2.setImageBitmap(photo);
                ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                b2=imageStream.toByteArray();
            }
            else if(requestCode==GALLERY_REQUEST2){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                        i2.setImageBitmap(bm);
                        ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                        b2=imageStream.toByteArray();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(requestCode==CAMERA_REQUEST3){
                Bitmap photo = (Bitmap)data.getExtras().get("data");
                i3.setImageBitmap(photo);
                ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                b3=imageStream.toByteArray();
            }
            else if(requestCode==GALLERY_REQUEST3){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                        i3.setImageBitmap(bm);
                        ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                        b3=imageStream.toByteArray();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(requestCode==CAMERA_REQUEST4){
                Bitmap photo = (Bitmap)data.getExtras().get("data");
                i4.setImageBitmap(photo);
                ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                b4=imageStream.toByteArray();
            }
            else if(requestCode==GALLERY_REQUEST4){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                        i4.setImageBitmap(bm);
                        ByteArrayOutputStream imageStream= new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                        b4=imageStream.toByteArray();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public boolean isValid(){
        teamName1=team1.getText().toString().trim();
        teamName2=team2.getText().toString().trim();
        teamName3=team3.getText().toString().trim();
        teamName4=team4.getText().toString().trim();
        if (TextUtils.isEmpty(teamName1) || TextUtils.isEmpty(teamName2) || TextUtils.isEmpty(teamName3) || TextUtils.isEmpty(teamName4)) {
            Toast.makeText(this, "Fill all fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(b1==null || b2==null || b3==null || b4==null){
            Toast.makeText(this, "Take Pics For ALL TEAMS", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
    private void reset() {
        team1.setText("");
        team2.setText("");
        team3.setText("");
        team4.setText("");
        i1.setImageResource(R.drawable.defaultteamicon);
        i2.setImageResource(R.drawable.defaultteamicon);
        i3.setImageResource(R.drawable.defaultteamicon);
        i4.setImageResource(R.drawable.defaultteamicon);

        if (count == 1) {
            group.setText("GROUP A");
            count++;
        } else if (count == 2) {
            group.setText("GROUP B");
            count++;
        } else if (count == 3) {
            group.setText("GROUP C");
            count++;
        } else if (count == 4) {
            group.setText("GROUP D");
            next_group.setText("Done");
            count++;
        }
    }

//    public void resetLayout(int count1){
//        if(count1==1){
//            Intent again = new Intent(AddTeamActivity.this,AddTeamActivity.class);
//            startActivity(again);
//        }
//        else if(count1==2){
//            Intent again = new Intent(AddTeamActivity.this,AddTeamActivity.class);
//            startActivity(again);
//        }
//        else if(count1==3){
//            Intent again = new Intent(AddTeamActivity.this,AddTeamActivity.class);
//            startActivity(again);
//        }
//    }
}
