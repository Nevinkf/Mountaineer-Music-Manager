package com.mountaineermusicmanager;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

//Instead of having a SongPlayer, and using it, 

public class GUIBuilder implements Builder<Region>{

    private final Runnable playMethod;

    GUIBuilder(Runnable playMethod) {
        this.playMethod = playMethod;
    }

    @Override
    public Region build() {
        BorderPane results = new BorderPane();
        results.setCenter(createTable());
        results.setLeft(createPlaylistTab());
        results.setTop(createMediaTab());
        return results;
    }

    private Node createTable() {
        return new TableView<Song>();
    }

    private Node createPlaylistTab() {
        return new Label("Playlists");
    }

    private Node createMediaTab() {
        Button playButton = new Button("Play");
        playButton.setOnAction(_ -> playMethod.run());
        return playButton;
    }

}
