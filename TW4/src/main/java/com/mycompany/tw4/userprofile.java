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


@Path("user")

public class userprofile {
   private Connection con;
   
    @Context
    private UriInfo context;
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addCourse(@FormParam("username") String username,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("createdate") String createdate)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg1;
        try {
            ps = con.prepareStatement("INSERT INTO userprofile(username,firstname,lastname,createdate) VALUES (?,?,?,?)");
            ps.setString(1, username);
            ps.setString(2, firstname);
            ps.setString(3,lastname);
            ps.setString(4,createdate);
            
            ps.executeUpdate();
            
            msg1 = "User Inserted Successfully...";
        } catch (Exception e) {
            msg1 = "exception "+e;
        }
       return msg1;
        
    }
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public List Showmsg() throws SQLException
//    {
//        ResultSet rs = null;
//        List showmsg = new ArrayList();
//        String query = "Select * from message";
//        PreparedStatement stmt = con.prepareStatement(query); 
//         rs = stmt.executeQuery();  
//             while (rs.next()) 
//             {  
//                 int id = rs.getInt("id");  
//                 String msg = rs.getString("msg");  
//                 String createdate = rs.getString("createdate");   
//                 String author = rs.getString("author");
//                 showmsg.add(id);
//                 showmsg.add(msg);
//                 showmsg.add(createdate);
//                 showmsg.add(author);
//             }
//             
//                return showmsg;
//    }
//     
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String deleteCourse(@FormParam("id") int id)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg;
        try {
            ps = con.prepareStatement("delete from userprofile where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            msg = "User Profile Deleted Sucessfully...";
        } catch (Exception e) {
            msg = "exception "+e;
        }
       return msg;
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateMessage(@FormParam("id") int id,@FormParam("username") String username,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("createdate") String createdate)
    {
        PreparedStatement ps;
        con=DbCon.getConnection();
        String msg1;
        try {
            ps = con.prepareStatement("UPDATE userprofile set username = ?,firstname = ?,lastname = ?, createdate = ? WHERE id=?");
            ps.setString(1, username);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, createdate);
            ps.setInt(5, id);
            ps.executeUpdate();
            
            msg1 = "USerProfile Updated Successfully...";
        } catch (Exception e) {
            msg1 = "exception "+e;
        }
       return msg1;
    }
    
    
}