<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.dan {
   width:100px;
   height:30px;
   border: outset #f333;
   background-color:pink;
   display:block;
   margin:10 auto;
}
.a {
   display:block;
   padding:10px;
   float;
   background-color:#F5E7F4;
   text-align:center;
}
</style>
<%@ page import="java.util.Date" %>
<%
int dan = (int)request.getAttribute("dan");
int limit = (int)request.getAttribute("limit");
%>
<html>
  <head>
  <meta charset="UTF-8">
  <title>GuGuDan page</title>
  </head>
  <body>
    <h1><%=dan%>단</h1>

    <div class="a">
    <% for(int i=1;i<=limit;i++) { %>
    <div class="dan"><%=dan%> * <%=i%> = <%=dan*i%></div>
    <% } %>
    </div>
    <h4><%= new Date() %></h2>
  </body>
</html>