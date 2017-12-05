/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Fábio
 */
public class songsInPlayList {

    private final IntegerProperty playListId = new SimpleIntegerProperty();
    private final IntegerProperty songId = new SimpleIntegerProperty();

    public int getSongId() {
        return songId.get();
    }

    public void setSongId(int value) {
        songId.set(value);
    }

    public IntegerProperty songIdProperty() {
        return songId;
    }
    

    public int getPlayListId() {
        return playListId.get();
    }

    public void setPlayListId(int value) {
        playListId.set(value);
    }

    public IntegerProperty playListIdProperty() {
        return playListId;
    }

    @Override
    public String toString() {
        return String.valueOf(getPlayListId()) + " " + String.valueOf(getSongId()) ;
    }
    
    
}
