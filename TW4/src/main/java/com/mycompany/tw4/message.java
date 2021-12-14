/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tw4;


import com.mycompany.util.DbCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Path("entermsg")

public class message {
   private Connection con;
   
    @Context
    private UriInfo context;
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addCourse(@FormParam("msg") String msg,@FormParam("createdate") String createdate,@FormParam("author") String author)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg1;
        try {
            ps = con.prepareStatement("INSERT INTO message(msg,createdate,author) VALUES (?,?,?)");
            ps.setString(1, msg);
            ps.setString(2, createdate);
            ps.setString(3,author);
            
            ps.executeUpdate();
            
            msg1 = "Message Inserted Successfully...";
        } catch (Exception e) {
            msg1 = "exception "+e;
        }
       return msg1;
        
    }
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List Showmsg() throws SQLException
    {
        List datas = new ArrayList();  
        con=DbCon.getConnection();
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery("select * from message");  
             while (rs.next()) 
             {  
                 int id = rs.getInt("id");  
                 String msg = rs.getString("msg");  
                 String createdate = rs.getString("createdate");   
                 String author = rs.getString("author");
                 datas.add(id);
                 datas.add(msg);
                 datas.add(createdate);
                 datas.add(author);
             }  
             
                return datas;
    }
     
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String deleteCourse(@FormParam("id") int id)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg;
        try {
            ps = con.prepareStatement("delete from message where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            msg = "Message Deleted Sucessfully...";
        } catch (Exception e) {
            msg = "exception "+e;
        }
       return msg;
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateMessage(@FormParam("id") int id,@FormParam("msg") String msg,@FormParam("createdate") String createdate,@FormParam("author") String author)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg1;
        try {
            ps = con.prepareStatement("UPDATE message set msg = ?,createdate = ?,author = ? WHERE id=?");
            ps.setString(1, msg);
            ps.setString(2, createdate);
            ps.setString(3, author);
            ps.setInt(4, id);
            ps.executeUpdate();
            
            msg1 = "Message Updated Successfully...";
        } catch (Exception e) {
            msg1 = "exception "+e;
        }
       return msg1;
    }
    
    
}