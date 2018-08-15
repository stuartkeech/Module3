

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Created by Pankti</title>
</head>
<body>


<div align="center">
    <h2>Initiate Claim</h2>
    <form action="list" method="post">
        Select a Policy<br>
        <select name="category">
            <c:forEach items="${listCategory}" var="category">
                <option value="${category.id}"
                    <c:if test="${category.id eq selectedCatId}">selected="selected"</c:if>>
          
                    ${category.name}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
 <input type="submit" value="Submit" />
    </form>       
    <br>
    <br>
    <br>
    <form>
  Nominee:<br>
  <input type="text" name="Nominee"><br>
  
</form>
<br>
<br>
<br>
<form>
  Expiry/Mature Date:<br>
  <input type="text" name="Expiry or Mature Date"><br>
  
</form>
<br>
<br>
<br>
<h2>Reason for Claim</h2>
       
    <a href="#Death of policy holder">Death of policy holder</a> <br>
    <a href="Policy matured or expired">Policy matured or expired</a><br>
    <a href="Intermittent Claim">Intermittent Claim</a><br>
    
        


</div>


        


</body>
</html>