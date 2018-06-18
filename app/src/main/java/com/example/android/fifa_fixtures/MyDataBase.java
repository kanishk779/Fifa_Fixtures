package com.example.android.fifa_fixtures;

/**
 * Created by hp on 10-06-2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.fifa_fixtures.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 08-06-2018.
 */

public class MyDataBase extends SQLiteOpenHelper {
    private final static  String DATABASE_NAME="database";
    private static final String TABLE_NAME="TeamInfo";
    private static final String TEAM_ID="id";
    private static final String TEAM_NAME="name";
    private static final String IMAGE_URL="TeamImage";
    private final static int VERSION=1;
    SQLiteDatabase sdW,sdR;
    private static final String CREATE_TABLE1="CREATE TABLE teaminfo ( " +
            "id INTEGER PRIMARY KEY, "+
            "name TEXT, " +
            "teamImage BLOB );";
    private static final String CREATE_TABLE="create table " + TABLE_NAME + " ( " + TEAM_ID + " int primary key, " + TEAM_NAME + " varchar(20), " + IMAGE_URL + " BLOB);";
    public MyDataBase(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME+"");
        this.onCreate(sqLiteDatabase);
    }
    public void openWrite(){
        sdW= getWritableDatabase();
    }
    public void closeWrite(){
        sdW.close();
    }
    public void openRead(){
        sdR= getWritableDatabase();
    }
    public void closeRead(){
        sdR.close();
    }
    public long insert(Team tm){
        ContentValues cv =new ContentValues();
        cv.put(TEAM_ID,tm.getTeam_id());
        cv.put(TEAM_NAME,tm.getTeam_name());
        cv.put(IMAGE_URL,tm.getImage_url());
        long i=0;
        try{
            i = sdW.insert(TABLE_NAME,null,cv);
        }
        catch(Exception e)
        {
            Log.e("in MydataBase","INSERT ERROR");
        }
        return i;
    }
    public long delete(int id){
        return sdW.delete(TABLE_NAME,""+TEAM_ID+"=?",new String[]{String.valueOf(id)});
    }
    public long update(int id,Team tm){
        ContentValues cv =new ContentValues();
        cv.put(TEAM_ID,tm.getTeam_id());
        cv.put(TEAM_NAME,tm.getTeam_name());
        cv.put(IMAGE_URL,tm.getImage_url());
        return sdW.update(TABLE_NAME,cv,""+TEAM_ID+"=?",new String[]{String.valueOf(id)});
    }
    public Team read(int id){
        sdR=getReadableDatabase();
        Team t = new Team();
        Cursor c=sdR.query(TABLE_NAME,null,""+TEAM_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(c!=null)
        {
            c.moveToNext();
            t.setTeam_id(c.getInt(0));
            t.setTeam_name(c.getString(1));
            t.setImage_url(c.getBlob(2));
        }
        c.close();
        return  t;
    }
    public List<Team> getAllTeams() {
        List<Team> list = new ArrayList<>();
        try {
            Cursor c = sdR.query(TABLE_NAME, null, null, null, null, null, null);
            while (c.moveToNext()) {
                Team t = new Team();
                t.setTeam_id(c.getInt(0));
                t.setTeam_name(c.getString(1));
                t.setImage_url(c.getBlob(2));
                list.add(t);
            }
            c.close();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return list;
    }
}
