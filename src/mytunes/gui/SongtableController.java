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

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class SongtableController implements Initializable {

    @FXML
    private TextField txtfieldtitle;
    @FXML
    private TextField txtfieldartist;
    @FXML
    private TextField txtfieldtime;
    @FXML
    private TextField txtfieldfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonchoose(ActionEvent event) {
    }

    @FXML
    private void buttonsave(ActionEvent event) {
    }

    @FXML
    private void bouttoncancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void musiccategory(ActionEvent event) {
    }
    
}
