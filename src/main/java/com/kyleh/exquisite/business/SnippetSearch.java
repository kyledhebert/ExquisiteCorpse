package com.kyleh.exquisite.business;

import java.io.Serializable;

/**
 * Created by kylehebert on 5/8/15.
 * Objects of this class will represent a snippet search.
 */
public class SnippetSearch implements Serializable {

    String artist;
    String track;

    public SnippetSearch() {
        artist = "";
        track = "";
    }

    public SnippetSearch(String artist, String track) {
        this.artist = artist;
        this.track = track;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
