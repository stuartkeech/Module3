

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




<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<select class="medium" id="elementreg" name="youare">
  <option value="" selected="selected"></option>
  <option value="death of policy holder">Death of policy holder</option>
  <option value="matured">Policy matured or expired</option>
  <option value="intermittent">Intermittent Claim</option>
  
</select>
<br>
<br>

<div class="types" id="death of policy holder">

  <label>If your reason for claim is death of a policy holder please upload image:</label>
  
   <form action="ListServlet" accept="image/gif,image/jpeg">
    <input type="file" name="pic" id="pic"><br>
    <br>
    
  <input type="submit" value="Upload">
</form>


    <br>
    <br>
    <label>If your reason for claim is matured/expired policy - enter expiration date here:</label>
    <div class="types" id="matured">
   <form name="myForm" action="ListServelet" method="post">
<input type="text" name="mytext" />
</form>
</div>

<br>
    <br>
<label>For Intermittent Claim:</label>
<div class="types" id="intermittent">
  <label>Please enter reason for closing:</label>
  <form name="myForm" action="ListServelet" method="post">
<input type="text" name="mytext" />
</form>
  
</div>
</div>


</div>


        


</body>
</html>