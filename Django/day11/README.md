# Day11
> 19.11.20 수
### todo
- 11/20 ~ 11/22 까지 1개의 프로젝트로 수업 진행
- instagram!
    - 댓글 comment
        - model 모델링
        - url
    - 이미지 업로드
    - 좋아요 기능, 해쉬태그 기능
        - 다대다 관계. database relation(M:N)
---
## Fake instagram
- `django-admin startproject instagram`, `cd instagram`, `python3 manage.py startapp instagram`

~~~ python
# settings.py
INSTALLED_APPS = [
    'article',
    ...
]
LANGUAGE_CODE = 'ko'
TIME_ZONE = 'Asia/Seoul'
USE_TZ = False
~~~
~~~ python
# urls.py
from django.contrib import admin
from django.urls import path, include
urlpatterns = [
    path('admin/', admin.site.urls),
    path('insta/', include('article.urls'))
]
~~~
- article/urls.py 생성
~~~ python
# article/urls.py
from django.urls import path
from . import views as article_views

urlpatterns = [
    path('', article_views.index, name="articles"),
]
~~~
~~~ python
# views.py
def index(request):
    return render(request, 'index.html')
~~~
~~~ html
<!-- base.html -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal">INSTAGRAM</h5>
        <a class="btn btn-outline-primary" href="#">Sign up</a>
    </div>
    {% block content %}
    {% endblock %}
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
    </script>
</body>

</html>
~~~
- 게시글 작성 페이지 만들기
~~~ html
<!-- index.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <div class="card">
        <div class="card-body">
            <textarea class="form-control" rows="5"></textarea>
        </div>
        <div class="card-footer text-right">
            <input type="submit" class="btn btn-success" value="작성하기">
        </div>
    </div>
</div>
{% endblock %}
~~~
- model
~~~ python 
# models.py
from django.db import models

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

~~~
- `python3 manage.py makemigrations`, `python3 manage.py migrate`
~~~ python
# views.py
from django.shortcuts import render, redirect
from .models import Article

# Create your views here.
def index(request):
    if request.method == "POST":
        article = Article()
        article.contents = request.POST['contents']
        article.save()
        return redirect('articles')
    else:
        return render(request, 'index.html')
~~~
- 글 작성하면 작성한 글 보이는 화면 만들기
~~~ html
<!-- index.html 작성한 글 보이게 하기 위해 카드 추가 -->
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
            <img src="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">{{article.contents}}</p>
            </div>
        </div>
    </div>
    {% endfor %}
</div>
~~~
- 최신 게시글이 맨 위에 뜨도록 하기 `articles = Article.objects.all().order_by("created_at").reverse()`
- 게시글에 댓글 추가하기
> [아이콘 가져와서 꾸미기](https://fontawesome.com/) \
`<i class="fas fa-comment-dots"></i>` 이런식으로 적용 
~~~ html
<!-- index.html -->
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
            <img src="" class="card-img-top">
            <div class="card-body" style="min-height: 9rem;">
                <p class="card-text">{{article.contents}}</p>
            </div>
            <ul class="list-group list-group-flush">
                    <li class="list-group-item"><i class="fas fa-comment-dots"></i>  댓글 1 <a class="btn btn-warning"><i class="far fa-edit"></i></a> <a class="btn btn-danger"><i class="far fa-trash-alt"></i></a></li>
                    <li class="list-group-item"><i class="fas fa-comment-dots"></i>  댓글 2</li>
                    <li class90 ="list-group-item"><i class="fas fa-comment-dots"></i>  댓글 3</li>
                  </ul>
        </div>
    </div>
    {% endfor %}
</div>
~~~
- 댓글 model 추가하기
~~~ python
# models.py
class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE) # '_id'가 자동으로 붙어서 DB에는 article_id라고 생성되고 사용해야함.
~~~
- 댓글 쓸수 있는 입력창 만들기
~~~ html
<!-- index.html -->
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
            <img src="" class="card-img-top">
            <div class="card-body" style="min-height: 9rem;">
                <p class="card-text">{{article.contents}}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <form>
                        <div class="row">
                            <div class="col-9 ">
                                <input type="text" class="form-control">
                            </div>
                            <div class="col-3 text-right">
                                <input type="submit" class="btn btn-info" value="쓰기">
                            </div>
                        </div>
                    </form>
                </li>
                <li class="list-group-item"><i class="fas fa-comment-dots"></i> 댓글 1
                    <sapn class="float-right">
                        <a class="btn btn-warning"><i class="far fa-edit"></i></a>
                        <a class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
                    </sapn>
                </li>
                <li class="list-group-item"><i class="fas fa-comment-dots"></i> 댓글 2
                </li>
                <li class="list-group-item"><i class="fas fa-comment-dots"></i> 댓글 3
                </li>
            </ul>
        </div>
    </div>
    {% endfor %}
</div>
~~~
- 댓글 저장
~~~ python
# article/urls.py
path('comments/', article_views.comments, name="comments"),
~~~
~~~ python
# views.py
def comments(request):
    if request.method == "POST":
        contents = request.POST['contents']
        article_id = request.POST['article_id']
        comment = Comment()
        comment.contents = contents
        comment.article_id = article_id
        comment.save()
        return redirect('articles')
~~~
~~~ html
<!-- index.html -->

~~~
- 댓글 삭제
~~~ python
# article/urls.py
path('comments/<int:comment_id>/delete', article_views.delete_comment, name="delete_comment")
~~~
~~~ python
# views.py
def delete_comment(request, comment_id):
    comment = Comment.objects.get(id=comment_id)
    comment.delete()
    return redirect('articles')
~~~
~~~ html
<!-- index.html -->
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
            <img src="" class="card-img-top">
            <div class="card-body" style="min-height: 9rem;">
                <p class="card-text">{{article.contents}}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <form action="{% url 'comments' %}", method="POST">
                        <input type="hidden" value="{{csrf_token}}" name="csrfmiddlewaretoken">
                        <input type="hidden" value="{{article.id}}" name="article_id">
                        <div class="row">
                            <div class="col-9 ">
                                <input type="text" class="form-control" name="contents">
                            </div>
                            <div class="col-3 text-right">
                                <input type="submit" class="btn btn-info" value="쓰기">
                            </div>
                        </div>
                    </form>
                </li>
                {% for comment in article.comment_set.all %}
                <li class="list-group-item"><i class="fas fa-comment-dots"></i>{{comment.contents}}
                    <sapn class="float-right">
                        <a class="btn btn-warning"><i class="far fa-edit"></i></a>
                        <a href="{% url 'delete_comment' comment.id %}" class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
                    </sapn>
                </li>
                {% endfor %}
            </ul>
        </div>
    </div>
    {% endfor %}
</div>
~~~
- 댓글 수정
~~~ python
# article/urls.py
path('comments/<int:comment_id>/edit', article_views.edit_comment, name="edit_comment"),
~~~
~~~ html
<!-- index.html -->
<a href="{% url 'edit_comment' comment.id %}" class="btn btn-warning"><i class="far fa-edit"></i></a>
~~~
~~~ html 
<!-- edit.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <div class="card">
        <form action="{% url 'edit_comment' comment.id %}" method="POST">
            <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
            <div class="card-body">
                <textarea class="form-control" rows="5" name="contents">{{comment.contents}}</textarea>
            </div>
            <div class="card-footer text-right">
                <input type="submit" class="btn btn-success" value="수정하기">
            </div>
        </form>
    </div>
</div>
{% endblock %}
~~~
~~~ python
# views.py
def edit_comment(request, comment_id):
    comment = Comment.objects.get(id=comment_id)
    if request.method == "POST":
        comment.contents = request.POST['contents']
        comment.save()
        return redirect('articles')
    else:
        context = {
            'comment': comment
        }
        return render(request, 'comment/edit.html', context)
~~~

- 게시글 수정 & 삭제하기
- `templates`에 폴더 `comment`(댓글용), `article`(게시글용) 폴더 두개 만들어서 각각 `edit.html` 파일 담아준다.
~~~ python
# article/urls.py
path('articles/<int:article_id>/delete', article_views.delete, name="delete"),
path('articles/<int:article_id>/edit', article_views.edit, name="edit"),
~~~
~~~ python
# views.py
def delete(request, article_id):
    article = Article.objects.get(id=article_id)
    article.delete()
    return redirect('articles')

def edit(request, article_id):
    article = Article.objects.get(id=article_id)
    if request.method == "POST":
        article.contents = request.POST['contents']
        article.save()
        return redirect('articles')
    else:
        context = {
            'article': article
        }
        return render(request, 'article/edit.html', context)
~~~
~~~ html
<!-- comment/edit.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <div class="card">
        <form action="{% url 'edit_comment' comment.id %}" method="POST">
            <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
            <div class="card-body">
                <textarea class="form-control" rows="5" name="contents">{{comment.contents}}</textarea>
            </div>
            <div class="card-footer text-right">
                <input type="submit" class="btn btn-success" value="수정하기">
            </div>
        </form>
    </div>
</div>
{% endblock %}
~~~
~~~ html
<!-- article/edit.html -->
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <div class="card">
        <form action="{% url 'edit_article' article.id %}" method="POST">
            <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
            <div class="card-body">
                <textarea class="form-control" rows="5" name="contents">{{article.contents}}</textarea>
            </div>
            <div class="card-footer text-right">
                <input type="submit" class="btn btn-success" value="수정하기">
            </div>
        </form>
    </div>
</div>
{% endblock %}
~~~

- `{% for comment in article.comment_set.all %}` -> `{% for comment in article.comments %}`
~~~ python
# models.py에 함수 추가
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    def comments(self):
        return Comment.objects.filter(article_id=self.id)
~~~

---
### tip
비동기적 vs 동기적
- 동기적 : 서버에 요청이 갔다가 응답이 돌아올때까지 기다려야함. 그 이후에 다른일 시작할 수 있다.
- 비동기적 : 서버에 특정 요청을 보내고 종료알림만 받겠다(callback).