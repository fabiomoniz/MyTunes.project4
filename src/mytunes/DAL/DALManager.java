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

/**
 *
 * @author Emil Pc
 */
public class DALManager {
    
    ConnectionManager cm = new ConnectionManager();
    
    public List<Song> getAllSongs() {
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
    
    public List<PlayList> getAllPlayList() {
        List<PlayList> allPlayLists
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM PlayList");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PlayList playList = new PlayList();
                playList.setId(rs.getInt("id"));
                playList.setNumberSongs(rs.getInt("songs"));
                playList.setPlayListnName(rs.getString("name"));
                playList.setTotalTime(rs.getFloat("time"));

                allPlayLists.add(playList);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allPlayLists;
    }
    
    public void addSong(Song song) {
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
    
    public void addPlayList(PlayList playList) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO PlayList"
                    + "(songs, name, time) "
                    + "VALUES(?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, playList.getNumberSongs());
            pstmt.setString(2, playList.getPlayListnName());
            pstmt.setFloat(3, playList.getTotalTime());

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
                    = "DELETE FROM SongTable WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void remove(PlayList selectedPlayList) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "DELETE FROM PlayList WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlayList.getId());
            pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }}
    
}
