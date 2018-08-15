<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="claims.CategoryDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Claims</title>
</head>
<body>

<%! CategoryDAO db=new CategoryDAO();%>

	<form action="claim">
	<table>
	<tr>
		<td>
		User ID
		</td>
		<td>
		Policy ID
		</td>
		<td>
		Reason for Claim
		</td>
		<td>
		Claim Amount
		</td>	
		
	</tr>
	<%	
	String[][] DBout=db.getPendingClaims(null);
	for(int i=0;i<DBout.length;i++){
	%>
	<tr>
		<td>
		<%=DBout[i][0]%>
		</td>
		<td>
		<%=DBout[i][1]%>
		</td>
		<td>
		<%=DBout[i][2]%>
		</td>
		<td>
		<%=DBout[i][3]%>
		</td>
		<td>	
		
		<button name="accept" value="accept<%=i%>">Accept</button>
		</td>
		<td>
		<button name="reject" value="reject<%=i%>">Reject</button>
		</td>		
	</tr>
	
	<%
	}
	%>
	</table>
	</form>
</body>
</html>