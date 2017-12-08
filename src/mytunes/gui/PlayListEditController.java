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
<<<<<<< HEAD
import javafx.scene.Node;
import javafx.scene.control.TextField;
import mytunes.BE.PlayList;
import mytunes.BE.Song;


=======
import javafx.scene.control.TextField;
>>>>>>> 0c5fc2abbd8a047ec49dbd5f51bb97783abb707b

/**
 * FXML Controller class
 *
<<<<<<< HEAD
 * @author Yindo
 */
public class PlayListEditController implements Initializable {

    SongModel model;
    @FXML
    private TextField txtfieldname;
    private PlayList selectedPlayList;
=======
 * @author Fábio
 */
public class PlayListEditController implements Initializable {

    @FXML
    private TextField txtfieldname;

>>>>>>> 0c5fc2abbd8a047ec49dbd5f51bb97783abb707b
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
<<<<<<< HEAD
    
    
    public void setModel(SongModel model) {
    this.model = model;
  
    }

    @FXML
    private void buttonsave(ActionEvent event) {
        
        selectedPlayList.setPlayListnName(txtfieldname.getText());
        
        model.updatePlayList(selectedPlayList);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
=======

    @FXML
    private void buttonsave(ActionEvent event) {
>>>>>>> 0c5fc2abbd8a047ec49dbd5f51bb97783abb707b
    }

    @FXML
    private void butotncancel(ActionEvent event) {
<<<<<<< HEAD
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
 
 public void setPlayList(PlayList selectedPlayList){
        this.selectedPlayList = selectedPlayList;
        txtfieldname.setText(selectedPlayList.getPlayListnName());

    }
}   
=======
    }
    
}
>>>>>>> 0c5fc2abbd8a047ec49dbd5f51bb97783abb707b
