package com.mountaineermusicmanager;

import javafx.scene.layout.Region;
import javafx.util.Builder;

// I might delete this, if I figure out this is not necessary

public class ProgramController {

    private Builder<Region> guiBuilder;
    private SongPlayer songPlayer;
    
    ProgramController() {
        songPlayer = new SongPlayer();
        guiBuilder = new GUIBuilder(songPlayer::playSong);
    }

    public Region getGUI() {
        return guiBuilder.build();
    }

    public void test(String testtt) {

    }

}
