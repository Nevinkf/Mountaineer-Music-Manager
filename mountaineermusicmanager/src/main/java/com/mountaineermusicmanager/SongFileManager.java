/*
 * File Name: SongFileManager.java
 * Author: Nevin Fullerton
 * Date: 1/7/2025
 * Description: This manages the song files stored on the hard drive between sessions.
 */

package com.mountaineermusicmanager;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import com.google.common.io.Files;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;

public class SongFileManager {

    final private Map<Integer, Song> songDatabase = new HashMap<>();
    private String songFolder = ""; 

    SongFileManager() {
        if (!"".equals(songFolder)) {
            readSongFolder();
        }
    }

    /*
     * Adds song to database and adds it to JSON file
     */
    public void addSong() {

    }

     /*
     * Removes song from database and removes it from JSON file
     */
    public void removeSong() {

    }

    public Song getSong(Integer songID) {
        return songDatabase.get(songID);
    }

    public Set<Integer> getAllSongKeys() {
        return songDatabase.keySet();
    }

    // TODO
    public void setSongFolder() {
        songDatabase.clear();
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Open Resource File");
        File folder = fileChooser.showDialog(null);

        if (folder != null) {
            songFolder = folder.getAbsolutePath();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Folder Selected");
            alert.setHeaderText(null);
            alert.setContentText("Folder selected succesfully.");
            alert.showAndWait();
            readSongFolder(); // REMOVE
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Folder Selected");
            alert.setHeaderText(null);
            alert.setContentText("No folder was selected.");
            alert.showAndWait();
        }

    }

    private void readSongFolder() {
        // TODO clean up after implemenation
        int songID = 0;
        for (File song : new File(songFolder).listFiles()) {
            try {
                if ("mp3".equals(Files.getFileExtension(song.getAbsolutePath()))) {
                    AudioFile songAudioFile = AudioFileIO.read(song);
                    Tag songTag = songAudioFile.getTag();

                    Song newSong = new Song(songTag.getFirst(FieldKey.TITLE), songTag.getFirst(FieldKey.ARTIST), songID, song.getAbsolutePath());
                    songDatabase.put(songID, newSong);
                    songID++;
                }
            } catch (CannotReadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TagException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
