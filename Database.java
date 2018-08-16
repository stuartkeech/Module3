package claims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

//The DAO class is responsible for retrieving data from the database. 
//In our case, we need to change sql to whatever we want to query

public class Database {
    private String databaseURL = "jdbc:oracle:thin:@10.101.1.152:1521:xe";
    private String user = "system";
    private String password = "tcs12345";
    private Connection connection;	
    public void createConnection() {
    	try {
			connection = DriverManager.getConnection(databaseURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
    }
    public void destroyConnection() {
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e2) {
			e2.printStackTrace();
		}
    }
    public String[][] getClaims(Integer aproved){
    	ArrayList<String[]> output=new ArrayList<String[]>();
    	Statement statement=null;
    	ResultSet result=null;
    	try {
    		statement=connection.createStatement();
            result = statement.executeQuery("Select claim_id from claims where aproved=null");
            ArrayList<Integer> claimIDs=new ArrayList<Integer>();           
            while (result.next()) {
               claimIDs.add(result.getInt("claim_id"));             
            }          
            for(int i:claimIDs) {
            	result=statement.executeQuery("SELECT customer_ID, policy_ID, premium_amount from PolicyMap where policy_Map_ID=(Select policy_map_id from claims where claim_id="+i+")");
            	result.next();
            	String userID=result.getInt(0)+"";
            	String policyID=result.getInt(1)+"";
            	result=statement.executeQuery("SELECT sum_assured from Policies where policy_ID="+policyID);
            	result.next();
            	String claimAmount=result.getDouble(0)+"";
            	String[]out= {userID,policyID,claimAmount};
            	output.add(out);            	
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    	try {
			result.close();
			statement.close();
	        connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
    	String[][] outputA = new String[output.size()][3];
    	for (int i = 0; i < output.size(); i++) {
    	    String[] row = output.get(i);
    	    outputA[i] =row;
    	}
    	return outputA;
    }
   
}