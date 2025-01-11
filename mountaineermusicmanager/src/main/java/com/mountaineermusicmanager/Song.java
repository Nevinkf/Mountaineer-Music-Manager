/*
 * File Name: Song.java
 * Author: Nevin Fullerton
 * Date: 1/7/2025
 * Description: This file holds all data of a song file
 */

package com.mountaineermusicmanager;


public class Song {

    private String name;
    private String artist;
    private int songID; // TODO some way to check next aviable id
    private String fileLocation;

    Song(String name, String artist, int songID, String fileLocation){
        this.name = name;
        this.artist = artist;
        this.songID = songID;
        this.fileLocation = fileLocation;
    }  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFileLocation() {
        return fileLocation;
    }
    
}
