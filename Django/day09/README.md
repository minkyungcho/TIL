#  Day9
> 19.11.18 월
#### VSCode 환경세팅
- `python3 -mpip install pylint`
- `python3 -mpip install pylint-django`
- `python3`로 alias 바꿔놓기

### today todo
- url namespace 설정하기
- RESTful한 API 설계하기
- Django Admin 설정하기
- created_at, updated_at 필드 설정하기
    - DateTimeField
## articles app
- `django-admin startproject crudtest`
- `cd crudtest`
- `python3 manage.py startapp articles`

~~~ python
# settings.py
INSTALLED_APPS = [
    'articles',
    ...
]
LANGUAGE_CODE = 'ko'
TIME_ZONE = 'Asia/Seoul'
USE_TZ = False
~~~
- `aricles/urls.py` 생성
~~~ python
# crudtest/urls.py
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('articles/', include('articles.urls'))
]
~~~
~~~ python
# articles/urls.py
from django.contrib import admin
from django.urls import path, include

from . import views

urlpatterns = [
    # CRUD
    # C -> new, create
    # R -> index, show
    # U -> edit, update
    # D -> destroy
    path('', views.index),
    path('<int:id>/', views.show),
    path('new/', views.new),
    path('create/', views.create),
    path('<int:id>/edit', views.edit),
    path('<int:id>/update', views.update),
    path('<int:id>/destroy', views.destroy),
]
~~~
- `views.py`에 함수 틀 잡아두기
~~~ python
# views.py
from django.shortcuts import render
# Create your views here.
def index(request):    
    return ''
def show(request):   
    return ''
def new(request):   
    return ''
def create(request):   
    return ''
def edit(request):  
    return ''
def update(request):  
    return ''
def delete(request):
    return ''
~~~
- `articles/templates` 폴더 생성하고 html 파일 생성. `base.html`, `index.html`, `new.html`, `show.html`, `edit.html` 
~~~ html
<!-- base.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    {% block content %}
    {% endblock %}
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
~~~
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
{% block content %}
<h1>INDEX</h1>
{% endblock %}
~~~
~~~ html
<!-- new.html -->
{% extends 'base.html' %}
{% block content %}
<h1>NEW</h1>
{% endblock %}
~~~
~~~ html
<!-- show.html -->
{% extends 'base.html' %}
{% block content %}
<h1>SHOW</h1>
{% endblock %}
~~~
~~~ html
<!-- edit.html -->
{% extends 'base.html' %}
{% block content %}
<h1>EDIT</h1>
{% endblock %}
~~~
- `views.py`에서 html파일과 연결시켜놓기
~~~  python
# views.py
from django.shortcuts import render, redirect
# Create your views here.
def index(request):
    # Article Model에 있는 모든 Article을 불러옴
    return render(request, 'index.html')    
def show(request, id):
    # Article Model에 있는 특정 id를 가진 하나의 Article 불러옴
    return render(request, 'show.html')
def new(request):
    # 불로올 Article 없음
    return render(request, 'new.html')
def create(request):
    # Article 새로 생성
    return redirect('/articles/')
def edit(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    return render(request, 'edit.html')
def update(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    return redirect('/articles/')
def delete(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    return redirect('/articles/')
~~~
- `articles/models.py`
~~~ python
# articles/models.py
from django.db import models
# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=16)
    contents = models.TextField()
    creator = models.CharField(max_length=8)
~~~
- `python3 manage.py makemigrations`, `python3 manage.py migrate`
- `python3 manage.py showmigrations` 통해 확인
- CRUD 기능 완성하기
~~~ python
# articles/views.py
from django.shortcuts import render, redirect
from .models import Article
# Create your views here.
def index(request):
    # Article Model에 있는 모든 Article을 불러옴
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'index.html', context)   
def show(request, id):
    # Article Model에 있는 특정 id를 가진 하나의 Article 불러옴
    article = Article.objects.get(id=id)
    context = {
        'article': article
    }
    return render(request, 'show.html', context)
def new(request):
    # 불로올 Article 없음

    return render(request, 'new.html')
def create(request):
    # Article 새로 생성
    title = request.GET['title']
    contents = request.GET['contents'] 
    creator = request.GET['creator']
    # 방법1
    article = Article.objects.create(title=title, contents=contents, creator=creator)
    # 방법2
    article = Article()
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()
    return redirect(f'/articles/{article.id}')
def edit(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    context = {
        'article': article
    }
    return render(request, 'edit.html', context)
def update(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    title = request.GET['title']
    contents = request.GET['contents'] 
    creator = request.GET['creator']
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()
    return redirect(f'/articles/{article.id}')
def delete(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    article.delete()
    return redirect('/articles/')
~~~
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Creator</th>
                <th scope="col">Created At</th>
            </tr>
        </thead>
        <tbody>
            {% for article in articles %}
            <tr>
                <th scope="row">{{article.id}}</th>
                <td><a href="/articles/{{article.id}}">{{article.title}}</a></td>
                <td>{{article.creator}}</td>
                <td>date</td>
            </tr>
            {% endfor %}
        </tbody>
    </table>
    <a href="/articles/new/" class="btn btn-info">새글쓰기</a>
</div>
{% endblock %}
~~~
~~~ html
<!-- show.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <h1>{{article.title}}</h1>
    </hr>
    <p>{{article.contents}}</p>
    <p class="text-right">created by {{article.creator}}</p>
    <a href="/articles/{{article.id}}/edit" class="btn btn-primary">수정하기</a>
    <a href="/articles/{{article.id}}/delete" class="btn btn-danger">삭제하기</a>
</div>
{% endblock %}
~~~
~~~ html
<!-- new.html -->
{% extends 'base.html' %}
{% block content %}
<div class="contianer">
    <form action="/articles/create/">
        <div class="form-group">
            <label for="title">Title</label>
            <input name="title" type="text" class="form-control" id="title" placeholder="제목을 입력하세요.">
        </div>
        <div class="form-group">
            <label for="contents">Contents</label>
            <textarea name="contents" rows="5" class="form-control" id="contents"></textarea>
        </div>
        <div class="form-group">
            <label for="creator">Creator</label>
            <input name="creator" type="text" class="form-control" id="creator" placeholder="작성자를 입력하세요.">
        </div>
        <button type="submit" class="btn btn-primary">작성하기</button>
    </form>
</div>
{% endblock %}
~~~
~~~ html
<!-- edit.html -->
{% extends 'base.html' %}
{% block content %}
<div class="contianer">
        <form action="/articles/{{article.id}}/update/">
            <div class="form-group">
                <label for="title">Title</label>
                <input name="title" type="text" class="form-control" id="title" value="{{article.title}}" placeholder="제목을 입력하세요.">
            </div>
            <div class="form-group">
                <label for="contents">Contents</label>
                <textarea name="contents" rows="5" class="form-control" id="contents" >{{article.contents}}</textarea>
            </div>
            <div class="form-group">
                <label for="creator">Creator</label>
                <input name="creator" type="text" class="form-control" id="creator" value="{{article.creator}}" readonly placeholder="작성자를 입력하세요.">
            </div>
            <button type="submit" class="btn btn-info">수정하기</button>
        </form>   
    </div>
{% endblock %}
~~~

## URL nanmespace
- 각각의 url에 별명을 지어줘서 html 파일에서 사용하는 링크를 추가적으로 바꾸지 않고, `urls.py` 에서만 수정하면 html 파일에서도 링크 수정이 반영되게끔 함
- `articles`라는 namespace 생성.
~~~ python
# articles/urls.py
app_name = 'articles'
~~~
- 별명 설정
~~~ python
# articles/urls.py
from django.contrib import admin
from django.urls import path, include
from . import views
urlpatterns = [
    # CRUD
    # C -> new, create
    # R -> index, show
    # U -> edit, update
    # D -> destroy
    path('', views.index, name="index"),
    path('<int:id>/', views.show, name="show"),
    path('new/', views.new, name="new"),
    path('create/', views.create, name="create"),
    path('<int:id>/edit/', views.edit, name="edit"),
    path('<int:id>/update/', views.update, name="update"),
    path('<int:id>/delete/', views.delete, name="delete"),
]
~~~
- `{% url 이름 파라미터 %}` \
    ex) `{% url 'articles:how' article.id %}`, `{% url 'articles:new' %}`, `{% url 'articles:create' %}`, `{% url 'articles:update' article.id %}`. `{% url 'articles:edit' article.id %}`, `{% url 'articles:delete' article.id %}`
    `'articles:show'`
- `views.py`도 namespace 설정해준다.
~~~ python
# views.py
from django.shortcuts import render, redirect
from .models import Article
# Create your views here.
def index(request):
    # Article Model에 있는 모든 Article을 불러옴
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'index.html', context)
def show(request, id):
    # Article Model에 있는 특정 id를 가진 하나의 Article 불러옴
    article = Article.objects.get(id=id)
    context = {
        'article': article
    }
    return render(request, 'show.html', context)
def new(request):
    # 불로올 Article 없음

    return render(request, 'new.html')
def create(request):
    # Article 새로 생성
    title = request.GET['title']
    contents = request.GET['contents'] 
    creator = request.GET['creator']
    # 방법1
    article = Article.objects.create(title=title, contents=contents, creator=creator)
    # 방법2
    article = Article()
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()
    return redirect('articles:show', article.id)
def edit(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    context = {
        'article': article
    }
    return render(request, 'edit.html', context)
def update(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    title = request.GET['title']
    contents = request.GET['contents'] 
    creator = request.GET['creator']
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()
    # article = Article.objects.create(title=title, contents=contents, creator=creator)
    return redirect('articles:show', article.id)
def delete(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    article.delete()
    return redirect('articles:index')
~~~

## RESTful api
역할|Request-Method|<center>End-point</center>|Views(Function)
:---:|:---:|:---|:---:
Create|GET|/articles/new|new
Create|POST|/articles/create|create
Read|GET|/articles/`<id>`|show
Read|GET|/articles/|index
Update|GET|/articles/`<id>`edit|edit
Update|POST|/articles/`<id>`/update|update
Delete|POST(DELETE)|/articles/`<id>`/delete|delete

> GET : 조회\
POST : DB 에 반영

## created_at, updated_at 필드 설정하기
- DateTimeField
~~~ python
# models.py
created_at = models.DateTimeField(auto_now_add=True, null=True)
updated_at = models.DateTimeField(auto_now=True, null=True)
~~~
- `python3 manage.py makemigrations` & `python3 manage.py migrate`
> `index.html`에 임의로 date라고 써놓은곳 `{{article.created_at}}`로 바꿔준다.\
ERROR
- `index.html`에 임의로 date라고 써놓은곳 `{{article.datetime_to_str}}`로 바꿔준다.
- `models.py`에 함수 추가하기 (`migrate`할 필요는 없음)
~~~ python
def created_by(self):
    return "created by " + self.creator 
def datetime_to_str(self):
    return self.created_at.strftime("%Y-%m-%d")
~~~
~~~ html
<!-- show.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <h1>{{article.title}}</h1>
    </hr>
    <p>{{article.contents}}</p>
    <p class="text-right">{{article.created_by}}</p>
    <a href="{% url 'articles:edit' article.id %}" class="btn btn-primary">수정하기</a>
    <a href="{% url 'articles:delete' article.id %}" class="btn btn-danger">삭제하기</a>
</div>
{% endblock %}
~~~
~~~ html
<!-- index.html -->
<tr>
    <th scope="row">{{article.id}}</th>
    <td><a href="{% url 'articles:show' article.id %}">{{article.title}}</a></td>
    <td>{{article.creator}}</td>
    <td>{{article.datetime_to_str}}</td>
</tr>
~~~
## Django Admin 설정하기
- admin 계정 만들기 : id는 `master`, email은 `master@zz.zz`, pwd는 `abc123` 로 설정
~~~ bash
$ python3 manage.py createsuperuser
사용자 이름 (leave blank to use 'ming'): master
이메일 주소: master@zz.zz  
Password: 
Password (again): 
Superuser created successfully.
~~~
> http://localhost:8000/admin/
- `articles/admin.py`
~~~ python
# articles/admin.py
from django.contrib import admin
from .models import Article
# Register your models here.
admin.site.register(Article)
~~~
<img src="">
- `models.py`에 함수 추가
~~~ python
# models.py 
def __str__(self):
    return f'[{self.title}] - created by {self.creator}'
~~~
<img src="">