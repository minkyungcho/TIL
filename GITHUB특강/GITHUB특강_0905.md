TIL

**README.md**에 폴더 내용 설명해두기 

#### 기호

**#** 파운드 pound

**^** 캐럿 caret

***** 애스터리스크 asterisk

**&** 앰퍼센드 ampersand

**-** 대시 dash

**git**

- SCM (Source  Code Management)
- VCS (Version Control System)  


working directory -> storage area -> commit log

hash(anything) => 2^160 랜덤 숫자

---


# Git 기초
Git 기초 커맨드

## 기본 커맨드
- 'git init' : Git으로 프로젝트 관리 시작하겠다.(.git 폴더 생성됨.)
- 'git config --global user.name "사용자 이름"' : 사용자 이름 저장
- 'git config --global user.email 사용자이메일' : 사용자 이메일 저장
- 'git add [파일명]' : staging
- 'git commit -m [메세지] : commit 히스토리
- 'git log --oneline' : git 로그 한줄로 보기.

## 추가 커멘드
- 'git checkout [커밋해시]' : 특정 commit을 확인하기
- 'git restore [파일명]' : 추가 내용을 버릴 때, 최종 커밋 버전으로 복원

## 원격저장소 관리
- 'git remote' : 원격 저장소의 정보(이름)
- 'git remote -v' : 원격 저장소 정보(이름, 주소)
- 'git remote add [이름], [주소]' : 원격저장소 추가
- 'git push [저장소이름] [브랜치이름]' : 원격저장소로 코드 없데이트
- 'git clone [주소] [프로젝트이름]' : 원격저장소 복제
- 'git diff' : staging area와 commit log 비교


