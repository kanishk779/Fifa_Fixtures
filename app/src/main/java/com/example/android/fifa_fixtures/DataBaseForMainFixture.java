package com.example.android.fifa_fixtures;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 15-06-2018.
 */

public class DataBaseForMainFixture extends SQLiteOpenHelper {
    private final static  String DATABASE_NAME="database_main_fixture";
    private static final String TABLE_NAME="fixtures";
    private static final String FIXTURE_ID="id";
    private static final String FIXTURE_NAME="name";
    private final static int VERSION=1;
    SQLiteDatabase sdW,sdR;
    private static final String CREATE_TABLE="create table " + TABLE_NAME + " (" + FIXTURE_ID + " int primary key," + FIXTURE_NAME + " varchar(20));";
    public DataBaseForMainFixture(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
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

    public long insert(ModelShowFixture tm){
        ContentValues cv =new ContentValues();
        cv.put(FIXTURE_ID,tm.getFixture_id());
        cv.put(FIXTURE_NAME,tm.getFixture_name());
        return sdW.insert(TABLE_NAME,null,cv);
    }
    public long delete(int id){
        return sdW.delete(TABLE_NAME,""+FIXTURE_ID+"=?",new String[]{String.valueOf(id)});
    }
    public long update(int id,ModelShowFixture tm){
        ContentValues cv =new ContentValues();
        cv.put(FIXTURE_ID,tm.getFixture_id());
        cv.put(FIXTURE_NAME,tm.getFixture_name());
        return sdW.update(TABLE_NAME,cv,""+FIXTURE_ID+"=?",new String[]{String.valueOf(id)});
    }
    public ModelShowFixture read(int id){
        sdR=getReadableDatabase();
        ModelShowFixture t = new ModelShowFixture();
        Cursor c=sdR.query(TABLE_NAME,null,""+FIXTURE_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(c!=null)
        {
            c.moveToNext();
            t.setFixture_id(c.getInt(0));
            t.setFixture_name(c.getString(1));
        }
        return  t;
    }
    public ArrayList<ModelShowFixture> getAllFixtures() {
        ArrayList<ModelShowFixture> list = new ArrayList<>();
        try {
            Cursor c = sdR.query(TABLE_NAME, null, null, null, null, null, null);
            while (c.moveToNext()) {
                ModelShowFixture t = new ModelShowFixture();
                t.setFixture_id(c.getInt(0));
                t.setFixture_name(c.getString(1));
                list.add(t);
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return list;
    }
}
