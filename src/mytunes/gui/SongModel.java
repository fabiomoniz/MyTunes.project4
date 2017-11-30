/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import mytunes.BE.Song;
import mytunes.BLL.BLLManager;

/**
 *
 * @author Yindo
 */
public class SongModel {
    private BLLManager bllManager = new BLLManager();
    
    private final ObservableList<Song> sList
            = FXCollections.observableArrayList(bllManager.getAllSongs());
    
    
    //returns the observable list that is called in the controller
    public ObservableList<Song> getSongList()
    {
        return sList;
    }
    
    
    public void add(Song song) {
        bllManager.add(song);
        sList.add(song);
    }
    
    public void remove(Song selectedSong)
    {
        sList.remove(selectedSong);        
    }
    
    // method to set the stage/opening a window on the view. Is called in the MainWindowController
    
}



