package com.example.android.fifa_fixtures;

/**
 * Created by hp on 11-06-2018.
 */

public class Team1 {
    private String team_name1;
    private String team_name2;
    private int team_id1;
    private int team_id2;
    private byte[] image_url1;
    private byte[] image_url2;
    private String Venue;
    private String time;

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam_name1() {
        return team_name1;
    }

    public void setTeam_name1(String team_name1) {
        this.team_name1 = team_name1;
    }

    public String getTeam_name2() {
        return team_name2;
    }

    public void setTeam_name2(String team_name2) {
        this.team_name2 = team_name2;
    }

    public int getTeam_id1() {
        return team_id1;
    }

    public void setTeam_id1(int team_id1) {
        this.team_id1 = team_id1;
    }

    public int getTeam_id2() {
        return team_id2;
    }

    public void setTeam_id2(int team_id2) {
        this.team_id2 = team_id2;
    }

    public byte[] getImage_url1() {
        return image_url1;
    }

    public void setImage_url1(byte[] image_url1) {
        this.image_url1 = image_url1;
    }

    public byte[] getImage_url2() {
        return image_url2;
    }

    public void setImage_url2(byte[] image_url2) {
        this.image_url2 = image_url2;
    }

}
