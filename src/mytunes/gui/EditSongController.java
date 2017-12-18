/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import mytunes.BE.Song;

/**
 * FXML Controller class
 *
 * @author Fábio
 */
public class EditSongController implements Initializable {

    @FXML
    private TextField txtfieldtitle;
    @FXML
    private TextField txtfieldartist;
    @FXML
    private TextField txtfieldtime;
    @FXML
    private TextField txtfieldfile;
    @FXML
    private ComboBox<String> comboCategory;
    
    SongModel model;
    private Song selectedSong;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboCategory.getItems().setAll("Classical","Jazz","Modern","Pop","R&B","ReligiousRock"); //sets the categories into the combobox
        // TODO
    }    

    @FXML
    private void buttonchoose(ActionEvent event) { //opens a pathfinder, to find a file
        String StringPath = null;
        
        final FileChooser fileChooser = new FileChooser();
        
        File filePath = fileChooser.showOpenDialog(null);
        if (filePath != null)
        {
            StringPath = filePath.getAbsolutePath();
        }
        
        txtfieldfile.setText(StringPath);
    }

    @FXML
    private void buttonsave(ActionEvent event) { //saves a song with the set variables, to the DB
        
        selectedSong.setTitle(txtfieldtitle.getText());
        selectedSong.setArtist(txtfieldartist.getText());
        selectedSong.setCategory(comboCategory.getValue());
        selectedSong.setTime(Float.parseFloat(txtfieldtime.getText()));
        selectedSong.setFilePath(txtfieldfile.getText());
        
        model.updateSong(selectedSong);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void bouttoncancel(ActionEvent event) { //closes the window
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(SongModel model) { //sets the model to the SongModel used in the mainwindowcontroller
        this.model = model;
    }
    
    public void setSong(Song selectedSong){ //sets the text and values in each textfield
        this.selectedSong = selectedSong; 
        txtfieldtitle.setText(selectedSong.getTitle());
        txtfieldartist.setText(selectedSong.getArtist());
        comboCategory.setValue(selectedSong.getCategory());
        txtfieldtime.setText(selectedSong.getTime() + "");
        txtfieldfile.setText(selectedSong.getFilePath());
        
    }
}
