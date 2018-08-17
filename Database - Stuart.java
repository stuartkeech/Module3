package claims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    		Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(databaseURL, user, password);
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
    }
    public void destroyConnection() {
    	try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch(NullPointerException e2) {
			e2.printStackTrace();
		}
    }
    //created by stuart 8/16/2018 3:34
    public String[][] getPolicies(Integer aproved){
    	ArrayList<String[]> output=new ArrayList<String[]>();
    	PreparedStatement statement=null;
    	ResultSet result=null;
    	try {
    		if (aproved==null)statement=connection.prepareStatement("Select Policy_map_id from Policymap where approved is NULL");
    		else statement=connection.prepareStatement("Select policy_map_id from policymap where approved="+aproved);    		
            result = statement.executeQuery();
            ArrayList<Integer> policyMapIDs=new ArrayList<Integer>();           
            while (result.next()) {
               policyMapIDs.add(result.getInt("policy_map_id"));             
            }
            for(int i:policyMapIDs) {
            	statement=connection.prepareStatement("SELECT customer_ID, policy_ID from PolicyMap where policy_Map_ID=?");
            	statement.setInt(1, i);
            	result=statement.executeQuery();
            	result.next();
            	String userID=result.getInt(1)+"";
            	int policyID=result.getInt(2);
            	statement=connection.prepareStatement("SELECT policy_name from Policies where policy_ID=?");            	
            	statement.setInt(1, policyID);
            	result=statement.executeQuery();
            	result.next();
            	String policyName=result.getString(1);
            	String[]out= {policyID+"",policyName,userID,i+""};
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
    	String[][] outputA = new String[output.size()][4];
    	for (int i = 0; i < output.size(); i++) {
    	    String[] row = output.get(i);
    	    outputA[i] =row;
    	}
    	return outputA;
    }
    //created by stuart 8/16/2018 2:45
    public String[][] getClaims(Integer aproved,int sum){
    	ArrayList<String[]> output=new ArrayList<String[]>();
    	PreparedStatement statement=null;
    	ResultSet result=null;
    	try {
    		if (aproved==null)statement=connection.prepareStatement("Select claim_id from claims where approved is NULL");
    		else statement=connection.prepareStatement("Select claim_id from claims where approved="+aproved);
            result = statement.executeQuery();
            ArrayList<Integer> claimIDs=new ArrayList<Integer>();           
            while (result.next()) {
               claimIDs.add(result.getInt("claim_id"));             
            }
            for(int i:claimIDs) {
            	statement=connection.prepareStatement("SELECT customer_ID, policy_ID, premium_amount from PolicyMap where policy_Map_ID=(Select policy_map_id from claims where claim_id=?)");
            	statement.setInt(1, i);
            	result=statement.executeQuery();
            	result.next();
            	String userID=result.getInt(1)+"";
            	int policyID=result.getInt(2);
            	statement=connection.prepareStatement("SELECT sum_assured from Policies where policy_ID=?");
            	statement.setInt(1, policyID);
            	result=statement.executeQuery();
            	result.next();
            	double claimAmount=result.getDouble(1);
            	String[]out= {userID,policyID+"",claimAmount+"",i+""};
            	if(claimAmount<sum ||sum==-1)output.add(out);            	
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
    	String[][] outputA = new String[output.size()][4];
    	for (int i = 0; i < output.size(); i++) {
    	    String[] row = output.get(i);
    	    outputA[i] =row;
    	}
    	return outputA;
    }
   
}