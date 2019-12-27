package com.sandalisw.mobileapp.analytics.models;

public class UserSongPreference {

    private int userid;
    private int songid;
    private int count;

    public UserSongPreference(int userid, int songid, int count) {
        this.userid = userid;
        this.songid = songid;
        this.count = count;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
