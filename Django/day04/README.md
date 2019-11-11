# Day4
---
- 지난주 Remind
    - Chrome 개발자 도구 Network 탭 보는법
    - Network 탭과, Elements 탭을 분석해서 여러 사이트 크롤링하는 방법
        - 파라미터 만들어서 서버로부터 원하는 자료 가져오기
        - 크롤링할 때 일반 클라이언트인 척 해보기
        - CSRF Token -> POST
        - 요청보내서 받은 데이터를 다시 파싱해서 새로운 URL로 요청보내기
    - Flask
        - WAS : Web Applicatiopn Sever
        ~~~ python
        app.route('/') # Route
        def ~~(): # View
            ~~
            return HTML
        ~~~
- 지난주 과제
    1. 사람인 크롤링 했던거 세부정보까지  크롤링하기
    2. 다음웹툰도 웹툰  세부정보 크롤링하기
        - 각 요일별 웹툰 데이터 ->  월,  화, 수,,, -> 각 요일 웹툰 리스트 -> 해당 웹툰의 세부정보
---
- Form 데이터 처리하기
    - path param
- Django 입문
    - 이번주 아이디어톤 전까지는 장고의 파일구조 살펴보기
    - ORM 빼고 계속 새로운 프로젝트로 재미난거 만들기
        - 신이 나를 만들때
        - 로또 번호 생성기
        - ...
    - 이번주 아이디어톤 이후에 CRUD -> ORM
    - 다음주 내내 CRUD 가지고 재밌는거 만들자~~
- 백엔드, 프론트엔드 기술면접
    - 인터페이스 : "약속"
---
## Form 데이터 처리하기
### path param
- 기본 틀 만들기
    - app.py
    ~~~ python
    # app.py
    from flask import Flask, request, render_template
    import requests
    import json
    app  = Flask(__name__)
    # 1. 전체 요일 적혀있는 페이지
    @app.route('/')
    def index():
        days = {
            '월요일': 'mon',
            '화요일': 'tue',
            '수요일': 'wed',
            '목요일': 'thu',
            '금요일': 'fri',
            '토요일': 'sat',
            '일요일': 'sun'
        }
        return render_template('index.html', days=days)
    ~~~
    - templates 폴더 생성하고 index.html 만들기
    ~~~ html
    <!-- templates/index.html -->
    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
    </head>
    <body>
        {% for key, value in days.items(): %}
            <a href="/{{ value }}">{{ key }}</a><br>
        {% endfor %}
    </body>
    </html>
    ~~~
- 요일별 웹툰 
~~~
webtoon= {
    '한글웹툰이름': ['닉네임', '요약', '작가'],
    ...
}
~~~
- app.py
~~~ python
# app.py
# 2. 각 요일에 대한 데이터 요청하는 곳
@app.route('/<day>')
def day_webtoon_list(day):
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    response = requests.get(url)
    data = response.json()
    webtoons = {}
    for toon in data["data"]:
        webtoon_title = toon["title"]
        webtoon_nickname = toon["nickname"]
        webtoon_introduction = toon["introduction"]
        webtoon_artists = []
        for artist in toon["cartoon"]["artists"]:
            webtoon_artists.append(artist["name"])
        
        webtoons[webtoon_title] = [webtoon_nickname, webtoon_introduction, ",".join(webtoon_artists)]
    return render_template('day_webtoon_list.html', webtoon_dic=webtoons)
# 3. 각 웹툰에 대한 세부데이터 요청하는 곳
@app.route('/webtoon/<nickname>')
def webtoon_info(nickname):
    url = f'http://webtoon.daum.net/data/pc/webtoon/view/{nickname}'
    return requests.get(url).json()
~~~
- day_webtoon_list.html
~~~ html
<!-- templates/day_webtoon_list.html -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    {% for key, value in webtoon_dic.items(): %}
        <a href="/webtoon/{{value[0]}}">{{ key }}</a> / {{ value[1] }} / {{ value[2] }}<br>
    {% endfor %}
</body>
</html>
~~~

> list를 문자열로 합치기
> ",".join(artists)

---
### Fake Naver 만들기
1. Fake 검색창
    - query string
    - 검색창, 검색결과
2. Fake Login
    - 로그인창 -> 로그인 로직 -> 메인창

#### Fake 검색창
**form** 활용
- name="query" 
~~~ python
# app.py
# Fake naver
@app.route('/naver')
def fake_naver():
    return render_template('naver.html')

@app.route('/naver/search')
def fake_naver_search():
    # 검색 로직
    query = request.args.get('query')
    return render_template('search.html', q = query)
~~~
- naver.html
~~~ html
<!-- templates/naver.html -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <!-- form 태그를 이용해서 검색창 만들기 -->
    <!-- 검색창 : 검색어 입력, 검색 버튼 -->
    <form action="/naver/search" method="GET">
        <input type="text" name="query">
        <input type="submit">
    </form>
</body>
</html>
~~~
- search.html
~~~ html
<!-- templates/search.html -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <!-- '검색어'에 대한 검색 결과입니다. -->
    '{{q}}'에 대한 검색 결과입니다.
</body>
</html>
~~~

#### Fake Login
~~~ python
# app.py
@app.route('/login')
def login_form():
    # 아이디 입력창, 패스워드 입력창, 로그인 버튼
    return render_template('login.html')

@app.route('/login/submit', methods=['POST'])
def login():
    # 아이디를 조회하고, 해당 row의 비밀번호가 일치하는지 확인
    # 로그인 로직
    # return render_template('success.html')
    return redirect(url_for('main'))

@app.route('/main')
def main():
    return '로그인에 성공하셨습니다. 메인페이지 입니다.'
~~~
~~~ html
<!-- templates/login.html -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form action="/login/submit" method="POST">
        <input type="text" name="id">
        <input type="password" name="password">
        <input type="submit">
    </form>
</body>
</html>
~~~

---
### REST API
REST API 설계시 중요한 항목 2가지
1. URI는 정보의 자원을 표현
2. 자원에 대한 행위는 HTTP Method(GET, POST, PUT DELETE)로 표현

CRUD
**C** - Post 생성 /webtoons
**R** - GET 조회 /webtoons/webtoon or /webtoons/<nickname>
**U** - PUT, PATCH 업데이트 /webtoon/<nickname>
**D** - DELETE /webtoon/<nickname>

---
## Django 
### django 설치
~~~ command
$ python3 -mpip install django
~~~
#### 환경설정
- project 생성
~~~ command
$ django-admin startproject myapp
~~~
- myapp으로 working dir 변경
- python3 manage.py runserver 으로 장고 서버 실행 -> unapplied migration 있다는 에러 나옴
- python3 manage.py migrate 으로 해결
- http://localhost:8000/ 접속

**myapp 구성**
- manage.py
- db.sqlite3
- myapp
    - __init__.py
    - settings.py
    - urls.py : @app.route 한 것들 모여있음
    - wsgi.py : web server gateway interface

**언어 설정 한글로 바꾸기**
- settings.py
    ~~~ python
    LANGUAGE_CODE = 'ko' 
    ~~~

---
## Tip
네이버 카카오 배민 토스 팀블로그 참고!
타다 vcnc 참고!
