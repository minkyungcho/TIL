컴파일러 구조...........



트리플 : 여행계획



크롤링

django, flask



[프론트엔드 개발자는 왜 구하기 어렵나요?](https://taegon.kim/archives/4810)



#### 구글 검색 방법

~~(언어) how to ~~(원하는것)

**stackoverflow** 질문 검색



#### 브라우저의 랜더링 과정 

[브라우저는 어떻게 동작하는가?](https://d2.naver.com/helloworld/59361)

동작과정

그림2

DOM 트리, 랜더 트리

그림4 모질라의 개코랜더링 동작과정

ES6?

[웹 브라우저에서 HTML문서 렌더링 과정 (동작 순서)](https://jeong-pro.tistory.com/90)



#### wappalyzer 설치 



### 개발자도구

네이버 웹툰 + F12 

#### Network 

데스크탑 버전

##### All

인덱스 - CSS - javascript - img

*index.nhn*

**<head>**

문서에 대한 다양한 정보 제공 그치만 우리; 눈에 보여지는건 타이틀 밖에 없음

**<meta>**

메타 태그 중 og 태그는 미리보기에 대한 정보들, 서치엔진에 최적화

검색엔진에서 원하는 형식에 맞춰서 넣어준다. 

형식은 웹마스터도구

구글 웹마스터도구 / 네이버 웹마스터도구

og 태그가 잘 되어있지 않으면 검색할때 안뜸

**<link>**

css 링크들 불러오기

**<body>**

div 태그나  a태그에 의해서 DOM 트리가 그려지기 시작함.,

jQuery는 DOM의 body 처음부터 보기시작함.

태그 이름과 속성들

태그 자체가 속성일 수도 있고 속성들을 활용. 프레임워크에 따라서 여러방향으로 사용할 수 있음

**<script>**

틀만 있는곳에 element를 붙이기 시작함

이미지, 광고 들을 붙임.

어떤 데이터를 붙이는지도 확인할 수 있음

데이터들에 대한 요청이 다시 들어가는 곳

데이터들 담고 있는 파일(형식) : xml, json

크롤링 할때는 xml 파일 불러서 요청한다.

Headers에서 어떤 url, 어떤 형식으로 어떤 파라미터 쓰는지 확인

틀 그리는 놈(index.nhn), 데이터 붙이는 놈(mainTopXml.nhn?order=viewCount&null)



##### 모바일버전

xml : 태그가 있음

json : '['으로 열고 ']'으로 닫고, key값이 있음

graphql [graphQL 개념잡기](https://tech.kakao.com/2019/08/01/graphql-basic/)

GQL 은 웹클라이언트가 데이터를 서버로부터 효율적으로 가져오는것

데이터를 효율적으로 요청하는 것

클라이언트시스템에서 작성하고 호출한다.

프론트엔드쪽에서 백엔드에 요청할때.

```json
{
  hero {
    name
    friends {
      name
    }
  }
}

{,…}
data: {getEditComponentList: [{id: 194, type: "IMAGE", position: 1, __typename: "EditComponent"},…]}
getEditComponentList: [{id: 194, type: "IMAGE", position: 1, __typename: "EditComponent"},…]
0: {id: 194, type: "IMAGE", position: 1, __typename: "EditComponent"}
id: 194
position: 1
type: "IMAGE"
__typename: "EditComponent"
1: {id: 190, type: "IMAGE_MULTI_HORIZONTAL", position: 2, __typename: "EditComponent"}
2: {id: 182, type: "COLLECTION_TITLE", position: 3, __typename: "EditComponent"}
3: {id: 83, type: "IMAGE_MULTI_HORIZONTAL", position: 4, __typename: "EditComponent"}
4: {id: 185, type: "IMAGE", position: 5, __typename: "EditComponent"}
```

gql 어떤 곳에서 어떻게 사용하였는지??



XHR : 데이터 물고 있는 파일들 볼 수 있음



#### Prieview

구조만 확인할 수 있음



데이터 서버 <-> desctop, mobile(web), app

서버는 데이터(xml, json)만 줌

javascript, ios, android가 그림

서버의 역할을 줄이고 프론트엔드에서 함.



##### UX

click -> 일단 화면 바꿔 -> 데이터 채워 -> 부분부분 업로딩 되는 것 확인함



### 1. Python 설치

https://wikidocs.net/8 보면서 설치

https://www.python.org/downloads/release/python-374/

Windows x86-64 executable installer

### 2. VSCode 설치



### 3. powershell

윈도우 + r + powershell



### 저장 환경구축

내문서에 DJANGO 폴더 만들고 파일 경로 바꿔주기



#### 데이터 받아오기

url 

import : 요청을 만들어주는 라이브러리

reponse : 응답 저장용

> requests module 설치 : pip install requests
>
> python 파일 실행 : python req.py

##### 응답코드

200, 300, 400, 500...

- 200 - 정상 : 서버에 어떤 요청을 보냈는데 정상적인 응답이 옴

- 300 - redirection 리디렉션 : 처리해야하는것 다른곳으로 보내줌

- 400 - client error 클라이언트 오류 : 사용자 잘못, 책임.

  ​	404 not found : 해당 url을 찾을 수 없음

  ​	403 forbidden : 이 페이지를 볼 수 없음 권한없음

- 500 - server error 서버 오류 : 개발자 잘못, 책임. 

print로 response.text를 찍어서 확인해본다.

request method 가 GET 방식 이어서 편하게 받아올 수 있었다.



mvc와 mvvm 비교 [MVC, MVP, MVVM 비교](https://beomy.tistory.com/43)



resume 

어떤 방식으로 썼고, 왜 썼고, 개발철학이 필요하고 목적이 있어야한다.

