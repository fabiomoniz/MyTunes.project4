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
 * @author Fábio
 */
public class Song {   

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();    
    private final StringProperty category = new SimpleStringProperty();
    private final FloatProperty time = new SimpleFloatProperty();
    private final StringProperty filePath = new SimpleStringProperty();
    private final StringProperty artist = new SimpleStringProperty();

    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String value) {
        artist.set(value);
    }

    public StringProperty artistProperty() {
        return artist;
    }
    

    public String getFilePath() {
        return filePath.get();
    }

    public void setFilePath(String value) {
        filePath.set(value);
    }

    public StringProperty filePathProperty() {
        return filePath;
    }
    

    public float getTime() {
        return time.get();
    }

    public void setTime(float value) {
        time.set(value);
    }

    public FloatProperty timeProperty() {
        return time;
    }
    

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }
    

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
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

    @Override
    public String toString() {
        return getTitle() ;
    }
    
    

}
