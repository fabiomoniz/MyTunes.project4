/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

/**
 *
 * @author Fábio
 */
public class Song {
    
    private int id;
    private String title;
    private String artist;
    private String category;
    private float time;
    private String filePath;           
    
    public Song(int id , String title , String artist , String category , float time , String filePath ){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.time = time;
        this.filePath = filePath;
    }
    
    
    
}
