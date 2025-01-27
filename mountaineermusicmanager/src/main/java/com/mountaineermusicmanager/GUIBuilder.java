package com.mountaineermusicmanager;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

//Instead of having a SongPlayer, and using it, 

public class GUIBuilder implements Builder<Region>{

    final private SongPlayer songPlayer;
    final private SongFileManager songFileManager;
    private TableView<Song> songTable;
    private Slider songTimeSlider;

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
                setMediaOnReady();
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

        Button lastSongButton = new Button("<");
        lastSongButton.setOnAction(_ -> lastSong());

        Button nextSongButton = new Button(">");
        nextSongButton.setOnAction(_ -> nextSong());

        HBox buttonBox = new HBox(lastSongButton, playButton, nextSongButton); // Will need later when more buttons are added

        VBox mediaBox = new VBox(buttonBox, createSongTimeSlider());

        return mediaBox;
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

    // TODO make lables to show song duration and current time
    private Node createSongTimeSlider() {
        songTimeSlider = new Slider(0, 1, 0);
        songTimeSlider.setOnMousePressed(event -> songPlayer.pauseSong());
        songTimeSlider.setOnMouseReleased(_ -> {
            Duration duration = Duration.seconds(songTimeSlider.getValue());
            songPlayer.seek(duration);
            songPlayer.playSong();
        });


        return songTimeSlider;
    }

    // Stuff below move to different class eventually

    //TODO fix error when clicking buttons with no songs in table
    private void addToTable(Song song) {
        songTable.getItems().add(song);
    }

    private void clearTable() {
        songTable.getItems().clear();
    }

    public void updateSlider(double value) {
        songTimeSlider.setValue(value);
    }

    public void setSlider(double min, double max) {
        songTimeSlider.setMin(0);
        songTimeSlider.setMax(max);
    }

    private void lastSong() {
        int index = songTable.getItems().indexOf(songPlayer.getCurrentSong()) - 1;

        boolean songPlaying = false;
        if (songPlayer.checkIsPlaying()) 
            songPlaying = true;
        
        if (index < 0)
            songPlayer.changeSong(songTable.getItems().getLast());
        else 
            songPlayer.changeSong(songTable.getItems().get(index));

        if (songPlaying) 
            songPlayer.playSong();
        
    }

    private void nextSong() {
        int index = songTable.getItems().indexOf(songPlayer.getCurrentSong()) + 1;
        
        boolean songPlaying = false;
        if (songPlayer.checkIsPlaying()) 
            songPlaying = true;

        if (index > songTable.getItems().indexOf(songTable.getItems().getLast()))
            songPlayer.changeSong(songTable.getItems().getFirst());
        else 
            songPlayer.changeSong(songTable.getItems().get(index));

        if (songPlayer.checkIsPlaying()) 
            songPlayer.playSong();
        

        if (songPlaying) 
            songPlayer.playSong();
    }

    private void playAndResume() {
        if (songTable.getItems().isEmpty() == true) { // TODO THIS should be a method
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Song");
            alert.setHeaderText(null);
            alert.setContentText("No song in table.");
            alert.showAndWait();
        }
        else if (songPlayer.getCurrentSong() == null) {
            songPlayer.changeSong(songTable.getItems().get(0));
            setMediaOnReady();
            songPlayer.playSong();
        }
        else if (songPlayer.checkIsPlaying()) {
            songPlayer.pauseSong();
        } else {
            songPlayer.playSong();
        }

    }

    private void setMediaOnReady() {
        MediaPlayer mediaPlayer = songPlayer.getSongPlayer();

        mediaPlayer.setOnReady(() -> {
            Duration songDuration = mediaPlayer.getTotalDuration();
            setSlider(songDuration.toSeconds(), songDuration.toSeconds());
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            updateSlider(newValue.toSeconds());
        });

        // mediaPlayer.setOnEndOfMedia(_ -> {

        // });
    }

    //C:\Users\nevin\Nextcloud\Archive\music\ULTRAKILL Soundtrack\INFINITE HYPERDEATH
    private void setSongFolderButton() {
        songFileManager.setSongFolder();
        clearTable();
        songPlayer.clearMediaPlayer();
        for (Integer songID: songFileManager.getAllSongKeys()) { // REMOVE TESTING
            addToTable(songFileManager.getSong(songID));
        }
        
    }

}
