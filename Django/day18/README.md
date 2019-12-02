# Day18
>19.11.29 금
### 어제 review
- 회원가입 -> 유저에게 접근할 수 있는 범위를 지정해줌
- M:N Field
    - ManyToManyField 가 자동으로 join table 생성해줌
    - 어느쪽에서 관계를 다느냐에 따라서 앞에 붙는 이름과 뒤에 붙는 related_name을 잘 줘야함.
        - 해당 해쉬태그가 있는 게시글들을 불러오기 위해서 : tags. 
        - 게시글에 있는 해시태그들 가져오기 : article.tags

---
## Pusher
- ajax로 요청을 보내고 응답으로 오는 내용들이 success 코드로 가지 않고 pusher가 listening하는 곳으로 감.
[Pusher API](https://pusher.com/)
- 내 서버에서 pusher 서버로 동시에 보낼수 있는 정보가 20만개이다.
- create new channel app 
    - `chatproject` 
    - front : `jQuery`
    - end : `django`
- install pusher `python3 -mpip install pusher`
- add script `<script src="https://js.pusher.com/5.0/pusher.min.js"></script>`
- add import pusher package
~~~ python
import pusher
pusher_client = pusher.Pusher(
  app_id='908830',
  key='3996cf098f3e8b647f56',
  secret='INSERT SECRET KEY',
  cluster='ap3',
  ssl=True
)
pusher_client.trigger('my-channel', 'my-event', {'message': 'hello world'})
~~~

#### 채팅 앱 만들기
`django-admin startproject chatproject`
`cd chatproject/`
`python3 manage.py startapp boards`
- `settings.py` 
~~~ python
INSTALLED_APPS = [
    'boards',
    ...
]
LANGUAGE_CODE = 'ko'
TIME_ZONE = 'Asia/Seoul'
USE_TZ = False
~~~
- `chatapp/urls.py`
~~~ python
from django.contrib import admin
from django.urls import path, include
urlpatterns = [
    path('admin/', admin.site.urls),
    path('boards/', include('boards.urls')),
]
~~~
- `boards/urls.py`
~~~ python
from django.urls import path, include
from . import views
app_name = "boards"
urlpatterns = [
    path('', views.index, name="index"),
    path('<int:room_id>/', views.show, name="room"),
]
~~~
- `views.py`
~~~ python
from django.shortcuts import render
# Create your views here.
def index(request):
    return render(request, 'index.html')
def show(request, room_id):
    return render(request, 'show.html')
~~~
- `models.py`
~~~ python
from django.db import models
from django.conf import settings
from django.contrib.auth.models import User

# Create your models here.
class Room(models.Model):
    title = models.CharField(max_length=32)
    code = models.CharField(max_length=16, unique=True)
    max_connection = models.IntegerField()
    master = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

class Message(models.Model):
    contents = models.TextField()
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
~~~
- `boards/templates` `index.html` `show.html` `base.html`
~~~html
<!-- base.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Company name</a>
        <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
        <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="#">Sign out</a>
            </li>
        </ul>
    </nav>

    {% block content %}
    {% endblock %}

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>

    {% block script %}
    {% endblock %}
</body>
</html>
~~~
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
<!-- 실제 표시 내용 -->
{% block content %}
<div class="container mt-5">
    <h1>채팅</h1>
</div>

<div class="container">
    <div class="row">
        <table class="table table-hover table-dark text-center">
            <thead>
                <tr>
                    <th scope="col" width="5%">#</th>
                    <th scope="col" width="50%">채팅방</th>
                    <th scope="col" width="25%">방장</th>
                    <th scope="col" width="20%">참여인원</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td colspan="2">Larry the Bird</td>
                    <td>@twitter</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- 새 방 만들기 -->
    <div class="row">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            새 채팅방 만들기
        </button>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
{% endblock %}

{% block script %}
{% endblock %}
~~~
~~~ html
<!-- show.html -->
{% extends 'base.html' %}
<!-- 실제 표시 내용 -->
{% block content %}
<div class="container mt-5">
    <h1>Show</h1>
</div>
{% endblock %}

<!-- 자바 스크립트 -->
{% block script %}
{% endblock %}
~~~




---
### tip
- Toaster
- git에 올라가면 안되는 API key
Q. API만 덕지덕지 붙여서 만든걸 프로젝트라고 할수 있어요?
A. 내가 최소한의 시간으로 최대한의 효율을 내야하는데 그 시간으로 실제로 구현하는것보다 소비자들을 위한 부분을 생각했다.