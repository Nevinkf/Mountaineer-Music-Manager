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
import javafx.scene.media.MediaPlayer.Status;

public class SongPlayer {

    private MediaPlayer songPlayer;
    private Song currentSong;

    /*
     * current song
     */
    public void changeSong(Song song) {
        if (checkIsPlaying() == true) {
            pauseSong();
        }
        Media songFile = new Media(new File(song.getFileLocation()).toURI().toString());
        songPlayer = new MediaPlayer(songFile);
        currentSong = song;
    }

    public void playSong() {
        if (songPlayer != null)
            songPlayer.play();
    }

    public void pauseSong() {
        if (songPlayer != null)
            songPlayer.pause();
    }

    public void clearMediaPlayer() {
        songPlayer = null;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public boolean checkIsPlaying() {
        if (songPlayer != null) {
            if (songPlayer.getStatus().equals(Status.PLAYING))
                return true;
            else
                return false;
        }
        return false; // NOTE
    }

}
