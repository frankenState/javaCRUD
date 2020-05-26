/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author frank lou
 */
public class DB {
    
    private Connection conn;
    private Statement stmt;
    
    public DB(){
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/SISDB", "root", "");
            this.stmt = conn.createStatement();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean query(String sql){
        
        try {
            
           return stmt.executeUpdate(sql) == 1;
           
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
        
    }
    
    public ArrayList select(String sql, String[] colNames){
        ArrayList array;
        ResultSet rs;
        
        try {
            array = new ArrayList();
            
            rs = this.stmt.executeQuery(sql);
            
            while (rs.next()){
                String[] row = new String[colNames.length];
                
                for (int i = 0; i < colNames.length; i++) {
                    row[i] = rs.getString(colNames[i]);
                }
                
                array.add(row);
                
            }
            
            return array;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return new ArrayList();
    }
    
    
    
    
}
