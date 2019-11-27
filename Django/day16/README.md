# Day16
> 19.11.27 수

## AUTH
### admin 계정 생성
- admin 계정 만들기 `python3 manage.py createsuperuser`
- 사용자이름 : master
- 이메일 : master@mail.com
- 비밀번호 : master123
[admin url](localhost:8000/admin)

### user
- accounts app 만들기
- `python3 manage.py startapp accounts`
~~~ python
# settings.py
INSTALLED_APPS = [
    'article',
    'accounts',
]
~~~
~~~ python
# urls.py
urlpatterns = [
    path('admin/', admin.site.urls),
    path('insta/', include('article.urls'))
    path('accounts/', include('accounts.urls'))
]
~~~
~~~ python
# accounts/urls.py
from django.urls import path
from . import views as accounts_views

app_name = 'accounts'

urlpatterns = [
    path('login/', accounts_views.login, name="login"),
    path('logout/', accounts_views.logout, name="logout"),
    path('signup/', accounts_views.signup, name="signup"),
]
~~~
~~~ python
# views.py
from django.shortcuts import render, request

# Create your views here.
def login(request):
    return ''

def logout(request):
    return ''

def signup(request):
    return ''
~~~
- `accounts/templates` 폴더 만들고 `login.html`, `signup.html` 파일 생성
~~~ html
<!-- login.html -->
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Signin Template · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css"
        integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://getbootstrap.com/docs/4.4/examples/sign-in/signin.css">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

</head>

<body class="text-center">
    <form class="form-signin" method="POST">
        <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
        <h1 class="h3 mb-3 font-weight-normal">Please Login</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="username" type="email" id="inputEmail" class="form-control" placeholder="Email address" required
            autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
    </form>
</body>

</html>
~~~
~~~ html
<!-- signup.html -->
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Signin Template · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css"
        integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://getbootstrap.com/docs/4.4/examples/sign-in/signin.css">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

</head>

<body class="text-center">
    <form class="form-signin" method="POST">
        <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
        <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="username" type="email" id="inputEmail" class="form-control" placeholder="Email address" required
            autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password1" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <input name="password2" type="password" id="inputPassword" class="form-control"
            placeholder="Password Confirmation" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
    </form>
</body>

</html>
~~~
> django는 form을 통해 로그인, ... 지원 \
UserCreationForm : \
AuthenticationForm: 정보가 존재하는지, 

- `login.html`에 name이 `username`, `password` 인 input 생성
- `signup.html`에 name이 `username`, `password1`, `password2` 인 input 생성

~~~ html
<!-- base.html -->
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">INSTAGRAM</h5>
    {% if user.is_authenticated %}
        <a class="btn btn-outline-primary" href="/accounts/logout">{{user.username}}</a>
    {% else %}
        <a class="btn btn-outline-primary" href="/accounts/login">Login</a>
        <a class="btn btn-outline-primary" href="/accounts/signup">Sign up</a>
    {% endif %}
</div>
~~~
~~~ python
# views.py
from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout

# Create your views here.
def signup(request):
    if request.method == "POST":
        form = UserCreationForm(request.POST)
        if form.is_valid(): # 정보가 유효한지
            user = form.save()
            auth_login(request, user) # 로그인
            return redirect('articles') # insta 메인페이지로 보내기
        else:
            return render(request, 'signup.html')
    else: 
        # 로그인 되어 있는 상태인지 확인하기
        if request.user.is_authenticated:
            return redirect('articles')
        else:
            return render(request, 'signup.html')
        
def login(request):
    if request.method == "POST":
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            auth_login(request, form.get_user())
            return redirect('articles')
        else:
            return render(request, 'login.html')
    else:
        if request.user.is_authenticated:
            return redirect('articles')
        else:
            return render(request, 'login.html')    

def logout(request):
    auth_logout(request)
    return redirect('articles')

~~~
#### 결과
signup을 통해 회원가입을 하고, 가입된 계정으로 로그인을 하면 instagram에 접속!

---
### cookie vs session
HTTP request/response 무상태성(stateless)
- 무상태성을 해결하기 위해 나온 개념

- 차이 : 정보저장의 주체, 라이프사이클
    - cookie : 내 브라우저. 시크릿탭과 일반탭 저장을 다르게 함. 크롬과 익스플로러 또한 저장이 다르게 됨. 각각의 브라우저가 다르기 때문. 보안에 취약
    - session : 서버 컴퓨터. sessionid(내 정보의 위치). 브라우저가 종료되면 값이 reset됨. 서버컴퓨터에 정보를 요청. 로그인 상태를 서버컴에 저장. 
        - 내 컴: 정보의 위치만 저장
        - 서버 컴 : 실제 정보 저장. 

- 오ㅐ 쿠키와 세션 사용?
- 둘의 차이는?
- 활용방법에는 어떤것들?

