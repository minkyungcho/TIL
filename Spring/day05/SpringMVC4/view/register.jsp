<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=902cb96a96e5d5281900c283095b217c"></script> 
<script>
$(document).ready(function(){
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(37.5012428, 127.03958590000002),
		level: 3
	};
	var map = new kakao.maps.Map(container, options);
});
</script>
<div class="center_page">
<h1>Register Page</h1><br>
<div id="map" style="width:500px;height:400px;"></div>
</div>