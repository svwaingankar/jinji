<%@ page language="java" contentType="text/html" %>

<!doctype html>
<html>
<head>
</head>

<body>
<br/>
<strong><u>Better Than Cash Initial Data Import</u></strong>
<p>
<%
  if(request.getAttribute("importComplete")!=null) {

%>
Initial data import complete.
<%
    }
    else {
%>
<a href="InitialImport">Start initial import now!</a>
<%
    }
%>
</p>
 </div>
  <footer></footer>
</body>



</html>