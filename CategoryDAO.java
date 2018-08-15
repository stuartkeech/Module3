//Created by Pankti
package finalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

//The DAO class is responsible for retrieving data from the database. 
//In our case, we need to change sql to whatever we want to query

public class CategoryDAO {
    String databaseURL = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "system";
    String password = "tcs12345";
     
    public List<Category> list() throws SQLException {
        List<Category> listCategory = new ArrayList<>();
         
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "SELECT * FROM category ORDER BY name";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("category_id"); //catagory_id is a column name
                String name = result.getString("name"); // name is a column
                Category category = new Category(id, name);
                     
                listCategory.add(category);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return listCategory;
    }
}