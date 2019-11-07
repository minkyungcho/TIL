import requests
import json
import time

def request_json_data_from_url(url):
    response = requests.get(url)
    data = response.json()
    return data

def parse_naver_map_data(data):
    routes = []
    for route in data["context"]:
        # 출발지
        start = route["start"]

        # 도착지
        goal = route["goal"]
        
        # 경로
        #paths = []
        tmp = {
            "start" : start,
            "goal" : goal

        }
        routes.append(tmp)
    return routes


url = 'https://map.naver.com/v5/api/dir/findpt?start=127.039466,37.501288,placeid=11572240,name=%EB%A9%80%ED%8B%B0%EC%BA%A0%ED%8D%BC%EC%8A%A4%20%EC%97%AD%EC%82%BC&goal=127.0339402,37.4845515,placeid=13479306,name=%EC%96%91%EC%9E%AC%EC%97%AD%203%ED%98%B8%EC%84%A0&crs=EPSG:4326&departureTime=2019-11-07T15:31:25&isStatic=null&mode=TIME&lang=ko'
data = request_json_data_from_url(url)
maps = parse_naver_map_data(data)
print(maps)

#print(data)