
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yejin.article.dto.ArticleDto" %>
<html>
  <head>
  <meta charset="UTF-8">
  <%
 // int id = request.getAttribute("id");
  ArticleDto article = (ArticleDto) request.getAttribute("article");

if(article!=null){  %>

<title>게시물 <%=article.getId()%></title>
<h1>게시물 <%=article.getId()%></h1>
  </head>
  <body>

<ul>
<li><%=article.getId()%></li>
<li ><%=article.getTitle()%></li>
<li ><%=article.getBody()%></li>
<li ><%=article.getDate()%></li>
<%}%>

<div class="btn">
<button class="third">
<a href ="/usr/article/list/free>"> 자유게시판 목록 </a>
</button>
</div>
  </body>
</html>