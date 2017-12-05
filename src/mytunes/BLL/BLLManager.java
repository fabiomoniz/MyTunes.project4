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
import mytunes.BE.PlayList;
import mytunes.BE.Song;
import mytunes.DAL.DALManager;

/**
 *
 * @author Emil Pc
 */
public class BLLManager {
    
    private ObservableList<Song> songs = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<PlayList> playLists = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Song> songsInPlayList = FXCollections.observableArrayList(new ArrayList<>());
    
    DALManager DAL = new DALManager();
    
    public List<Song> getAllSongs(){
        return DAL.getAllSongs();
    }
    
    public List<PlayList> getAllPlayLists(){
        return DAL.getAllPlayList();
    }
    
    public ObservableList<Song> getSongList()
    {
        return songs;
    }
    
    public ObservableList<PlayList> getPlayList()
    {
        return playLists;
    }
    
    public void addSongsList(Song song) {
        DAL.addSong(song);
        songs.add(song);
    }
    
    public void add(Song song)
    {
        DAL.addSong(song);
    }
    
    public void add(PlayList playList) {
        DAL.addPlayList(playList);
    }
    
    
    
    public void remove(Song selectedSong) {
        DAL.remove(selectedSong);
    }

    public void remove(PlayList selectedPlayList) {
        DAL.remove(selectedPlayList);
    }

    public void addSongToPlayList(Song selectedSong, PlayList selectedPlayList) {
        DAL.addSongToPlayList(selectedPlayList, selectedSong);
        songsInPlayList.add(selectedSong);
        selectedPlayList.setPlayListSongs(songsInPlayList);
        
        /*DAL.addSongToPlayList(selectedPlayList, selectedSong);
        setAllSongsInPlayListIntoPlayList();
        songsInPlayList.setAll(selectedPlayList.getAllSongNamesFromPlayList());*/
    }

    public void setAllSongsInPlayListIntoPlayList(){
        for (int i = 0; i < DAL.getAllSongsFromPlaylist().size(); i++) {
            
            int PlayListId = DAL.getAllSongsFromPlaylist().get(i).getPlayListId();
            int SongId = DAL.getAllSongsFromPlaylist().get(i).getSongId();
            
            for (PlayList playlist : playLists) {
                
              if(PlayListId == playlist.getId()) 
              {
                  for (Song song : songs) {
                      
                      if (SongId == song.getId())
                      {
                          playlist.getAllSongNamesFromPlayList().add(song);
                      }
                      
                  }
              }
            }
            
        }
        
        
    }
}

