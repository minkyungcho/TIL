import requests
from flask import Flask, request, render_template

app = Flask(__name__)

if __name__ == '__main__':
    app.run(debug=True)

@app.route('/')
def index():
    # request.args -> Dictionary(Immutable)
    # 클라이언트로부터 받은 파라미터를 저장하고 있음
    student = request.args.get('student')
    return{'hello':student}

# @app.route('/<day>')
# def toons(day):
#     return { 'today is': day}

@app.route('/daum_webtoon')
def daum_toon_index():
    #html = '''
    #    <a href="/daum_webtoon/mon">월요일</a>
    #    <a href="/daum_webtoon/tue">화요일</a>
    #    <a href="/daum_webtoon/wed">수요일</a>
    #    <a href="/daum_webtoon/thu">목요일</a>
    #    <a href="/daum_webtoon/fri">금요일</a>
    #    <a href="/daum_webtoon/sat">토요일</a>
    #    <a href="/daum_webtoon/sun">일요일</a>
    #'''
    #return html
    days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
    msg="배고푸댬 히히"
    return render_template('daum_webtoon_list.html', days=days)
    # return render_template('daum_webtoon_list.html', **locals())

@app.route('/daum_webtoon/<day>')
def daum_toon(day):
    # daily_toon_data = {} # 선언
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_from_url(url)
    # daily_toon_data[day] = parse_daum_webtoon_data(data)
    # return daily_toon_data
    return { day: parse_daum_webtoon_data(data)}

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