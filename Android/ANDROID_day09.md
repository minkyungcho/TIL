## 13 멀티미디어 다루기

### 13-3 음악 파일 재생하기

app/res 우클릭 - new - Folde - Raw Resorces Foler - finish

=> raw 폴더 생성되면 mp3 파일 복사 붙여넣기.

### 13-4 동영상 재생하기

raw 폴더에 mp4 파일 복사 붙여넣기

**P641**    [실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day09/P641)

음악 재생 및 정지, 동영상 재생



## 14 위치기반 서비스

### 14-1 GPS로 나의 위치 확인하기

manifest에 위치 권한 추가하기.

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

**P673**    [실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day09/P673)

에뮬레이터의 가상 위치 정보 텍스트로 표현하기.



### 14-2 현재 위치의 지도 보여주기

**P674**    [실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day09/P674)

시드니 좌표로 설정하고 지도에 마커와 함께 표시



**P675**    [실습코드](https://github.com/minkyungcho/TIL/tree/master/Android/day09/P675)

내 위치로 지도 이동시키고 마커이미지 추가하여 표시하기.

---

## WORKSHOP

**P676**

- 지도를 화면에 출력 하고 현재 위치를 찍는다.

- 서버에 현재 위치에서 오차범위 (.00X) 받아서 지도를 계속 이동 시킨다.

- network 통해 서버에 요청 - AsyncTask 사용

