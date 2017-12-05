/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.util.ArrayList;
import java.util.List;
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


    private final StringProperty playListnName = new SimpleStringProperty();
    private final IntegerProperty id = new SimpleIntegerProperty();
    public final ListProperty<Song> playListSongs = new SimpleListProperty<>();
    
    public ObservableList getPlayListSongs() {
        return playListSongs.get();
    }

    public void setPlayListSongs(ObservableList value) { 
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
    
    public String getPlayListnName() {
        return playListnName.get();
    }

    public void setPlayListnName(String value) {
        playListnName.set(value);
    }

    public StringProperty playListnNameProperty() {
        return playListnName;
    }
    
    public ObservableList<Song> getAllSongNamesFromPlayList(){
        return playListSongs.get();
    }

    @Override
    public String toString() {
        return getPlayListnName();
    }
    
    
}
