<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="com.yejin.*" %>
<html>
  <head>
  <meta charset="UTF-8">
  <title>Factorization Page</title>
  </head>
  <body>
    <h1>시작</h1>
    <% Rq rq = new Rq(request,response);
    int num=rq.getIntParam("num",10);
    out.println(num);
    for(int i=0;i<num;i++){
        for(int j=0;j<num;j++){
            out.println("["+j+"]");
        }
        out.println("\n");
    }
    %>

    <h2><%= new Date() %></h2>
  </body>
</html>