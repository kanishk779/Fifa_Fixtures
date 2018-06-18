package com.example.android.fifa_fixtures;

/**
 * Created by hp on 14-06-2018.
 */

public class ModelForMatches {
    private String Team1;
    private String Team2;
    private byte[] image1;
    private byte[] image2;
    private int team1id;
    private int team2id;
    public String getTeam1() {
        return Team1;
    }

    public void setTeam1(String team1) {
        Team1 = team1;
    }

    public String getTeam2() {
        return Team2;
    }

    public int getTeam1id() {
        return team1id;
    }

    public void setTeam1id(int team1id) {
        this.team1id = team1id;
    }

    public int getTeam2id() {
        return team2id;
    }

    public void setTeam2id(int team2id) {
        this.team2id = team2id;
    }

    public void setTeam2(String team2) {

        Team2 = team2;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }


}
