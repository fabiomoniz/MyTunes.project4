/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;
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
    
    public void loadAll() //loads all the songs into the songList
    {
        sList.clear();
        sList.addAll(bllManager.getAllSongs());
    }
    
    public void add(Song song) { //adds new song to DB and songList
        bllManager.add(song);
        sList.add(song);
    }
    
    public void add(PlayList playList){ //adds new PlayList to DB and PlayList List
        bllManager.add(playList);
        pList.add(playList);
    }
    
    public void remove(Song selectedSong) //deletes song from DB and SongList, and PlayList
    {
        bllManager.remove(selectedSong);
        sList.remove(selectedSong);        
        for (PlayList pl : pList) {
            if(pl.getPlayListSongs().contains(selectedSong))
                pl.remove(selectedSong);
        }
        xList.remove(selectedSong);
    }

    public void remove(PlayList selectedPlayList) { //deletes PlayList from the PlayList list and DB
        pList.remove(selectedPlayList); 
        bllManager.remove(selectedPlayList);
        
    }

    public void addSongToPlayList(Song selectedSong, PlayList selectedPlayList) { //add song from SongList to PlayList
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
        bllManager.addSongToPlayList(selectedSong,selectedPlayList); //adds reference to the DB
    }

    
    public void setAllSongsIntoPlayLists(){ //sets songs into playlist, based on ID's and Id references in DB
        
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
    
    public void updateSong(Song song){ //updates... song :)
        
        bllManager.updateSong(song);
        
    }

    public void updatePlayList(PlayList playlist) { //pllllllllllllllaylist
        
        bllManager.updatePlayList(playlist);
    }

    public void remove(PlayList selectedPlayList, Song selectedSong) { //removes song from playlist, including the DB reference
        songsInPlayList sip = null;
        Song s = null;
        
        for (songsInPlayList sp : xList) {
            
            if (sp.getPlayListId() == selectedPlayList.getId() && sp.getSongId() == selectedSong.getId())
            {
                sip = sp;
            }
            System.out.println(sp);
        }
        
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

    void search(String search) { //searches for a song in the songlist has a variable equal to the search term
        
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
        }
        
    }

    void moveUp(PlayList selectedPlayList, Song selectedSong) { //moves song up in the list index
        ObservableList<Song> dick = selectedPlayList.getPlayListSongs();
        int a = selectedPlayList.getPlayListSongs().indexOf(selectedSong);
        for (Song song : dick) {
            if(a == 0)
                break;
            if(y == a) {
                Song tempSong = dick.get(y-1);
                dick.set(y-1, song);
                dick.set(y, tempSong);
                break;
            }
            else {
               y++; 
            }
                
        }
        y = 0;
    }
    
    public void moveDown(PlayList selectedPlayList, Song selectedSong) { //moves song down in the list index
        ObservableList<Song> dick = selectedPlayList.getPlayListSongs();
        int a = selectedPlayList.getPlayListSongs().indexOf(selectedSong);
        for (Song song : dick) {
            if(a == dick.size()-1)
                break;
            if(y == a) {
                Song tempSong = dick.get(y+1);
                dick.set(y+1, song);
                dick.set(y, tempSong); 
                break;
            }
            else {
               y++; 
            }
                
        }
        y = 0;
    }

    public void play(Song selectedSong, PlayList selectedPlayList) {  //Overloading play method
        bllManager.play(selectedSong, selectedPlayList);
    }

//    public void play(PlayList selectedPlayList) { //plays 
//        bllManager.play(selectedPlayList);
//    }

    public void stop() {
        bllManager.stop();
    }

    public void setVolume(double value) {
        bllManager.setVolume(value);
    }

    public String getCurrentSong() {
        return bllManager.getCurrentSong();
    }

    public MediaPlayer getPlayer() {
        MediaPlayer player = bllManager.getPlayer();
        return player;
    }

    void setXto0() {
        bllManager.setXto0();
    }

    String getPlayState() {
        return bllManager.getPlayState();
    }



   

}



