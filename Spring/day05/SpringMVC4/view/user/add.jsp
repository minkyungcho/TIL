<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_page">
<h1>User Add Page</h1><br>
<form action="useraddimpl.mc" method="POST">
ID <input type="text" name="id"><br>
PWD <input type="text" name="pwd"><br>
NAME <input type="text" name="name"><br>
<input type="submit" value="ADD"><br>
</form>
</div>