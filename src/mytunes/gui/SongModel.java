/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunes.BE.PlayList;
import mytunes.BE.Song;
import mytunes.BE.songsInPlayList;
import mytunes.BLL.BLLManager;


/**
 *
 * @author Yindo
 */
public class SongModel {
    private BLLManager bllManager = new BLLManager();
    
    private final ObservableList<Song> sList
            = FXCollections.observableArrayList(bllManager.getAllSongs());
    private final ObservableList<PlayList> pList
            = FXCollections.observableArrayList(bllManager.getAllPlayLists());
    private final ObservableList<songsInPlayList> xList
            = FXCollections.observableArrayList(bllManager.getAllSp());
     private ObservableList<Song> songsInPlayList 
            = FXCollections.observableArrayList(new ArrayList<>());
    
    public ObservableList<songsInPlayList> getAllSp() {
       return xList;
    }
    
    public ObservableList<Song> getSongList()
    {
        return sList;
    }
    
    public ObservableList<PlayList> getPlayList() 
    {
        return pList;
    }
    
    public void loadAll()
    {
        sList.clear();
        sList.addAll(bllManager.getAllSongs());
    }
    
    public void add(Song song) {
        bllManager.add(song);
        sList.add(song);
    }
    
    public void add(PlayList playList){
        bllManager.add(playList);
        pList.add(playList);
    }
    
    public void remove(Song selectedSong)
    {
        bllManager.remove(selectedSong);
        sList.remove(selectedSong);        
    }

    public void remove(PlayList selectedPlayList) {
        pList.remove(selectedPlayList); 
        bllManager.remove(selectedPlayList);
        
    }

    public void addSongToPlayList(Song selectedSong, PlayList selectedPlayList) {
        selectedPlayList.add(selectedSong);
        songsInPlayList.clear();
        songsInPlayList.add(selectedSong);
        if(selectedPlayList.getPlayListSongs() != null)
        {
            songsInPlayList.addAll(selectedPlayList.getPlayListSongs());
        }
       // selectedPlayList.setPlayListSongs(songsInPlayList);
        bllManager.addSongToPlayList(selectedSong,selectedPlayList);
    }

    
    public void setAllSongsIntoPlayLists(){
        
        for (songsInPlayList xl : xList) {
            
            int PlayListId = xl.getPlayListId();
            int SongListId = xl.getSongId();
            
            for (PlayList pl : pList) {
                int PlayListId2 = pl.getId();
                
                for (Song sl : sList) {
                    
                    int SongListId2 = sl.getId();
                    
                    if(PlayListId == PlayListId2 && SongListId == SongListId2)
                    {
                        pl.getPlayListSongs().add(sl);
                    }
                }
            }
        }
        
    }
    
    public void updateSong(Song song){
        
    }
            
}



