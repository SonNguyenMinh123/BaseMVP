package com.example.hdt.basemvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hdt
 */

public class ItemSongSearch {
    @SerializedName("Id")
    private String id;

    @SerializedName("Title")
    private String title;

    @SerializedName("Artist")
    private String artist;

    @SerializedName("Avatar")
    private String avatar;

    @SerializedName("UrlJunDownload")
    private String urlJunDownload;

    @SerializedName("LyricsUrl")
    private String lyricsUrl;

    @SerializedName("UrlSource")
    private String urlSource;

    @SerializedName("SiteId")
    private String siteId;

    @SerializedName("HostName")
    private String hostName;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUrlJunDownload() {
        return urlJunDownload;
    }

    public String getLyricsUrl() {
        return lyricsUrl;
    }

    public String getUrlSource() {
        return urlSource;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getHostName() {
        return hostName;
    }
}
