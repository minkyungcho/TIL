# Day5
- Django 시작하기
    - 프로젝트 만들기
        - 프로젝트 vs 어플리케이션(app)
        - MVC -> MVT
        - Model View Controller -> Model View(Controller) Template(View)

- Django
    - 시작하기
    - django-admin startproject "projectname"
    - cd "projectname"
    - python manage.py startapp "appname" (**MAC** : python3 manage.py startapp "appname")
    - django에서 app단위는 하나의 모델에 대한 모든 내용이 담겨있다.
    - 예를 들어 게시판을 만든다고 하면, Post라는 app을 만들어서 그 안에서 모든 내용을 처리한다.

- 로또번호 생성기 + 
- 신이 나를 만들때

---
## 로또 만들기

~~~ command
$ django-admin startproject day5
$ cd day5
$ python3 manage.py startapp lotto
~~~