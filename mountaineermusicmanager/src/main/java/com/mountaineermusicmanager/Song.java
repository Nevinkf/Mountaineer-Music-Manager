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

    Song(String name, String artist){
        this.name = name;
        this.artist = artist;
    }
    
}
