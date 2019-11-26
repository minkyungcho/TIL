# Day15
> 19.11.26 화

### 지난 주 review
#### JavaScript
- 속도의 문제로 자바스크립트 파일 제일 아래에 선언함
- html은 한줄씩 순서대로 읽는다.
- stylesheet와 돔트리가 합쳐져서 이쁜 틀을 구성하게 됨.
- 그 후 자바스크립트에서 이벤트를 달아준다.
- `console.log`나 `console.dir`로 상세 내용을 찍어본다.
- `$('#~~').on('click', function(){ ... })` 구조에 익숙해지자!

### today to do
- JS, jQuery
    - 요소를 찾아서
    - 요소에 이벤트가 발생하는 것을 포착해서(Event Listener)
    - 이벤트가 발생했을 때 어떤 로직을 실행할 지 결정(Event Handler)

- AJAX (Asynchronous Javascript And XML)
    - 비동기JS & XML 
    - Callback
    - ``` html
        <script>
        $(function(){
            $.ajax({
                url: '어느 주소로 요청을 보낼지',
                method: '어떤 request method로 보낼지',
                data: {
                    key: '어떤 형태로 보낼지'
                },
                success: function(data) {
                    '요청이 성공적으로 완료됐을 때'
                },
                error: function(data) {
                    '요청이 정상적으로 완료되지 않았을 때'
                }
            })
        })
        </script>
    
- Auth(User)

---
## jQuery

- `models.py`에 class 추가
~~~ python
# models.py
class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True)
~~~
- DB에 적용 `python3 manage.py makemigrations`, `python3 manage.py migrate`
- `urls.py`
~~~ python
path('jq-test/boards/', article_views.submit_boards, name="submit_boards"),
~~~
- `views.py`
~~~ python
def submit_boards(request):
    return ''
~~~

- `<form class="mb-5 mt-5" id="boardForm">`
- 댓글쓰기 버튼 속성 바꾸기 `<button type="submit" ...>`
- 댓글 쓰는 곳 input id 바꾸기 `<input type="text" class="form-control" id="boardInput">`
~~~ javascript
$(function () {
    // 댓글 쓰고 DB에 등록
    // boardForm이 제출되었을 때
    $('#boardForm').on('submit', function(event){ // e : event
        // 기존 이벤트를 삭제 해주어야 한다
        event.preventDefault();
        // 실제 DB에 등록 될 수 있게끔 ajax 요청을 만들어줌
        var board = $('#boardInput').val();
        $.ajax({
            url: '{% url "submit_boards" %}', // ''로 열었으면 "", ""로 열었으면 ''
            method: 'POST',
            data: {
                // key: value
                board: board
            },
            success: function(data){
                alert("성공")
            },
            error: function(data){
                alert("실패")
            }
        })
    })
})
~~~
- `<input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}" id="csrftoken">`로 csrf token 넘겨주는 대신 jQuery에서 data 로 넘겨줌
~~~ javascript
data: {
    // key: value
    board: board, 
    csrfmiddlewaretoken: '{{csrf_token}}'
},
~~~

~~~ javascript
$(function () {
    // 댓글 쓰고 DB에 등록
    // boardForm이 제출되었을 때
    $('#boardForm').on('submit', function(event){ // e : event
        // 기존 이벤트를 삭제 해주어야 한다
        event.preventDefault();
        // 실제 DB에 등록 될 수 있게끔 ajax 요청을 만들어줌
        var board = $('#boardInput').val();
        $('#boardInput').val('');
        $.ajax({
            url: '{% url "submit_boards" %}', // ''로 열었으면 "", ""로 열었으면 ''
            method: 'POST',
            data: {
                // key: value
                board: board, 
                csrfmiddlewaretoken: '{{csrf_token}}'
            },
            success: function(data){
                alert("성공")
                $('.list-group').prepend(data); // html data(댓글, empty.html)를 댓글 그룹에 붙여넣기
            },
            error: function(data){
                alert("실패")
            }
        })
    })
})
~~~
~~~ python
# views.py
def submit_boards(request):
    if request.method == "POST":
        contents = request.POST["board"]
        board = Board.objects.create(contents=contents)
        context = {
            'board': board
        }
        return render(request, 'empty.html', context)
~~~
~~~ html
<!-- empty.html -->
<li class="list-group-item" id="comment-1">
    {{board.contents}}
    <span class="float-right">
        <button type="button" class="btn btn-warning">수정</button>
        <button type="button" class="btn btn-danger delete-comment" value="1">삭제</button>
    </span>
</li>
~~~
~~~ html
<!-- jq-test.html -->
<form class="mb-5 mt-5" id="boardForm">
        <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}" id="csrftoken">
        댓글쓰기
        <input type="text" class="form-control" id="boardInput">
        <button type="submit" class="btn btn-primary" id="submitComment">댓글쓰기</button>
    </form>
    <div class="commnetList">
        <ul class="list-group">
            {% for board in boards %}
            <li class="list-group-item" id="comment-1">
                {{ board.contents }}
                <span class="float-right">
                    <button type="button" class="btn btn-warning">수정</button>
                    <button type="button" class="btn btn-danger delete-comment" value="1">삭제</button>
                </span>
            </li>
            {% endfor %}
        </ul>
    </div>
~~~

이벤트 발생
1. JS로 HTML 요소를 추가한 다음 ajax로 서버에 요청을 보내 
    - 실패했을 경우 삭제를 다시 해줘야함.
2. ajax로 서버에 요청을 보내 실제 DB에 반영되고 나면 
    - response가 올때까지 댓글이 달리지 않는다.

- 삭제
- 삭제 버튼의 class 명 바꿔주기 `<button type="button" class="btn btn-danger deleteBoard" data-value="{{board.id}}">삭제</button>`
- 어떤 댓글 삭제했는지 알기 위한 댓글 id를 변경 `<li class="list-group-item" id="board-{{board.id}}">`
- `urls.py`에서 삭제를 위한 경로 생성해주기. 
~~~ python
path('jq-test/boards/delete', article_views.delete_boards, name="delete_boards"),
~~~
- `views.py`에서 연결 완성
~~~ python
def delete_boards(request):
    return ''
~~~
- 완성된 코드
~~~ html
<!-- empty.html -->
<li class="list-group-item" id="board-{{board.id}}">
    {{board.contents}}
    <span class="float-right">
        <button type="button" class="btn btn-warning">수정</button>
        <button type="button" class="btn btn-danger deleteBoard" data-value="{{board.id}}">삭제</button>
    </span>
</li>
~~~
~~~ html
<!-- jq_test.html -->
<form class="mb-5 mt-5" id="boardForm">
        <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}" id="csrftoken">
        댓글쓰기
        <input type="text" class="form-control" id="boardInput">
        <button type="submit" class="btn btn-primary" id="submitComment">댓글쓰기</button>
    </form>
    <div class="commnetList">
        <ul class="list-group">
            {% for board in boards %}
            <li class="list-group-item" id="board-{{board.id}}">
                {{ board.contents }}
                <span class="float-right">
                    <button type="button" class="btn btn-warning">수정</button>
                    <button type="button" class="btn btn-danger deleteBoard" data-value="{{board.id}}">삭제</button>
                </span>
            </li>
            {% endfor %}
        </ul>
    </div>
~~~
~~~ javascript
$(function () {
    // 삭제 버튼 눌렀을 때 실제로 DB에서 삭제시키기
    // 삭제 버튼을 눌렀을 때
    $(document).on('click', '.deleteBoard', function(){
        // 해당 줄(list)을 하나 삭제해야함
        var id = $(this).data('value'); // data-value="{{board.id}}"
        // console.log(id); // 댓글 클릭할때마다 댓글의 id가 출력되는것 확인 1,2,3,...
        // 실제로 DB에서 삭제하기.
        $.ajax({
            url: '{% url "delete_boards" %}',
            method: 'POST',
            data: {
                board_id: id,
                csrfmiddlewaretoken: '{{csrf_token}}' // post로 보내주기 때문에 필요해
            },
            success: function(data) {
                alert("삭제 성공");
                $('#board-' + data.board_id).hide(); 
            },
            error: function(data) {
                alert("삭제 실패");
            }
        })
        // $('#board-' + id).hide(); // 눈에만 안보이게 하는것. 숨긴다. 새로고침하면 다시 생김.
    })
    // 댓글 등록
})
~~~
~~~ python
# views.py
from django.http.response import HttpResponse
import json
...
def delete_boards(request):
    if request.method == "POST":
        id = request.POST["board_id"]
        board = Board.objects.get(id=id)
        board.delete()
        context = {
            'board_id':id
        }
        # 삭제를 하면 network 탭에 delete가 오고 preview로 보면 json형식으로 온것을 확인할 수 있음.
        return HttpResponse(json.dumps(context), content_type="appication/json")
~~~



---
### tip
많은 에러들을 만나봐야 한다
