package com.example.android.fifa_fixtures;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 11-06-2018.
 */

public class DataBaseForFixture extends SQLiteOpenHelper {
    private final static  String DATABASE_NAME="database_fixture";
    private static final String TABLE_NAME="matches";
    private static final String TEAM_ID1="id1";
    private static final String TEAM_NAME1="name1";
    private static final String IMAGE_URL1="image1";
    private static final String TEAM_ID2="id2";
    private static final String TEAM_NAME2="name2";
    private static final String IMAGE_URL2="image2";
    private static final String VENUE="venue";
    private static final String TIME="time";
    private final static int VERSION=1;
    SQLiteDatabase sdW,sdR;
    private static final String CREATE_TABLE="create table " + TABLE_NAME + " (" + TEAM_ID1 + " int ," + TEAM_NAME1 + " varchar(20),"+IMAGE_URL1+" blob," +
            "" + TEAM_ID2 + " int ," + TEAM_NAME2 + " varchar(20),"+IMAGE_URL2+" blob,"+VENUE+" varchar(20),"+TIME+" varchar(20);";
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
    public DataBaseForFixture(Context cmp){
            super(cmp,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME+"");
        this.onCreate(sqLiteDatabase);
    }
    public void insert(Team1 tm){
        ContentValues cv =new ContentValues();
        cv.put(TEAM_ID1,tm.getTeam_id1());
        cv.put(TEAM_NAME1,tm.getTeam_name1());
        cv.put(IMAGE_URL1,tm.getImage_url1());
        cv.put(TEAM_ID2,tm.getTeam_id2());
        cv.put(TEAM_NAME2,tm.getTeam_name2());
        cv.put(IMAGE_URL2,tm.getImage_url2());
        cv.put(VENUE,tm.getVenue());
        cv.put(TIME,tm.getTime());
        sdW.insert(TABLE_NAME,null,cv);
    }
    public void delete(int id1,int id2){
        sdW.delete(TABLE_NAME,""+TEAM_ID1+"=? and "+TEAM_ID2+"=?",new String[]{String.valueOf(id1),String.valueOf(id2)});
    }
    public void update(int id1,int id2,Team1 tm){
        ContentValues cv =new ContentValues();
        cv.put(TEAM_ID1,tm.getTeam_id1());
        cv.put(TEAM_NAME1,tm.getTeam_name1());
        cv.put(IMAGE_URL1,tm.getImage_url1());
        cv.put(TEAM_ID2,tm.getTeam_id2());
        cv.put(TEAM_NAME2,tm.getTeam_name2());
        cv.put(IMAGE_URL2,tm.getImage_url2());
        cv.put(VENUE,tm.getVenue());
        cv.put(TIME,tm.getTime());
        sdW.insert(TABLE_NAME,null,cv);
    }
    public Team1 read(int id1,int id2){
        sdR=getReadableDatabase();
        Team1 t = new Team1();
        Cursor c=sdR.query(TABLE_NAME,null,""+TEAM_ID1+"=?and "+TEAM_ID2+"=?",new String[]{String.valueOf(id1),String.valueOf(id2)},null,null,null);
        if(c!=null)
        {
            c.moveToNext();
            t.setTeam_id1(c.getInt(0));
            t.setTeam_name1(c.getString(1));
            t.setImage_url1(c.getBlob(2));
            t.setTeam_id2(c.getInt(3));
            t.setTeam_name2(c.getString(4));
            t.setImage_url2(c.getBlob(5));
            t.setVenue(c.getString(6));
            t.setTime(c.getString(7));
        }
        return  t;
    }
}
