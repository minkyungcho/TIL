<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	request.setCharacterEncoding("euc-kr");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<h1>REGISTER OK</h1>
<h2>${id }님 환영합니다.</h2>
<h3>아래 기본정보는 ${id }님의 등록 정보입니다.</h3>
<br><br>
<h4>ID : ${id }</h4>
<h4>PWD : ${pwd }</h4>
<h4>성별 : ${gender }</h4>
<h4>직업 : ${job }</h4>
<h4>취미 : ${hobbys }</h4>

</body>
</html>