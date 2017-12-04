/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.util.ArrayList;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Fábio
 */
public class PlayList {

    
    private final IntegerProperty numberSongs = new SimpleIntegerProperty();
    private final StringProperty playListnName = new SimpleStringProperty();
    private final FloatProperty totalTime = new SimpleFloatProperty();
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ListProperty<Song> playListSongs = new SimpleListProperty<>();

    
    public ObservableList getPlayListSongs() {
        return playListSongs.get();
    }

    public void setPlayListSongs(ObservableList value) { //not sure we need this method...
        playListSongs.set(value);
    }

    public ListProperty playListSongsProperty() {
        return playListSongs;
    }

    
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    

    public float getTotalTime() {
        return totalTime.get();
    }

    public void setTotalTime(float value) {
        totalTime.set(value);
    }

    public FloatProperty totalTimeProperty() {
        return totalTime;
    }
    
    

    public String getPlayListnName() {
        return playListnName.get();
    }

    public void setPlayListnName(String value) {
        playListnName.set(value);
    }

    public StringProperty playListnNameProperty() {
        return playListnName;
    }
    

    public int getNumberSongs() {
        return numberSongs.get();
    }

    public void setNumberSongs(int value) {
        numberSongs.set(value);
    }

    public IntegerProperty numberSongsProperty() {
        return numberSongs;
    }
    
    public String getAllSongNamesFromPlayList(){
        for (Song playListSong : playListSongs) {
            return playListSong.getTitle();
        }
        return null;
        
    }
}
