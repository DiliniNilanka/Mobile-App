package com.sandalisw.mobileapp.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sandalisw.mobileapp.models.Song;
import com.sandalisw.mobileapp.models.SongR;

import java.util.List;

public class TopSongsResponse {
    @SerializedName("recent")
    @Expose()
    private List<Song> recent_songs;

    @SerializedName("classic")
    @Expose()
    private List<Song> old_songs;

    @SerializedName("recentlyplayed")
    @Expose()
    private List<SongR> recently_played_songs;

    public List<Song> getRecent_songs() {
        return recent_songs;
    }

    public void setRecent_songs(List<Song> recent_songs) {
        this.recent_songs = recent_songs;
    }

    public List<Song> getOld_songs() {
        return old_songs;
    }

    public void setOld_songs(List<Song> old_songs) {
        this.old_songs = old_songs;
    }

    public void setRecently_played_songs(List<SongR> recently_played_songs){ this.recently_played_songs=recently_played_songs;}

    public List<SongR> getRecently_played_songs(){ return recently_played_songs;}





}
