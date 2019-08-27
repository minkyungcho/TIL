### 가상 device 생성하기

configure - AVD Manager - create ~ - phone, 5.1WVGA - 

show advanced setting

ram : 2048, heap : 512

*finish*

## create new project

empty activity 

Hello 

경로 : C:\android\AndroidStudioProjects\Hello

언어 : java

minimum api level : 5.1 lollipop

*finish*



#### 안드로이드 구조

UI는 xml로 만들고 main_activity controller는 java.



#### resource Manager

- Mip Map : 어플 icon lancher. hdpi에 저장
- drawable : 어플 이미지, 배경 등 관리. 
- layout
- values 

#### manifests

- 환경적인 요소들
- icon, label 등 결정
- 메인으로 시작하는 클래스. 시작하면 on.create이 작동됨.



```
onPause()
```

앱이 사라졌을때 실행되는 함수

```
onResume()
```

앱이 실행될때. 



#### layout

match_parent : 화면 가로 비율에 맞춰서

wrap_content : font 크기에 맞춰서

| 레이아웃        | 설명                                                         |
| --------------- | ------------------------------------------------------------ |
| 제약 레이아웃   | 제약 조건 기반 모델                                          |
| 리니어 레이아웃 | 박스 모델 div - vertical, horizental                         |
| 상대 레이아웃   | 규칙 기반 모델 - 사용자가 갖다대면 버튼이나 텍스트가 만들어짐 |
| 프레임 레이아웃 | 싱글 모델 - 여러개의 화면이 중첩되어 들어가는 뷰             |
| 테이블 레이아웃 | 격자 모델                                                    |

 

##### 뷰 영역 알아보기

- padding
- margin

- **gravity**

- **layout gravity**

- layout weight

##### 레이아웃

- layout_alignParentBottom

---

**p. 115**

버튼 클릭하면 색깔 바꾸기.

[실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day02/P115)

**p. 158**

버튼4개 활용.

버튼 2개로 사진 바꾸기. **VISIBLE & INVISIBLE**

버튼 2개로 한 view에 로그인 / 회원가입 화면 뜨도록 제어

[실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day02/P158)

---

## workshop

**#1 p. 168**

두개의 이미지뷰에 이미지 번갈아 보여주기

[실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day02/P168)



**#2 p. 169**

SMS 입력 화면 만들고 글자의 수 표시하기

[실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day02/P169)



**#3**

#1, #2 통합하여 하단버튼 두개로 조작.

각 버튼을 누르면 각각의 화면이 출력되도록.