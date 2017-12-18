/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import mytunes.BE.PlayList;



/**
 * FXML Controller class
 *
 * @author Yindo
 */
public class PlayListEditController implements Initializable {

    SongModel model;
    @FXML
    private TextField txtfieldname;
    private PlayList selectedPlayList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setModel(SongModel model) { //sets the model to the SongModel from the MWC
    this.model = model;
  
    }

    @FXML
    private void buttonsave(ActionEvent event) { //saves the PlayList with the new variable
        
        selectedPlayList.setPlayListnName(txtfieldname.getText());
        
        model.updatePlayList(selectedPlayList);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void butotncancel(ActionEvent event) { //closes the window
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
 
 public void setPlayList(PlayList selectedPlayList){ //sets the text in the textfield
        this.selectedPlayList = selectedPlayList;
        txtfieldname.setText(selectedPlayList.getPlayListnName());

    }
}   
