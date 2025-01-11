package com.mountaineermusicmanager;

import javafx.scene.layout.Region;
import javafx.util.Builder;

// I might delete this, if I figure out this is not necessary

public class ProgramController {

    private Builder<Region> guiBuilder;
    
    
    ProgramController() {
        // songFileManager = new SongFileManager(); // 
        // songPlayer = new SongPlayer();
        guiBuilder = new GUIBuilder();
    }

    public Region getGUI() {
        return guiBuilder.build();
    }

}
