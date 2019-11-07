# Day2

---

- 파이썬 딕셔너리
  - 딕셔너리
  - 얘를 왜 씀?
  - 얘를 어떻게 활용할지?
  - JSON과 비교했을 때 어떤 차이가 있는지?
  - 딕셔너리 활용 문제
- 어제 사용했던 다음 웹툰 코드를 활용해서 우리가 원하는 정보를 뽑아내는 것
- 자유롭게 원하는 사이트에서 데이터를 뽑아내보기
  - html 통째로 뿌리는지 / 틀 만들고 데이터 붙이는지 비교
- 함수 활용하기
- 플라스크 기본

---

정보를 보관하는 방법

- 변수에 저장한다. 변수 : 정보를 담기위한 하나의 박스
  - a = "haha"
- 배열 (array, list): 비슷한 정보들을 담기위한 박스들을 옆으로 붙여서 나열
  - python에서는 다양한 변수형을 하나의 배열에 저장할 수 있음
- Dictionary (Hash, HashMap) : key & value
  - 중복되는 key값이 있으면 마지막 정보만 기억함.
  - key값은 고유해야함
  - 하나의 변수에 더 많은 정보를 담고, 정보를 뽑아서 쓸때 더 잘 뽑아서 사용할 수 있다.
  - value값에 또 dictionary가 들어갈 수 있다. 여러 타입의 정보가 들어갈 수 있음.

---

### 좋은 코드란?

- 다른사람이 만든 코드를 봐도 이해가 가는 코드..?
- 주석없이도 읽히는 코드
- 변수명으로 '뭘하고 어떤걸 담고 있는지'가 보이는 코드

---

random module

- choice
- select

> 덕타이핑

---

문자열 더하기

"a" + "b"

"ab"

"a" + 변수 + "b"

"a변수b"

### format string

~~~python
name = 'Bob'
test = f'Hello {name}'
print(test)

~~~

---

다음 웹툰 데이터 크롤링 해서 원하는 데이터 뽑아내기

- **다음웹툰**
- 데이터 크롤링
- 데이터 뽑아내기

### 함수 만들기

~~~python
def 함수명(파라미터):
  
~~~

---

- 내가 자주 접속하는 사이트들 중에 우리가 좀전에 배운 형태로 되어 있는 사이트를 찾아서 필요한 정보들만 뽑아서 화면에 출력하세요.



---

## flask

- app.py

~~~python
from flask import Flask, escape, request

app = Flask(__name__)

@app.route('/')
def index():
    return { 'method': 'Hello'}
~~~

- terminal

~~~ command
> flask run
* Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
~~~

- chrome에 localhost:5000 접속

~~~
{"method":"Hello"}
~~~





production 모드 : 배포 할때 모드

developement 모드 : 개발하면서 사용하는 모드

debug 모드 

- 환경을 껐다가 키지 않고 새로고침만으로도 

> python flask how to change enviroment

~~~command
> env:FLASK_ENV="Development"
> env:FLASK_DEBUG="True"
---
MAC OS
> export FLASK_DEBUG="True"
> export FLASK_ENV="Development"
> export FLASK_RUN_PORT=8787
~~~

