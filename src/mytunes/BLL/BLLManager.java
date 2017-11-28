/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.util.List;
import mytunes.BE.Song;
import mytunes.DAL.DALManager;

/**
 *
 * @author Emil Pc
 */
public class BLLManager {
    DALManager DAL = new DALManager();
    
    public List<Song> getAllSongs(){
        return DAL.getAllSongs();
    }
}
