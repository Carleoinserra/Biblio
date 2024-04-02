<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "app.libro"%>
<%
ArrayList<libro> lista= (ArrayList)request.getAttribute("lista");
String actionUrl = "prenota";
String actionUrl1 = "storico";
String actionUrl2 = "pendenze";
if (lista == null){
	out.print("E' null");
}
else {
	out.print("<form action='" + actionUrl + "' method='post'>");
for (int i = 0; i < lista.size(); i++){
	
	out.print(lista.get(i).getTitolo());
	out.print(lista.get(i).getAutore());
	out.print(lista.get(i).getAnno());
	out.print("<br>");
out.print("<input type='checkbox' id='ordini' name='ordini' value='" + lista.get(i).getTitolo() + "'>");
	
	out.print("<hr>");
	
	
}
out.print("<input type='submit' value='Invia'>");
out.print("</form>");
out.print("<hr>");
out.print("<hr>");
}
out.print("<form action='" + actionUrl1 + "' method='post'>");
out.print("<input type='submit' value='Visualizza storico'>");
out.print("</form>");
out.print("<hr>");
out.print("<hr>");
out.print("<form action='" + actionUrl2 + "' method='post'>");
out.print("<input type='submit' value='Visualizza libri da resituire'>");
out.print("</form>");
	


%>
</body>
</html>