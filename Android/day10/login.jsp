<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
 String id = request.getParameter("id");
 String pwd = request.getParameter("pwd");
 String result = "0";
 if(id.equals("ming") && pwd.equals("11")){
	 result = "0";
 }else {
	 result = "1";
 }
 out.print(result);
%>