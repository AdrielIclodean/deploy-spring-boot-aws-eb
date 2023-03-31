package com.cantaricrestine.aws.webservices.restfulwebservices.cantaricrestine;

public class Song {

    private AlbumNames albumName;
    private String songName;
    private String text;

    public Song() {

    }

    public Song(AlbumNames albumName, String songName, String text) {
        this.albumName = albumName;
        this.songName = songName;
        this.text = text;
    }

    public AlbumNames getAlbumName() {
        return albumName;
    }

    public void setAlbumName(AlbumNames albumName) {
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Song{" +
                "albumName=" + albumName +
                ", songName='" + songName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}