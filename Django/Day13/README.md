# Day13
> 19.11.22 금

### 어제까지
- insta
    - 글쓰기, 댓글달기, 수정하기, 삭제하기
- Image
    - 이미지 업로드(리사이징, 썸네일 만들기), 이미지 여러개 업로드

### today todo
- 기본 JS + jQuery
    - why? 
        - 페이지를 다이나믹하게 만들기 위해서 사용
        - 클라이언트 단에서 데이터(상태)를 관리
        - 요소 찾기 -> 해당 요소에 이벤트 먹이기 -> 이벤트 발생 -> 변화 생김(색상 변화, 나타났다 사라지기 등)
        - 일부 부분에서 발생한 변화를 서버에 저장, 수정, 삭제(ajax)
- 월요일에는 JS + auth(로그인) 할 예정


## CSS selector


~~~ javascript
joke.addEventListener('click', function(){
    alert("배고파");
})
~~~


---
### tip
camalcase : `var = pList`, `joke.addEventListener` 
snakecase : `js_test`


사라지게 하기
~~~ javascript
pList2.forEach(function(element){
            element.addEventListener('click', function(){
                if(confirm("이 태그를 삭제하시겠습니까?")){
                    element.setAttribute('style', 'display:none;')
                }
            }
~~~
`addEventListener`와 `eventHandler` 활용하기


---
## jQuery
`$(document).on('ready', function(){})`
`$(function(){})`
- 삭제버튼 달기

