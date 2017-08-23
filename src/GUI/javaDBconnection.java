package GUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class javaDBconnection {
    
    
    private static  Connection connection = null ; 
    public static Connection dbconection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rami M.Mohsen\\Documents\\NetBeansProjects\\EasyStat\\EasyStat.sqlite");
            return connection ; 
        } catch (ClassNotFoundException | SQLException e) {
            
            JOptionPane.showMessageDialog(null, e);
            return null ;
        }
    }
    
    
    
}
