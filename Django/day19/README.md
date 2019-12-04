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


### 로그인 검사
`@login_required`를 함수 위에 추가하면 그 함수는 로그인을 해야만 실행될 수 있다.


# Day 21
> 19.12.4 수

### todaty todo
- datetimepicker

