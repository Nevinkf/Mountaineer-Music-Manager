/*
 * File Name: SongPlayer.java
 * Author: Nevin Fullerton
 * Date: 1/9/2025
 * Description: Plays and mangaes songs
 */

package com.mountaineermusicmanager;
import java.io.File;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SongPlayer {

    private MediaPlayer songPlayer;

    SongPlayer() {
        StackPane test = new StackPane();
        

    }

    /*
     * 
     */
    public void playSong(String fileName) {
        Media songFile = new Media(new File(fileName).toURI().toString());
        songPlayer = new MediaPlayer(songFile);
        songPlayer.play();
    }

}
