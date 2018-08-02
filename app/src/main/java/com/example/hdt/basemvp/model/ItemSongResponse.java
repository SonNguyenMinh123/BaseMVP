package com.example.hdt.basemvp.model;

/**
 * Created by hdt
 */

public class ItemSongResponse {
    private String dataCode;
    private String title;
    private String artist;

    public ItemSongResponse(String dataCode, String title) {
        this.dataCode = dataCode;
        this.title = title;
    }

    public String getDataCode() {
        return dataCode;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
