package com.sandalisw.mobileapp.models;

import android.support.v4.media.MediaMetadataCompat;

//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "songs")
public class Song {

    @SerializedName("song_id")
    //@PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("song_name")
    //@ColumnInfo(name = "song_name")
    private String title;
    @SerializedName("artist_id")
    //@ColumnInfo(name = "artist_name")
    private String artist;
    @SerializedName("thumbnail_url")
    //@ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;
    @SerializedName("song_url")
    //@ColumnInfo(name="song_url")
    private String song_url;

    public Song(int id, String title, String artist, String thumbnailUrl, String song_url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.thumbnailUrl = thumbnailUrl;
        this.song_url = song_url;
    }

    public Song(Song s) {
        this.id = s.id;
        this.title = s.title;
        this.artist = s.artist;
    }

    public Song(String s,int i) {
        this.id = i;
        this.title = s;
    }

    public Song (MediaMetadataCompat mSong){
        this.id = Integer.parseInt(mSong.getDescription().getMediaId());
        this.title = mSong.getDescription().getTitle().toString();
        this.artist = mSong.getDescription().getSubtitle().toString();


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }
}
