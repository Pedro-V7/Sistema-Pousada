/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DalPousada;

import java.sql.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ConexaoPousada {
     public static Connection conector(){
        java.sql.Connection conexao = null;
        //a linha abaixo chama o driver
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3307/pousada";
        String user ="root";
        String password = "";
        //Estabelecendo a conexão com o banco
        try{
           Class.forName(driver);
           conexao = DriverManager.getConnection(url,user,password);
           return conexao;
        }
       
        catch (Exception e){ 
        return null; 
       } 
    }
}
