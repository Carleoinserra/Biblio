<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%



ArrayList<String> lista= (ArrayList)request.getAttribute("lista");
int cont = 1;
out.print("<h1>");
out.print("Lista dello storico dei libri prenotati");
out.print("</h1>");
for (String lib : lista){
	out.print("<h2>");
	out.print(cont + " Titolo: " + lib);
	out.print("<h3>");
	
}

%>
</body>
</html>