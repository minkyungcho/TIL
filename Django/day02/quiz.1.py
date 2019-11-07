import requests
import json
import time

# 3. 월요일부터
'''
days = {
    'mon': '', 
    'tue': '', 
    'wed': '', 
    'thu': '',
    'fri': '', 
    'sat': '', 
    'sun': ''
}
'''
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

days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'] 
# data 담을 dictionary 생성
daily_toon_data = {}
for day in days:
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_from_url(url)
    daily_toon_data[day] = parse_daum_webtoon_data(data)
    print(daily_toon_data[day])
    # 일시정지
    time.sleep(3)

