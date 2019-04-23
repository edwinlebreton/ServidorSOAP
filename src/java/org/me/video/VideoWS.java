/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.video;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.me.conexion.DBConnection;
import org.me.modelo.Video;

/**
 *
 * @author edwinlebreton
 */
@WebService(serviceName = "VideoWS")
@Stateless()
public class VideoWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByTitle")
    public List<Video> findByTitle(@WebParam(name = "title") String title) {
        Connection connection = DBConnection.ConexionDB();
        List<Video> list= new ArrayList<Video>();
        String sql = "select * from videos where titulo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id= rs.getInt("id");
                String author= rs.getString("autor");
                String duration = rs.getTime("duracion").toString();
                int reproductions = rs.getInt("reproducciones");
                String description = rs.getString("descripcion");
                String format = rs.getString("formato");
                String creationDate = rs.getDate("fecha_creacion").toString();
                Video newVid = new Video(id, title, author, creationDate, 
                        duration, reproductions, description, format);
                list.add(newVid);
            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByAuthor")
    public List<Video> findByAuthor(@WebParam(name = "author") String author) {
        Connection connection = DBConnection.ConexionDB();
        List<Video> list= new ArrayList<Video>();
        String sql = "select * from videos where autor = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id= rs.getInt("id");
                String title= rs.getString("titulo");
                String duration = rs.getTime("duracion").toString();
                int reproductions = rs.getInt("reproducciones");
                String description = rs.getString("descripcion");
                String format = rs.getString("formato");
                String creationDate = rs.getDate("fecha_creacion").toString();
                Video newVid = new Video(id, title, author, creationDate, 
                        duration, reproductions, description, format);
                list.add(newVid);
            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByDate")
    public List<Video> findByDate(@WebParam(name = "date") String date) {
        Connection connection = DBConnection.ConexionDB();
        List<Video> list= new ArrayList<Video>();
        String sql = "select * from videos where fecha_creacion = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id= rs.getInt("id");
                String title= rs.getString("titulo");
                String author= rs.getString("autor");
                String duration = rs.getTime("duracion").toString();
                int reproductions = rs.getInt("reproducciones");
                String description = rs.getString("descripcion");
                String format = rs.getString("formato");
                String creationDate = Date.valueOf(date).toString();
                Video newVid = new Video(id, title, author, creationDate, 
                        duration, reproductions, description, format);
                list.add(newVid);
            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return list;
    }
}
