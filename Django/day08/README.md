# Day8
> 19.11.15 월
### Yesterday review
- Naver API 사용해보기
    - 외부 사이트에 Request를 보낼때, POST 방식으로 요청하는 방법 학습
    - Request Body에 단순히 파라미터명과 파라미터값으로 이루어진 쌍이 아니라 json형식으로 파라미터를 보내는 방법
- ORM 기초
    - Create, Read를 Django Shell에서 실행
    - ORM(Object Realationship Mapping)이 무엇인지? 왜 사용해야하는지?
---
### today todo
- 기본 게시판 만들기
    - URL 분리하기
        - `urls.py`에 우리가 접속할 모든 주소를 명시했는데, CRUD를 하다보면 만들어야 할 페이지가 점점 많아서 구분하기가 어려워진다. 때문에 각 역할을 하는 App마다 `urls.py` 파일을 생성할 예정         
    - 공용으로 사용할 수 있는(공유할 수 있는) HTML 파일 만들기
        - 반복되는 HTML 구조를 계속해서 새로 만들지 말고, 공통되는 부분은 하나의 파일로 묶어서 반복해서 사용함.
    - CRUD 계속 반복
        - **C** - New Create
        - **R** - Index Show
        - **U** - Edit Update
        - **D** - Destroy

---
#### bootstrap 활용
[bootstrap.com](https://getbootstrap.com/)
- [Quick start](https://getbootstrap.com/docs/4.3/getting-started/introduction/)
    - CSS는 head 부분에 
    - js는 body 가장아래부분에
    - `form`, `button` 등 검색을 통해 마음에 드는 타입으로 가져다 쓴다.
- [example](https://getbootstrap.com/docs/4.3/examples/)
    - menu bar 가져오기
---
## 기본 게시판 만드기 
1. project, app 생성 및 설정 세팅
    - `django-admin startproject crud_test`
    - `cd crud_test`
    - `python3 manage.py startapp boards`
    - 
2. db 
    - `python3 manage.py makemigrations`
    - `python3 manage.py migrate`
3. CRUD
### save, create
~~~ python
# urls.py
from django.contrib import admin
from django.urls import path
from boards import views as boards_views
urlpatterns = [
    path('admin/', admin.site.urls),
    # 게시판의 메인페이지, 전체 리스트 페이지
    path('boards/', boards_views.index),
    # 게시판의 새 글을 작성하는 페이지
    path('boards/new/', boards_views.new),
    path('boards/create/', boards_views.create),
    # 게시판의 글 하나를 상세히 보는 페이지
    path('boards/<id>/', boards_views.show)
]
~~~
**GET** 방식
~~~ python
#  views.py
from django.shortcuts import render, redirect
from .models import Board
# Create your views here.
# 게시글 작성자
def index(request):
    boards = Board.objects.all()
    return render(request, 'index.html')

def new(request):
    return render(request, 'new.html')

def create(request):
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']
    new_board = Board()
    new_board.title = title
    new_board.contents = contents
    new_board.creator = creator
    new_board.save()
    return redirect(f'/boards/{new_board.id}')

def show(request, id):
    board = Board.objects.get(id=int(id))
    context = {
        'board': board
    }
    return render(request, 'show.html', context)
~~~
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
{% block content %}
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">작성지</th>
            <th scope="col">작성일자</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
        </tr>
    </tbody>
</table>
<a href="/boards/new"><button type="button" class="btn btn-info">새글쓰기</button></a>
{% endblock %}
~~~
- `board = filter(조건)`으로 활용
- `primary key`는 `board = Board.objects.get(id=int(id))` 형식으로 찾을수 있다.
> **beautify** 활용하기 \
> vscode - beautify 설치 \
> `command + P` -> `>beautify` -> `enter` 로 적용

#### app을 추가할 때, URL 관리를 위해 폴더를 나눈다.
- `urls.py`에 있는 board 관련 path 삭제한다.
- `path('boards/', include('boards.urls'))` 추가
- `from django.urls import path, include` include import 하기
- `crud_test/boards/urls.py` 생성 후 위에서 삭제한 board 관련 path 붙여넣기
~~~ python
# crud_test/boards/urls.py
from django.urls import path, include 
from . import views as boards_views
urlpatterns = [
    # 게시판의 메인페이지, 전체 리스트 페이지
    path('', boards_views.index),
    # 게시판의 새 글을 작성하는 페이지
    path('new/', boards_views.new),
    path('create/', boards_views.create),
    # 게시판의 글 하나를 상세히 보는 페이지
    path('<id>/', boards_views.show)   
]
~~~
### read, indes, show
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
{% block content %}
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">작성지</th>
            <th scope="col">작성일자</th>
        </tr>
    </thead>
    <tbody>
        {% for board in boards %}
        <tr onclick="location.href='/boards/{{board.id}}'">
            <th scope="row">{{board.id}}</th>
            <td>{{board.title}}</td>
            <td>{{board.creator}}</td>
            <td>2019.11.15</td>
        </tr>
        {% endfor %}
    </tbody>
</table>
<a href="/boards/new"><button type="button" class="btn btn-info ">새글쓰기</button></a>
{% endblock %}
~~~

- `save`로 저장하는 방법
~~~ python
new_board = Board()
new_board.title = title
new_board.contents = contents
new_board.creator = creator
~~~
- `create`로 새로 생성하는 방법
~~~ python
new_board = Board(title=title, contents=contents, creater=creator)
new_board.save()
~~~
- `create` 한줄로 더 간단히
~~~ python
new_board = Board.objects.create(title=title, contents=contents, creater=creator)
~~~
~~~ python
# views.py
def create(request):
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']
    new_board = Board.objects.create(title=title, contents=contents, creator=creator)    
    return redirect(f'/boards/{new_board.id}')

def show(request, id):
    board = Board.objects.get(id=id)
    context = {
        'board': board
    }
    return render(request, 'show.html', context)
~~~

### edit, update
~~~ python
# /board/urls.py 에 추가
path('<int:id>/edit', boards_views.edit)
path('<int:id>/update', boards_views.update)
~~~
~~~ python
# views.py 에 추가
def edit(request, id):
    # 원래 있던 내용이 들어있는 Form
    board = Board.objects.get(id=id)
    context = {
        'board': board
    }
    return render(request, 'edit.html', context)

def update(request, id):
    # 실제로 update가 일어나는 곳
    board = Board.objects.get(id=id)
    title = request.GET['title']
    contents = request.GET['contents']

    board.title = title
    board.contents = contents
    board.save()

    context = {
        'board': board
    }
    return redirect(f'/boards/{board.id}')
~~~
~~~ html
<!-- show.html -->
{% extends 'base.html' %}
{% block content %}
<h2>제목 : {{board.title}}</h2>
<hr/>
<p>{{board.contents}}</p>
<p class="font-italic text-center">created by {{board.creator}}</p>
<div class="mt-3 text-center">
    <a href="/boards/{{board.id}}/edit" class="btn btn-danger">수정하기</a>
    <a href="/boards/{{board.id}}/delete" class="btn btn-warning text-white">삭제하기</a>
</div>
{% endblock %}
~~~
~~~ html
<!-- edit.html -->
{% extends 'base.html' %}
{% block content %}
<form action="/boards/{{board.id}}/update">
    <div class="form-group">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" name="title" value="{{board.title}}">
    </div>
    <div class="form-group">
        <label for="contents">내용</label>
        <textarea class="form-control" id="contents" rows="3" name="contents">{{board.contents}}</textarea>
    </div>

    <div class="form-group">
        <label for="creator">작성자</label>
        <input type="text" class="form-control" id="creator" name="creator" value="{{board.creator}}" readonly>
    </div>

    <div class="form-group text-center">
        <input type="submit" value="작성하기" class="btn btn-info">
    </div>
</form>
{% endblock %}
~~~

### destroy
~~~ python
# views.py
def delete(request, id):
    board = Board.objects.get(id=id)
    board.delete()
    return redirect('/boards')
~~~
~~~ python
# urls.py
path('<int:id>/delete', boards_views.delete),
~~~
---

---
### tip
회사 내 업무 관리 프로그램
- 트렐로
- Jira
- Notion
    - 나만의 게시판!
    - 학교계정 활용해보자!