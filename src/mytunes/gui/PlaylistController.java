package mytunes.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
 * @author ZeXVex
 */
public class PlaylistController implements Initializable {

    SongModel model;
    
    @FXML
    private TextField txtfieldname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonsave(ActionEvent event) {
        PlayList pl = new PlayList();
        
        pl.setPlayListnName(txtfieldname.getText());
        
        model.add(pl);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void butotncancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(SongModel model) {
        this.model = model;
    }
}
