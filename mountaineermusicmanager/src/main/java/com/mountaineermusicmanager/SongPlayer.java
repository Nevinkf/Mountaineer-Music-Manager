/*
 * File Name: SongPlayer.java
 * Author: Nevin Fullerton
 * Date: 1/9/2025
 * Description: Plays and mangaes songs
 */

package com.mountaineermusicmanager;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SongPlayer {

    private MediaPlayer songPlayer;
    private String currentSong = "C:\\Users\\nevin\\Nextcloud\\Hobbies\\Software Development\\Java\\Mountaineer-Music-Manager\\songs\\Heaven Pierce Her - ULTRAKILL- INFINITE HYPERDEATH - 01 The Fire Is Gone (for Piano, Saxophone and Trumpet).mp3"; // FileName

    /*
     * 
     */
    public void playSong() {
        Media songFile = new Media(new File(currentSong).toURI().toString());
        songPlayer = new MediaPlayer(songFile);
        songPlayer.play();
    }

    public void setSong() {

    }

}
