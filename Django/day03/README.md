# Day3

---

### 개발자 면접 질문 정리

https://blockchain-baam.tistory.com/34

- java main class 만들기
  - public void main() 

---

### to do

- parameter

  - query string
  - path parameter

- 웹툰 데이터 요일별로 다르게 url 세우기

  - ../mon, ../tue ,,,

- html 파일로 view 만들기(render template)

  - 템플릿을 정해진 규격대로 넣는다(랜더한다)

- Beautiful soup

  - 사이트 구조 분석하는 방법 
    - html은 어떻게 하는지
    - 어떻게 찾는지
  - URL 구조
    - query string
  - '사람인' 크롤링해보기

  - '기술은행' / 등
  - 데이터기 xml 형태로 주고받는 사이트 제외하고 모두 크롤링 가능(로그인 안한 상태에서 볼 수 있는 것만)

---

- app.py

~~~ python
from flask import Flask, request, render_template

app = Flask(__name__)

if __name__ == '__main__':
    app.run(debug=True)

@app.route('/')
def index():
  request.args.get('파라미터명')
  return{'hello':'hi'}
~~~

~~~ command
$ export FLASK_RUN_PORT=8787
$ export FLASK_DEBUG="True"
$ export FLASK_ENV="Development"
$ flask run
~~~

- test

~~~ python
def index():
  something = {
    'aa': 'bb'
  }
  something["aa"]
  something.get("aa") 
~~~



### query string

파라미터를 넘기는 방법 중 하나

내가 입력한 값에 따라서 바뀌는 것을 확인 할 수 있음

중요하지 않은 정보들을 받을때

~~~ python
@app.route('/')
def index():
    # request.args -> Dictionary(Immutable)
    # 클라이언트로부터 받은 파라미터를 저장하고 있음
    student = request.args.get('student')
    return{'hello':student}
~~~

~~~ url
http://localhost:8787/?student=bye
{
hello: "bye"
}
~~~



### path parameter

중요할 수 있는 정보를 받을때

url자체에서 정보들을 구성하고 싶을때

~~~ python
@app.route('/<day>')
def toons(day):
    return { 'today is': day}
~~~

~~~ url
http://localhost:8787/friday
{
today is: "friday"
}
~~~

- 하루치의 다음웹툰 데이터 뽑아오기
~~~ python
@app.route('/daum_webtoon/<day>')
def daum_toon(day):
    # daily_toon_data = {} # 선언
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_from_url(url)
    # daily_toon_data[day] = parse_daum_webtoon_data(data)
    # return daily_toon_data
    return { day: parse_daum_webtoon_data(data)}

def request_json_data_from_url(url):
    response = requests.get(url)
    data = response.json() # 응답으로 온 내용을 JSON형식으로 바꿔줌.
    return data

def parse_daum_webtoon_data(data):
    #print(data)
    toons = []
    for toon in data["data"]:
        # 제목의 key는 'title'
        title = toon["title"]

        # 설명의 key는 'introduction'
        desc = toon["introduction"]

        # 작가랑 장르 
        # 정통방법!
        # 장르의 위치는 'cartoon' 안에 'genre' 라는 리스트 안에 'name'이라는 key
        genres = [] # 장르의 데이터를 하나씩 넣자!
        for genre in toon["cartoon"]["genres"]:
            genres.append(genre["name"])
        # print(genres)
    
        # 작가 이름의 위치는 'cartoon' 안에 'artists'라는 리스트 안에 'name'이라는 key
        artists = []
        for artist in toon["cartoon"]["artists"]:
            artists.append(artist["name"])
    
        # 썸네일 이미지
        img_url = toon["pcThumbnailImage"]["url"]
        tmp = {
            title : {
                "desc" : desc,
                "writer" : artists,
                "img_url" : img_url
            }
        }

        # 정보들 넣기
        toons.append(tmp)
    return toons
~~~

#### html 문서로 return
- error code
~~~ python
@app.route('/daum_webtoon')
def daum_toon():
    html = '''
        <a href="/daum_webtoon">월요일</a>
    '''
    return html
~~~
~~~ url
http://localhost:8787/daum_webtoon
ASSERTION ERROR # daum_toon()이라는 같은 이름의 함수가 있어서 에러
~~~

- 정상 code
~~~ python
@app.route('/daum_webtoon')
def daum_toon_index():
    html = '''
        <a href="/daum_webtoon/mon">월요일</a>
        <a href="/daum_webtoon/tue">화요일</a>
        <a href="/daum_webtoon/wed">수요일</a>
        <a href="/daum_webtoon/thu">목요일</a>
        <a href="/daum_webtoon/fri">금요일</a>
        <a href="/daum_webtoon/sat">토요일</a>
        <a href="/daum_webtoon/sun">일요일</a>
    '''
    return html
~~~

#### html 파일 따로 만들어서 작업
- templates 폴더 생성 후 html 파일 작성
- html:5 + 엔터키 : 자동완성
~~~ html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
~~~
- 요일별 웹툰 리스트 불러오는 a 링크 추가
~~~ html
<body>
    <a href="/daum_webtoon/mon">월요일</a>
    <a href="/daum_webtoon/tue">화요일</a>
    <a href="/daum_webtoon/wed">수요일</a>
    <a href="/daum_webtoon/thu">목요일</a>
    <a href="/daum_webtoon/fri">금요일</a>
    <a href="/daum_webtoon/sat">토요일</a>
    <a href="/daum_webtoon/sun">일요일</a>
</body>
~~~
~~~ python
@app.route('/daum_webtoon')
def daum_toon_index():
  return render_template('daum_webtoon_list.html')
~~~

- for문으로 요일별 웹툰 리스트
~~~ html
<body>
    {% for day in days %}
      <a href="/daum_webtoon/{{ day }}">{{ day }}</a>
    {% endfor %}
</body>
~~~
~~~ python
@app.route('/daum_webtoon')
def daum_toon_index():
  days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
  return render_template('daum_webtoon_list.html', **locals())
~~~

*문제가 생길 수 있다!*
모든 local 데이터가 넘어가기 때문에 사용자가 이상한 데이터를 보는 큰일이 생길 수 있다
~~~ python
msg="배고푸댬 히히"
~~~
~~~ html
{{msg}}
~~~
- 원하는 데이터만 보이게 하기
  - **locals() -> days=days

---
### Beautiful soup
- bs4 모듈 설치
~~~ command
> python3 -mpip install bs4
~~~

- 뷰티풀 슾이 응답으로 온 텍스트를 속성들을 이용해서 한번 걸러야함 **select** 활용
  - banner_list > div:nth-child(2) > ul:nth-child(3) > li:nth-child(10) > div.part_top > h3
~~~ python
html = BeautifulSoup(response.text, 'html.parser') # 
company_names = html.select('.company_name') # class가 company_name인 모든 친구들
print(company_names)
print(type(company_names)) # <class 'list'> 배열은 반복문으로 사용가능!
~~~
~~~ python
for company_name in company_names:
    print(company_name)
    print(company_name.text) # 태그를 제외한 텍스트만 뽑아오기
~~~
- 두가지 조건을 가진 목록 한번에 불러오기 **zip** 활용
~~~ python
for company_name, recruit_name in zip(company_names, recruit_names):
    print(f'{company_name.text} - {recruit_name.text}')
~~~
- 더 큰 조건으로 작은 조건들을 뽑아내보자
~~~ python
company = html.select('.part_top')
for com in company:
    # print(type(com)) # <class 'bs4.element.Tag'>
    print(f'{com.select_one(".company_name").text}- {com.select_one(".recruit_name").text}- {com.select_one(".list_recruit_condition").text}')
~~~

**full code**
~~~ python
from bs4 import BeautifulSoup
import requests

url = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=404&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
url2 = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=407&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
url3 = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_key=40701&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
response = requests.get(url)

# print(response.text)

html = BeautifulSoup(response.text, 'html.parser') # 
company_names = html.select('.company_name') # class가 company_name인 모든 친구들
recruit_names = html.select('.recruit_name')
recruit_conditions = html.select('.list_recruit_condition')

for company_name, recruit_name, recruit_condition in zip(company_names, recruit_names, recruit_conditions):
    print(f'{company_name.text} - {recruit_name.text} - {recruit_condition.text}')

# print(company_names)
# print(type(company_names))
for company_name in company_names:
    #print(company_name)
    print(company_name.text)

for recruit_name in recruit_names:
    print(recruit_name.text)

# 길이가 같은지 확인해본다
# print(len(company_names))
# print(len(recruit_names))
# print(len(recruit_conditions))

# 더 큰 조건으로 작은 조건들을 뽑아내보자
company = html.select('.part_top')
for com in company:
    # print(type(com)) # <class 'bs4.element.Tag'>
    print(f'{com.select_one(".company_name").text}- {com.select_one(".recruit_name").text}- {com.select_one(".list_recruit_condition").text}')
    #print(com.select('list_recruit_condition'))
~~~
---
##### workshop



---
#### POST 방식
~~~ python
from bs4 import BeautifulSoup
import requests
response = requests.get(url)
html = BeautifulSoup(response.text, 'html.parser') 
company_list = html.select('ul.product_list li')

for com in company_list:
    # print(type(com))  # <class 'bs4.element.Tag'>
    idx = com.select_one('a')['href'].split('=')[-1]
    company_info_url = "http://www.saramin.co.kr/zf_user/jobs/relay/view-ajax"
    company_info_params = { 'rec_idx' : idx }
    company_response = requests.post(company_info_url, params=company_info_params) # url과 params라는 매개변수
    print(company_response)
    company_html = BeautifulSoup(company_response.text, 'html.parser')
    company_title = company_html.select_one('a.company').text
    print(company_title.strip()) # strip() 을 활용하여 공백 없애기
    break
~~~
- result
~~~
p-70-12-225-25:day03 ming$ python3 saramin_parser.py 
<Response [200]>
㈜메디씽큐
ip-70-12-225-25:day03 ming$ python3 saramin_parser.py 
<Response [200]>
(주)위즈더플래닛
~~~

> 정규표현식
  python regen 8 digit
---
## 주말 WORKSHOP
1. 사람인 나머지 데이터 파싱

2. 다음웹툰 일요일 첫번째 나오는 웹툰 상세보기 페이지 크롤링
- hint
  - 요청 보내는 url 따로, ,,, 따로
  - url 비교하다 보면 다른 부분 있음, 달라지는 내용이 어딨는지 그거 보면서 해보자!

