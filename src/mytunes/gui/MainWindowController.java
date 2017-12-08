package mytunes.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunes.BE.PlayList;
import mytunes.BE.Song;
import mytunes.BLL.BLLManager;

/**
 *
 * @author Fábio
 * @auther Nicolai
 * @author Emil Schütt
 * @author Bastian
 */
public class MainWindowController implements Initializable {
    

    BLLManager bll = new BLLManager();

    SongModel model = new SongModel();
    

    private Label label;
    @FXML
    private TableView<PlayList> playList;
    @FXML
    private TableView<Song> songsList;

    
    @FXML
    private TableColumn<Song, String> columnTitle;
    @FXML
    private TableColumn<Song, String> columnArtist;
    @FXML
    private TableColumn<Song, String> columnCategory;
    @FXML
    private TableColumn<Song, Float> columnTime;
    @FXML
    private TableColumn<PlayList, Integer> columnNumberSongs;
    @FXML
    private TableColumn<PlayList, String> columnPlayListName;
    @FXML
    private TableColumn<PlayList, Float> columnTotalTime;
    @FXML
    private ListView<String> listview;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnTitle.setCellValueFactory(
            new PropertyValueFactory("title"));
        columnArtist.setCellValueFactory(
            new PropertyValueFactory("artist"));
        columnCategory.setCellValueFactory(
            new PropertyValueFactory("category"));
        columnTime.setCellValueFactory(
            new PropertyValueFactory("time"));
        
        songsList.setItems(model.getSongList());
        
        columnNumberSongs.setCellValueFactory(
            new PropertyValueFactory("numberSongs"));
        columnPlayListName.setCellValueFactory(
            new PropertyValueFactory("playListnName"));
        columnTotalTime.setCellValueFactory(
            new PropertyValueFactory("totalTime"));
        
        playList.setItems(model.getPlayList());
        
        System.out.println(model.getSongList());
        System.out.println(model.getPlayList());
        System.out.println(model.getAllSp());
        model.setAllSongsIntoPlayLists();
    }
    
    // opens the Playlist window when clicking new playlistp
    private void playlistNew(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("Playlist.fxml"));
        
        Parent root = fxLoader.load();
        PlaylistController stc = fxLoader.getController();
        stc.setModel(model);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }


    private void playListDelete(ActionEvent event) {
        PlayList selectedPlayList
                = playList.getSelectionModel().getSelectedItem();

        model.remove(selectedPlayList);
    }

    // opens Songtable window when clicking new song
    private void songListNew(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("Songtable.fxml"));
        
        Parent root = fxLoader.load();
        SongtableController stc = fxLoader.getController();
        stc.setModel(model);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    private void songListDelete(ActionEvent event) {
        Song selectedSong
                = songsList.getSelectionModel().getSelectedItem();

        model.remove(selectedSong);
    }
    


    @FXML
    private void SongVolumeDragDetected(MouseEvent event) {
    }

    private void buttonclose(ActionEvent event) {
        Platform.exit();
    }

    private void clickedInsert(ActionEvent event) {
        Song selectedSong
            = songsList.getSelectionModel().getSelectedItem();
        
        PlayList selectedPlayList
            = playList.getSelectionModel().getSelectedItem();
        
        model.addSongToPlayList(selectedSong , selectedPlayList);
        
        
        listview.setItems(selectedPlayList.getPlayListSongs());
        
    }

    @FXML
    private void updateList(MouseEvent event) {
        PlayList selectedPlayList
            = playList.getSelectionModel().getSelectedItem();

        System.out.println(selectedPlayList);
        listview.setItems(selectedPlayList.getPlayListSongs());
        
        
    }

    @FXML
    private void playlistNew(javafx.event.ActionEvent event) {
    }

    @FXML
    private void playListEdit(javafx.event.ActionEvent event) {
    }

    @FXML
    private void playListDelete(javafx.event.ActionEvent event) {
    }

    @FXML
    private void songListNew(javafx.event.ActionEvent event) {
    }

    @FXML
    private void songListEdit(javafx.event.ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("editSong.fxml"));
        
        Parent root = fxLoader.load();
        EditSongController stc = fxLoader.getController();
        stc.setModel(model);
        
        Song selectedSong
                = songsList.getSelectionModel().getSelectedItem();
        stc.setSong(selectedSong);
        
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    @FXML
    private void songListDelete(javafx.event.ActionEvent event) {
    }

    @FXML
    private void buttonclose(javafx.event.ActionEvent event) {
    }

    @FXML
    private void clickedInsert(javafx.event.ActionEvent event) {
    }

    @FXML
    private void songPlay(javafx.event.ActionEvent event) {
    }

    @FXML
    private void songLast(javafx.event.ActionEvent event) {
    }

    @FXML
    private void songNext(javafx.event.ActionEvent event) {
    }
}
