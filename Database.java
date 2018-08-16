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
	
    // created by Chin Han Chen on 2018/08/16
    public boolean checkDate(String polmID) throws Exception{
    	Statement st = null;
    	ResultSet rs = null;
		double temp1 = 0;
		Date temp2 = new Date();
		st = connection.createStatement();
		rs = st.executeQuery("select Policies.tenure, PolicyMap.start_date "+
		"from Policies inner join PolicyMap on Policies.policy_id = PolicyMap.policy_ID");
		while(rs.next()) {
			temp1 = rs.getDouble(1);
			temp2 = new Date(rs.getDate(1).getTime());
		}
		rs.close();
		st.close();
		LocalDate date1 = temp2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return(Period.between(date1,LocalDate.now()).getYears() >= temp1);
	}
    
    // created by Chin Han Chen on 2018/08/16
    public void inputData(int inp2,java.util.Date inp3, int inp5, String inp6, String inp7, Part filePart) throws Exception{
    	Statement st = null;
    	PreparedStatement pr = null;
		pr = connection.prepareStatement("insert into Claims values((select NVL(max(claim_id)+1,1) from Claims),?,?,null,?,?,?,?)");
		pr.setInt(2, inp2);
		pr.setDate(3,sqlDate);
		pr.setInt(5, inp5);
		pr.setString(6, inp6);
		pr.setString(7, inp7);
		if(filePart != null) {
			InputStream is = filePart.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while((nRead = is.read(data,0,data.length))!=-1) {
				buffer.write(data,0,nRead);
			}
			buffer.flush();
			byte[] filecontents = buffer.toByteArray();
			Blob b = con.createBlob();
			b.setBytes(1, filecontents);
			pr.setBlob(8, b);
		}else {
			pr.setBlob(8, (Blob)null);
		}
		pr.executeUpdate();
		pr.close();
	}
    
    // created by Chin Han Chen on 2018/08/16
    public void inputRejectionReason(String inp, String poliMid) {
    	PreparedStatement pr = null;
    	pr = connection.prepareStatement("insert into PolicyMap (reason_for_rejection) values (?) where policy_map_id = ?");
    	pr.setString(1,inp);
    	pr.setString(2, poliMid);
    	pr.executeUpdate();
    	
    	pr.close();
    }
    
    // created by Chin Han Chen on 2018/08/16
    public void inputRejectionStatus(int inp, String poliMid) {
    	PreparedStatement pr = null;
    	pr = connection.prepareStatement("update PolicyMap set approved = ? where policy_map_id = ?");
    	pr.setInt(1,inp);
    	pr.setString(2, poliMid);
    	pr.executeUpdate();
    	
    	pr.close();
    }
   
}
