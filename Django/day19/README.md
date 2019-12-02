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