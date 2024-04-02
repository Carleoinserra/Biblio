<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
out.print("Lista  dei libri da restituire");
out.print("</h1>");
for (String lib : lista){
	out.print("<h2>");
	out.print(cont + " Titolo: " + lib);
	out.print("<h3>");
	
}

%>
</body>
</html>
</body>
</html>