# Day19
> 19.12.2 월

---
### 개발환경 구축
- `python3 -m venv venv`
- `source venv/Scripts/activate` for mac : `source venv/bin/activate`
- `pip list` 로 해당 폴더마다의 설치 목록을 볼 수 있다.
    - pip
    - setuptools
- `python3 -mpip install django` 다시 장고 설치

남들과 같이 쓰기 위해서!! 내가 뭘 쓰는지 알기 위해서 venv!! 
- `pip freeze` 버전 얼려버리기!
- `requirements.txt` 만들기 (Python 국룰!)
- `pip freeze > requirements.txt` 
> **>** 왼쪽 출력값 오른쪽 파일에 쓴다.
- `.gitignore` 에 `venv/` `.vscode/` 등 추가 
    - [gitignore.io](https://www.gitignore.io/) 에서 검색
    - `python`, `venv`, `django`, `macOS` 로 검색한 파일 내용 붙여넣기.

- `django-admin startproject django_advance .`으로 프로젝트 시작
- `init project & app Board`

- `control + g` 줄번호 입력 후 이동
- `master url`
~~~ python
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('board/', include('board.urls')),
]
~~~
- `board/urls.py`

`mkdir -p board/templates/board`
`cd board/template/board`
`touch article_list.html article_detail.html article_form.html base.html`
`cd -` 이전 위치로 돌아가기

- python faker
`python3 -mpip install faker`
- 


`python3 -mpip install ipython`
`python3 -mpip install django_extensions`
`python3 manage.py shell_plus`

#### 설치한 확장 프로그램
- vscode-icons
- Django
- SQLite

---
### tip

Q. SW 왜 만들어요?
A. 쓰려고 만들어요!!
Q. 굳이 웹 서비스로?
A. 결국 남들도 사용할 수 있도록 하려고!

성공했을때의 코드만큼 실패했을때의 에러코드 만드는 것도 매우 중요하다!!!

# Day20
> 19.12.3 화
`source venv/bin/activate`
`python3 manage.py startapp accounts`

#### DB 날리기
`rm db.sqlite3`
`rm board/migration/0*`


#### email 검증
`from django.core.validators import EmainValidator`를 통해 이메일 검증을 할 수 있지만 모델링이 복잡해진다.

- `touch board/forms.py`
~~~ python
# forms.py
from django import forms
# from django.db import models
from .models import Article, Comment

class ArticleForm(forms.ModelForm):
    class Meta:
        model = Article
        fields = '__all__'
~~~

`pip install django-bootstarp4` `python3 -mpip install django-bootstrap4`
~~~ python
INSTALLED_APPS = [
    'bootstrap4',
    ... 
]
~~~
- form 이쁘게 나오게 하기! `{{ form.as_p }}` -> `{% bootstrap_form form %}`
~~~ html
<!-- new_article.html -->
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block content %}
<h1>New Article</h1>
<form action="" method="POST">
    {% csrf_token %}
    {% bootstrap_form form %}
    <input type="submit">
</form>
{% endblock  %}
~~~

### form의 능력
1. 유효성 검사
2. 
edit.html 만들 필요 없음.
html 한장으로 new, edit, create, update가 끝난다!!

~~~ python
# models.py
from django.db import models
from faker import Faker
f = Faker()

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=300)
    keyword = models.CharField(max_length=50)
    email = models.CharField(max_length=200)
    content = models.TextField()
    datetime = models.DateField(blank=True, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    @classmethod
    def dummy(cls, n):
        # article = cls(); 
        # article.title='asdf'
        # article.content='..'
        # ...
        # article.save()
        for i in range(n):
            cls.objects.create(
                title=f.text(20),
                content=f.text(),
                keyword=f.company(),
                email=f.email(),
            )

class Comment(models.Model):
    content = models.CharField(max_length=300)

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
~~~
~~~ python
# forms.py
from django import forms
# from django.db import models
from .models import Article, Comment

class ArticleForm(forms.ModelForm):
    # ModelForm, Meta, model, fields 그대로 써야함.
    
    # 미세 조정 (브라우저단에서 제한하는것. 속성 지우면 서버로 넘어감.)
    title = forms.CharField(min_length=2, strip=True) # 최소 문자수 설정, 앞뒤 띄어쓰기한것 자르기(왜 필요? 서버단에서는 1바이트도 돈이다.)
    email = forms.EmailField()
    keyword = forms.CharField(min_length=1, max_length=10)
    class Meta:
        model = Article
        fields = '__all__' # 모든 컬럼
        # 원하는 컬럼만 표현하고 싶을때
        # fields = ('title', 'content')
        # 
        ###########################3
        # date만 빼고 싶을때 
        exclude = ['datetime',]
~~~
~~~ python
# views.py
from django.shortcuts import render, redirect, get_object_or_404
from .models import Article, Comment
from .forms import ArticleForm

# Create your views here.
def new_article(request):
    if request.method == 'POST':
        # 사용자가 제출한 후
        # pass
        form = ArticleForm(request.POST) # POST로 온 데이터 알아서 각각 넣어줌
        print('Data 넘어옴')
        print(form)
        if form.is_valid(): # 데이터 유효한지 검사
            article = form.save()
            return redirect('board:article_detail', article_id)
    else: # GET
        form = ArticleForm()
    context = {
        'form': form,
    }
    print(form.errors)
    return render(request, 'board/article_form.html', context)

def article_list(request):
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    # article = Article.objects.get(pk=article_id) # pk=article_id | id=article_id
    context = {
        'article': article
    }
    return render(request, 'board/article_detail.html', context)

def edit_article(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    if request.method == 'POST':
        form = ArticleForm(request.POST, instance=article) # 
        if form.is_valid(): # 데이터 유효한지 검사
            article = form.save()
            return redirect('board:article_detail', article_id)
    else:
        form = ArticleForm(instance=article)
    context = {
        'form': form,
    }
    return render(request, 'board/article_form.html', context)
~~~
~~~ python
# urls.py
from django.urls import path
from . import views

app_name = 'board'

urlpatterns = [
    path('', views.article_list, name="article_list"),
    path('<int:article_id>/', views.article_detail, name="article_detail"),
    path('new/', views.new_article, name="new_article"),
    path('<int:article_id>/edit/', views.edit_article, name="edit_article"),
]
~~~
- `html`
~~~ html
<!-- article_detail.html -->
{% extends 'base.html' %}

{% block content %}
<h1>{{article.title}}</h1>
<small>{{ article.created_at }} | {{ article.updated_at }}</small>
<p>{{article.content}}</p>

<p>
    {{ article.keyword }} | {{ article.emails }}
</p>
{% endblock  %}
~~~
~~~ html
<!-- artucle_form.html -->
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block content %}
<h1>New Article</h1>
<form method="POST">
    {% csrf_token %}
    {% bootstrap_form form%}
    
    <input type="submit">
</form>
{% endblock  %}
~~~
~~~ html
<!-- article_list.html -->
{% extends 'base.html' %}

{% block content %}

<h1>Article List</h1>
<ul>
    {% for article in articles %}
    <li>
        <a href="{% url 'board:article_detail' article.id %}">
            {{article.title}}
        </a>
    </li>
    {% endfor %}
</ul>
{% endblock %}
~~~
~~~ html
<!-- new_article.html -->
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block content %}
<h1>New Article</h1>
<form action="{% url 'board:new_article' %}" method="POST">
    {% csrf_token %}
    {% bootstrap_form form%}
    
    <input type="submit">
</form>
{% endblock  %}
~~~

#### 로그인 검사
`@login_required`를 함수 위에 추가하면 그 함수는 로그인을 해야만 실행될 수 있다.


# Day 21
> 19.12.4 수

### todaty todo
- datetimepicker

### User 관리
- `settings.py`에서 확인 할 수 있는 `django.contrib.auth`
- `db.sqlite3`에서 확인 할 수 있는 `auth_user` 테이블
> auth : 인증

장고의 특징
1. 인증
2. admin 페이지
> 장고 만든 이유 \
신문사 소속의 개발자들이 신문이 뜨면서 종이신문 말고 인터넷으로 신문을 보게 될 무렵..\
Django: The Web framework of   \
마감에 쫓기는 완벽주의자들을 위한 웹 프로그래밍\
기사를 쓰는 기자들이 봐야하는 관리자페이지들이 필요했다. 기자들이 자신의 기사를 올려야했기 때문에 admin페이지와 user 관리가 필수적이었다.

- 장고 기본 user 사용해보자
> 장고 project = app + app + ... + app : 프로젝트는 app들의 총 합

- `setting.py`
~~~ python
...
# AUTH_USER_MODEL = 'auth.User'

# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/2.2/howto/static-files/

STATIC_URL = '/static/'
STATIC_DIRS = [
    # os.path.join(BASE_DIR, 'static'),
]
STATIC_ROOT = os.path.join(BASE_DIR, 'static')
~~~
- superuser 만들기 `python3 manage.py createsuperuser`
> 비밀번호 암호화 -> SHA
브라우저 기준으로 쿠키에 로그인 정보가 저장되어 있음.

