<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<script>
$(document).ready(function(){
	$('input[type="button"]').click(function(){
		var c = confirm('회원가입하시겠습니까?');
		if(c == true){
			$('#r_form').attr('method','POST');
			$('#r_form').attr('action','user.do?cmd=useraddimpl'); /* user type에게 addimpl할거다. id,pwd,name이 뒤에 붙어서 req로 날아감 POST방식이라 안보임 */
			$('#r_form').submit();
		}
		
	});
});
</script>
<div class="center_page">
<h1>User Add Page</h1>
<form id="r_form">
ID<input type="text" name="id"><br>
PWD<input type="text" name="pwd"><br>
NAME<input type="text" name="name"><br>
<input type="button" value="REGISTER"><br>
</form>
</div>