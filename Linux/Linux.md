- HW 기반으로 만든 MAC OS(리눅스)
- 가상머신에 컴퓨터가 들어가있는 모양
- 가상머신 다운받아서 가상환경 만들기
- 가상HW 만들기 centOS-64
- 빅데이터 환경을 구성하기 위해 리눅스 사용



## CentOS

### I. Client 설치 - 서버 2개 만들기



### II. IP 할당

DHCP(Dynamic Host Configuration Protocol) : 자동으로 IP 할당받음

p. 68, 게시판 VMware IP Setting

- 



### III. 서버 보안 및 네트워크 설정 변경

#### step1~3

- 1406 시점의 OS에 맞는 sw 설치하기 위한 작업

- 업데이트 하지 않기 위한 작업

#### step4

server 변경

```
>hostnamectl set-hostname server1
>gedit /etc/hosts
// 추가 할 내용
192.168.111.100		server1
//ping 찍어서 확인해보기
>ping server1

```

#### step5~6

- 보안 설정 해제 작업
- 화면 보호기 해제





