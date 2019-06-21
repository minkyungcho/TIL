## jQuery

<https://www.w3schools.com/jquery/default.asp>

모든 브라우저는 자바스크립트 lib를 가지고 있다.

브라우저에 html을 가져다 놓고 html에 있는 javascript를 ...가 실행해줌.

html 실행 전에 jquery lib을 실행시킴 (jvm처럼)

기존 브라우저에 없는 jquery 기능 동작 : 브라우저 위에 lib 올리고 실행

ㅓjqery가 브라우저에 없기때문에 네트워크에서 받아와서 씀



> ``` javascript
> <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
> ```
>
> ### jQuery import

### selector

h1이 여러개 있으면 모두한테 적용

``` javascript
<script>
/* 문서가 준비가 되면 함수가 실행된다  */
$(document).ready(function(
	$('h1').css('color','red');
){});
</script>
```

n번째, first, last

``` javascript
$(document).ready(function(){
	$('h1:nth-child(2n)').css('color','red');
});
```

.eq()

```javascript
$(document).ready(function(){
	$('h1').eq(0).css('color','blue');
});
```

not : 나빼고 다

```javascript
$(document).ready(function(){ 
	$('h1:not(h1:eq(3))').css('color','blue');
});
```





메서드 체이닝(method chain) : 여러개의 img를 붙일수 있음



> jquery는 seletor와 함수(css함수 포함)로 구성되어 있음. 

### DOM 변경

- 요소의 내용을 가져오거나 변경할 수 있다. text(), html()
- 요소의 속성을 가져오거나 변경 할 수 있다. attr()
- 요소의 스타일 속성을 가져오거나 변경할 수 있다. css()
- 요소를 추가하거나 삭제할 수 있다. append(), remove()

