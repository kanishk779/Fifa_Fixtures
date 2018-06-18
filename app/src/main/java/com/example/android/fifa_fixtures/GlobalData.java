package com.example.android.fifa_fixtures;

import android.app.Application;

/**
 * Created by hp on 14-06-2018.
 */

public class GlobalData extends Application {
    private int count=1;
    //public static int id=1;
    //public static int id_for_matches=1000;
    //public static int id_for_fixtures=2000;
    private int id=1;
    private int id_for_matches=1000;
    private int id_for_fixtures=2000;
    public int getId_for_fixtures() {
        return id_for_fixtures;
    }

    public void setId_for_fixtures(int id_for_fixtures) {
        this.id_for_fixtures = id_for_fixtures;
    }

    public int getId_for_matches() {
        return id_for_matches;
    }

    public void setId_for_matches(int id_for_matches) {
        this.id_for_matches = id_for_matches;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
