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

public class SongFileManager {

    private Map<Integer, Song> songDatabase = new HashMap<>();
    private String songFolder = "";

    SongFileManager() {
        readSongFolder();
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

    private void readSongFolder() {
        // TODO clean up after implemenation
        int songID = 0;
        for (File song : new File(songFolder).listFiles()) {
            try {
                AudioFile songAudioFile = AudioFileIO.read(song);
                Tag songTag = songAudioFile.getTag();

                Song newSong = new Song(songTag.getFirst(FieldKey.TITLE), songTag.getFirst(FieldKey.ARTIST), songID, song.getAbsolutePath());
                songDatabase.put(songID, newSong);
                songID++;
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
