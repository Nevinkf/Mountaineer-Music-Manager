package com.mountaineermusicmanager;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

//Instead of having a SongPlayer, and using it, 

public class GUIBuilder implements Builder<Region>{

    private SongPlayer songPlayer;
    private SongFileManager songFileManager;
    private TableView<Song> songTable;

    GUIBuilder() {
        songPlayer = new SongPlayer();
        songFileManager = new SongFileManager();
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
        songTable = new TableView<>();
        songTable.setOnMouseClicked((mouseEvent) -> {
            if (mouseEvent.getClickCount() == 1) { // Single click
                System.out.println("Row clicked: " + songTable.getItems().get(0).getFileLocation());
            }   
        });

        TableColumn<Song, String> column1 =  new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Song, String> column2 = new TableColumn<>("Artist");
        column2.setCellValueFactory( new PropertyValueFactory<>("artist"));

        songTable.getColumns().add(column1);
        songTable.getColumns().add(column2);

        for (Integer songID: songFileManager.getAllSongKeys()) {
            songTable.getItems().add(songFileManager.getSong(songID));
        }

        return songTable;
    }

    private Node createPlaylistTab() {
        return new Label("Playlists");
    }

    private Node createMediaTab() {
        Button playButton = new Button("Play");
        playButton.setOnAction(_ -> songPlayer.playSong(songTable.getSelectionModel().getSelectedItem()));
        return playButton;
    }

}
