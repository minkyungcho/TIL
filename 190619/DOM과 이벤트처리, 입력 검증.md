## DOM과 이벤트처리, 입력 검증

### 문서 객체 모델 DOM

innerText  사용하여 값 가져오고 값 집어넣기.

```javascript
<script>
function c1(){
	var h1 = document.getElementById('h1').innerText; // 가져올때
	document.getElementById('h1').innerText = h1 + ' ADD TEXT'; // 집어넣을때
};
</script>
</head>
<body>
<h1 id="h1">Header</h1>
ID<input id="id" type="text" name="id"><br>
<button onclick="c1();">Click1</button>
```

