/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yindo
 */
public class Playlist {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty songs = new SimpleIntegerProperty();
    private final FloatProperty time = new SimpleFloatProperty();

    public float getTime() {
        return time.get();
    }

    public void setTime(float value) {
        time.set(value);
    }

    public FloatProperty timeProperty() {
        return time;
    }
    

    public int getSongs() {
        return songs.get();
    }

    public void setSongs(int value) {
        songs.set(value);
    }

    public IntegerProperty songsProperty() {
        return songs;
    }
    
    
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
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
    
    
    
}
