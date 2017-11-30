/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.Song;
import mytunes.DAL.DALManager;

/**
 *
 * @author Emil Pc
 */
public class BLLManager {
    
    private ObservableList<Song> songs = FXCollections.observableArrayList(new ArrayList<>());
    
    DALManager DAL = new DALManager();
    
    public List<Song> getAllSongs(){
        return DAL.getAllSongs();
    }
    
    public void addSongsList(Song song) {
        DAL.add(song);
        songs.add(song);
    }
    
    public void add(Song song)
    {
        DAL.add(song);
    }
    
    
    public ObservableList<Song> getSongList()
    {
        return songs;
    }
    
    public void remove(Song selectedSong) {
        DAL.remove(selectedSong);
    }
}

