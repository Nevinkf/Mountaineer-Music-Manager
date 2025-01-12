package com.mountaineermusicmanager;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

//Instead of having a SongPlayer, and using it, 

public class GUIBuilder implements Builder<Region>{

    final private SongPlayer songPlayer;
    final private SongFileManager songFileManager;
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
        results.setTop(new VBox(createMenuBar(), createMediaTab()));
        return results;
    }

    private Node createTable() {
        songTable = new TableView<>();
        songTable.setOnMouseClicked((mouseEvent) -> {
            if (mouseEvent.getClickCount() == 2) {
                songPlayer.changeSong(songTable.getSelectionModel().getSelectedItem());
                songPlayer.playSong();
            }
        });

        TableColumn<Song, String> column1 =  new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Song, String> column2 = new TableColumn<>("Artist");
        column2.setCellValueFactory( new PropertyValueFactory<>("artist"));

        songTable.getColumns().add(column1);
        songTable.getColumns().add(column2);

        for (Integer songID: songFileManager.getAllSongKeys()) {
            addToTable(songFileManager.getSong(songID));
        }

        return songTable;
    }

    private Node createPlaylistTab() {
        return new Label("Playlists");
    }

    private Node createMediaTab() {
        Button playButton = new Button("Play/Resume");
        playButton.setOnAction(_ -> playAndResume());

        HBox buttonBox = new HBox(playButton); // Will need later when more buttons are added

        return buttonBox;
    }

    private Node createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem songFolderMenuItem = new MenuItem("Set Song Folder");
        songFolderMenuItem.setOnAction(_ -> setSongFolderButton());

        fileMenu.getItems().add(songFolderMenuItem);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    // Stuff below move to different class eventually

    private void addToTable(Song song) {
        songTable.getItems().add(song);
    }

    private void clearTable() {
        songTable.getItems().clear();
    }

    private void playAndResume() {
        if (songTable.getItems().isEmpty() == true) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Song");
            alert.setHeaderText(null);
            alert.setContentText("No song in table.");
            alert.showAndWait();
        }
        else if (songPlayer.getCurrentSong() == null) {
            songPlayer.changeSong(songTable.getItems().get(0));
            songPlayer.playSong();
        }
        else if (songPlayer.checkIsPlaying() == true) {
            songPlayer.pauseSong();
        } else {
            songPlayer.playSong();
        }
    }

    private void setSongFolderButton() {
        songFileManager.setSongFolder();
        clearTable();
        songPlayer.clearMediaPlayer();
        for (Integer songID: songFileManager.getAllSongKeys()) { // REMOVE TESTING
            addToTable(songFileManager.getSong(songID));
        }
        
    }

}
