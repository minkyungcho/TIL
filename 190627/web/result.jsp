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
<h2>${id }�� ȯ���մϴ�.</h2>
<h3>�Ʒ� �⺻������ ${id }���� ��� �����Դϴ�.</h3>
<br><br>
<h4>ID : ${id }</h4>
<h4>PWD : ${pwd }</h4>
<h4>���� : ${gender }</h4>
<h4>���� : ${job }</h4>
<h4>��� : ${hobbys }</h4>

</body>
</html>