/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mytunes.BE.PlayList;
import mytunes.BE.Song;
import mytunes.BE.songsInPlayList;
import mytunes.DAL.DALManager;

/**
 *
 * @author Emil Pc
 */
public class BLLManager {
    
    private ObservableList<Song> songs = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<PlayList> playLists = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Song> songsInPlayList = FXCollections.observableArrayList(new ArrayList<>());
    private int x = 0; //for the play() to make it pause and play
    
    DALManager DAL = new DALManager();

    String filePath2;
    String filePath3;
    String filePath;
    MediaPlayer player;
    Media media;
    Song song;
    
    public List<Song> getAllSongs(){
        return DAL.getAllSongs();
    }
    
    public List<songsInPlayList> getAllSp(){
        return DAL.getAllSp();
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
    }

    public void updateSong(Song song) {
        DAL.updateSong(song);
    }

    public void updatePlayList(PlayList playlist) {
       DAL.updatePlayList(playlist);
    }

    public void removeFromPlayList(songsInPlayList selectedSongInPlayList) {
        DAL.remove(selectedSongInPlayList);
    }

    public void play(Song selectedSong, PlayList selectedPlayList) {
        switch(x) {
            
        case 0:
            song = selectedSong;
            filePath2 = song.getFilePath();
            if(filePath2 != filePath3) {
            filePath3 = song.getFilePath();
            File file = new File(song.getFilePath());
            filePath = file.toURI().toString();
            media = new Media(filePath);
            player = new MediaPlayer(media);
            player.play();
            x = 2; 
            break;}

            else if(player.getCurrentTime() == player.getStopTime()) {
                filePath3 = null;
                x = 0; }
                
            else x = 1;
            
        
        case 1:
            
            {
            player.play();
            x = 2;
            break;
            }
        
        case 2:
                
            player.pause();
            x = 0;
            break;
        }
    }

    public void play(PlayList selectedPlayList) {
       
        switch(x) {
            case 0:
            song = (Song) selectedPlayList.getPlayListSongs().get(0);
            filePath2 = song.getFilePath();
            if(filePath2 != filePath3) {
            filePath3 = song.getFilePath();
            File file = new File(song.getFilePath());
            String filePath = file.toURI().toString();
            media = new Media(filePath);
            player = new MediaPlayer(media);
            player.play();
            x = 1;
            break;}
            else x = 1;
            player.play();
            
            case 1:
            player.pause();
            x = 0;
            break;
            }
                
    }

    public void stop() {
        player.stop();
        x = 0;
    }

    public void setVolume(double value) {
        if(player != null)
            player.setVolume(value);
    }

    public String getCurrentSong() {
        String labelText = null;
        if(song != null)
            labelText = song.getTitle();
        return labelText;
    }
    
    
    
}

