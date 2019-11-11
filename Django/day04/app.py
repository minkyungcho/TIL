from flask import Flask, request, render_template, redirect, url_for
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

# Fake naver
@app.route('/naver')
def fake_naver():
    return render_template('naver.html')

@app.route('/naver/search')
def fake_naver_search():
    # 검색 로직
    query = request.args.get('query')

    return render_template('search.html', q=query)

@app.route('/login')
def login_form():
    # 아이디 입력창, 패스워드 입력창, 로그인 버튼
    return render_template('login.html')

@app.route('/login/submit', methods=["POST"])
def login():
    # 아이디를 조회하고, 해당 row의 비밀번호가 일치하는지 확인
    # 로그인 로직
    return render_template()
    #return redirect(url_for('/main'))

@app.route('/main')
def main():
    return '로그인에 성공, 또 오자'


#### 각각의 메소드들을 개별 방말고 기닽가 간다니ㅠㅡ
