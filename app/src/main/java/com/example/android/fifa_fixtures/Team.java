package com.example.android.fifa_fixtures;

import java.sql.Blob;

/**
 * Created by hp on 10-06-2018.
 */

public class Team {
    private String team_name;
    private int team_id;
    private byte[] image_url;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public byte[] getImage_url() {
        return image_url;
    }

    public void setImage_url(byte[] image_url) {
        this.image_url = image_url;
    }
}
