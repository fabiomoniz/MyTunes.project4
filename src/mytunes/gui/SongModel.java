/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Song> tempList = new ArrayList<>();
    
    int x = 0; // is used for the switch on the search()
    int y = 0; // to determine and meddle with the position of a song in a playlist in the moveUp() and moveDown() 
    
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
        for (PlayList pl : pList) {
            if(pl.getPlayListSongs().contains(selectedSong))
                pl.remove(selectedSong);
        }
        xList.remove(selectedSong);
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
        songsInPlayList newSong = new songsInPlayList();
        newSong.setPlayListId(selectedPlayList.getId());
        newSong.setSongId(selectedSong.getId());
        xList.add(newSong);
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
        
        bllManager.updateSong(song);
        
    }

    public void updatePlayList(PlayList playlist) {
        
        bllManager.updatePlayList(playlist);
    }

    public void remove(PlayList selectedPlayList, Song selectedSong) {
        songsInPlayList sip = null;
        Song s = null;
        
        for (songsInPlayList sp : xList) {
            
            if (sp.getPlayListId() == selectedPlayList.getId() && sp.getSongId() == selectedSong.getId())
            {
                sip = sp;
            }
            System.out.println(sp);
        }
        
//        if (sip != null)
//        {
//            xList.remove(sip);
//        }
        
        for (Song sp : sList) {
            if (sp.getId() == selectedSong.getId())
            {
                s = sp;
            }
            System.out.println(sp);
        }
        
        if(s != null)
        {
            songsInPlayList.remove(s);
        }
        xList.remove(sip);
        selectedPlayList.remove(selectedSong);
        System.out.println(songsInPlayList);
        bllManager.removeFromPlayList(sip);
    }

    void search(String search) {
        
        ArrayList<Song> tempList2 = new ArrayList();
        switch (x){
            case 0 :
                tempList.clear();
                tempList.addAll(sList);
                for (Song song : sList) {
                    if(song.getTitle().toLowerCase().equals(search) || song.getCategory().toLowerCase().equals(search) || song.getArtist().toLowerCase().equals(search))    {
                    tempList2.add(song);
                }
            }
            sList.clear();
            sList.addAll(tempList2);
            x = 1;
            break;
            
            case 1 :
            sList.clear();
            sList.addAll(tempList);
            x= 0;
            break;
            
            /*default:
            System.out.println("eat adick");
            break;*/
        }
        
    }

    void moveUp(PlayList selectedPlayList, Song selectedSong) {
        ObservableList<Song> dick = selectedPlayList.getPlayListSongs();
        int a = selectedPlayList.getPlayListSongs().indexOf(selectedSong);
        for (Song song : dick) {
            if(a == 0)
                break;
            if(y == a) {
                Song tempSong = dick.get(y-1);
                dick.set(y-1, song);
                dick.set(y, tempSong); 
                //selectedPlayList.getPlayListSongs().setAll(dick);
                break;
            }
            else {
               y++; 
            }
                
        }
        y = 0;
    }
    
    void moveDown(PlayList selectedPlayList, Song selectedSong) {
        ObservableList<Song> dick = selectedPlayList.getPlayListSongs();
        int a = selectedPlayList.getPlayListSongs().indexOf(selectedSong);
        for (Song song : dick) {
            if(a == dick.size()-1)
                break;
            if(y == a) {
                Song tempSong = dick.get(y+1);
                dick.set(y+1, song);
                dick.set(y, tempSong); 
                //selectedPlayList.getPlayListSongs().setAll(dick);
                break;
            }
            else {
               y++; 
            }
                
        }
        y = 0;
    }

}



