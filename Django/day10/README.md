# Day10
> 19.11.19 화
### today todo
- CRUD 혼자서 구현하기
- REST api

## RESTful api
역할|Request-Method|<center>End-point</center>|Views(Function)|기존역할
:---:|:---:|:---|:---:|:---
Create|GET|/articles/new|new|새글 form
Create|POST|/articles/create|create|새글 작성
Read|GET|/articles/`<id>`|show|글 하나
Read|GET|/articles/|index|전체 리스트
Update|GET|/articles/`<id>`edit|edit|수정 form
Update|POST|/articles/`<id>`/update|update|업데이트
Delete|POST(DELETE)|/articles/`<id>`/delete|delete|삭제