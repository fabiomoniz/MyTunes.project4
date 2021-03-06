/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.BE.PlayList;
import mytunes.BE.Song;
import mytunes.BE.songsInPlayList;

/**
 *
 * @author Emil Pc
 */
public class DALManager {
    
    ConnectionManager cm = new ConnectionManager();
    
    public List<Song> getAllSongs() { //Get the songs from the database to the programe
        List<Song> allSongs
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM SongTable");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getInt("id"));
                song.setTitle(rs.getString("title"));
                song.setArtist(rs.getString("artist"));
                song.setCategory(rs.getString("category"));
                song.setTime(rs.getFloat("time"));
                song.setFilePath(rs.getString("filePath"));

                allSongs.add(song);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSongs;
    }
    
    public List<PlayList> getAllPlayList() { //Get playlist form the database
        List<PlayList> allPlayLists
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM PlayList");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PlayList playList = new PlayList();
                playList.setId(rs.getInt("id"));
                playList.setPlayListnName(rs.getString("name"));

                allPlayLists.add(playList);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allPlayLists;
    }
    
    public void addSong(Song song) { // Add songs from the programe to thte database
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO SongTable"
                    + "(title, artist, category, time, filePath) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getCategory());
            pstmt.setFloat(4, song.getTime());
            pstmt.setString(5, song.getFilePath());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Song could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                song.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void addPlayList(PlayList playList) { //Add playlist from the programe to the database
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO PlayList"
                    + "(name) "
                    + "VALUES(?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playList.getPlayListnName());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("PlayList could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                playList.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Song selectedSong) {
        try (Connection con = cm.getConnection()) {
           
            String sql
                    = "DELETE FROM songsInPlayList WHERE songId=?"; //delete from songsInPlayList first 
            PreparedStatement pstmt                                     //to avoid the "DELETE statement conflicted with the REFERENCE constraint"
                    = con.prepareStatement(sql);                        // error , where deleting a playlist which is getting information
            pstmt.setInt(1, selectedSong.getId());                  // information from another table accessing the same id.
            pstmt.execute(); 
            
            
            String sql2
                    = "DELETE FROM SongTable WHERE id=?";
            PreparedStatement pstmt2
                    = con.prepareStatement(sql2);
            pstmt2.setInt(1, selectedSong.getId());
            pstmt2.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void remove(PlayList selectedPlayList) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "DELETE FROM songsInPlayList WHERE playListId=?"; //delete from songsInPlayList first 
            PreparedStatement pstmt                                     //to avoid the "DELETE statement conflicted with the REFERENCE constraint"
                    = con.prepareStatement(sql);                        // error , where deleting a playlist which is getting information
            pstmt.setInt(1, selectedPlayList.getId());                  // information from another table accessing the same id.
            pstmt.execute();                                            //like a "nullPointerException" -> (not the same but similar)
            
            String sql2
                    = "DELETE FROM PlayList WHERE id=?";
            PreparedStatement pstmt2
                    = con.prepareStatement(sql2);
            pstmt2.setInt(1, selectedPlayList.getId());
            pstmt2.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }}

    public void addSongToPlayList(PlayList selectedPlayList, Song selectedSong) { //Add reference for songs in playlist
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO songsInPlayList"
                    + "(songId, playListId) "
                    + "VALUES(?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.setInt(2, selectedPlayList.getId());

            int affected = pstmt.executeUpdate();
            
            if (affected<1)
                throw new SQLException("Song could not be added to the playlist");

        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public List<songsInPlayList> getAllSp() { //get all songs in playlists, based on the references
        List<songsInPlayList> allSongs
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM songsInPlayList");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                songsInPlayList sp = new songsInPlayList();
                sp.setPlayListId(rs.getInt("playListId"));
                sp.setSongId(rs.getInt("songId"));

                allSongs.add(sp);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSongs;
    }

    public void updateSong(Song song) { 
             try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE SongTable SET "
                    + "title=?, artist=?, category=?, time=?, filePath=? "
                    + "WHERE id=?";
                    
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getCategory());
            pstmt.setFloat(4, song.getTime());
            pstmt.setString(5, song.getFilePath());
            pstmt.setInt(6, song.getId());
           

            int affected = pstmt.executeUpdate();
            
            if (affected<1)
                throw new SQLException("Could not update Song");

        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        
    }

    public void updatePlayList(PlayList playlist) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE PlayList SET "
                    + "name=? "
                    + "WHERE id=?";
                    
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playlist.getPlayListnName());
            pstmt.setInt(2, playlist.getId());
          
           

            int affected = pstmt.executeUpdate();
            
            if (affected<1)
                throw new SQLException("Could not update playlist");

        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
       
    }

    public void remove(songsInPlayList selectedSongInPlayList) { //delete reference for song in playlist
        try (Connection con = cm.getConnection()) {
           
            String sql
                    = "DELETE FROM songsInPlayList WHERE playListId=? AND songId=? "; 
            PreparedStatement pstmt                                     
                    = con.prepareStatement(sql);                        
            pstmt.setInt(1, selectedSongInPlayList.getPlayListId());  
            pstmt.setInt(2, selectedSongInPlayList.getSongId());// 
            
            pstmt.execute(); 
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
}
