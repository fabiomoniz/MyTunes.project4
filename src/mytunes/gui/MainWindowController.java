package mytunes.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
   

   // BLLManager bll = new BLLManager();
    
    SongModel model = new SongModel();
    

    private Label label;
    @FXML
    private TableView<?> playList;
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
    }

    
    /*public void setModel(SongModel model)
    {
    this.model = model;
    }*/
    
    // opens the Playlist window when clicking new playlist
    @FXML
    private void playlistNew(ActionEvent event) throws IOException {
        model.openWindow("Playlist.fxml");
    }

    @FXML
    private void playListEdit(ActionEvent event) {
    }

    @FXML
    private void playListDelete(ActionEvent event) {
    }

    // opens Songtable window when clicking new song
    @FXML
    private void songListNew(ActionEvent event) throws IOException {
        model.openWindow("Songtable.fxml");
    }

    @FXML
    private void songListEdit(ActionEvent event) {
    }

    @FXML
    private void songListDelete(ActionEvent event) {
    }

    @FXML
    private void songPlay(ActionEvent event) {
    }

    @FXML
    private void songLast(ActionEvent event) {
    }

    @FXML
    private void songNext(ActionEvent event) {
    }

    @FXML
    private void SongVolumeDragDetected(MouseEvent event) {
    }

    @FXML
    private void buttonclose(ActionEvent event) {
        Platform.exit();
    }
}
