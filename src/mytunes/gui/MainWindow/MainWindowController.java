/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.MainWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Fábio
 * @auther Nicolai
 * @author Emil Schütt
 * @author Bastian
 */
public class MainWindowController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<?> playList;
    @FXML
    private TableView<?> songsList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playlistNew(ActionEvent event) {
    }

    @FXML
    private void playListEdit(ActionEvent event) {
    }

    @FXML
    private void playListDelete(ActionEvent event) {
    }

    @FXML
    private void songListNew(ActionEvent event) {
    }

    @FXML
    private void songListEdit(ActionEvent event) {
    }

    @FXML
    private void songListDelete(ActionEvent event) {
    }

    @FXML
    private void songPlay(ActionEvent event) {
    }

    @FXML
    private void songLast(ActionEvent event) {
    }

    @FXML
    private void songNext(ActionEvent event) {
    }

    @FXML
    private void SongVolumeDragDetected(MouseEvent event) {
    }
    
}
