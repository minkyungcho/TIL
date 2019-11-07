
import requests
import json

# 1. 평균을 구하시오.
scores = {
    "수학" : 90,
    "영어" : 88,
    "화학" : 92
}
# 배열의 길이를 구하는 함수는 len(배열)
# 반올림 하는 함수는 round(숫자, 소숫점)
for score in scores.values():
    score += score 
avg_score = round(score/len(scores), 1)
print(avg_score)

# 2. 각 학생의 평균 점수와 반 평균을 구하세요
student_scores = {
    "a학생" : {
        "수학" : 95,
        "국어" : 90,
        "생물" : 100
    },
    "b학생" : {
        "수학" : 90,
        "영어" : 90,
        "화학" : 60
    }
}
for stu in student_scores.values():
    print(stu)
    '''
    for stu_score in stu.keys():
        stu_score += stu_score
    if stu=="a학생":
        avg_stu_a = round(student_scores/len(student_scores.keys()))
    else:
        avg_stu_b = round(student_scores/len(student_scores.keys()))
    '''
#print(avg_stu_a)
#print(avg_stu_b)

# 3. 월요일부터

days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'] 
for day in days:
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'

    response = requests.get(url)
    data = response.json() # 응답으로 온 내용을 JSON형식으로 바꿔줌.

    # print(data)
    # print(type(data)) # 형식이 dict인걸 확인

    #for d in data.keys():
    #    print(d)
    #print(data["data"])

    webtoon_data = data["data"]

    toons = []
    for toon in webtoon_data:
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
    print(toons)
    