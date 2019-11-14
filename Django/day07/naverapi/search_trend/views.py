from django.shortcuts import render

import requests, json

# Create your views here.
def search(request):
    # 검색어를 입력하는 곳
    return render(request, 'search.html')

def result(request):
    # 검색어에 대한 트렌드를 받아보는 곳
    # keyword = request.GET['search_keyword']
    start_date = request.GET['search_start_date']
    end_date = request.GET['search_end_date']
    time_unit = request.GET['search_time_unit']
    group_name = request.GET['search_group_name']
    keywords = request.GET['search_keywords'].split(',')
    query = {
        "startDate": start_date,
        "endDate": end_date,
        "timeUnit": time_unit,
        "keywordGroups": [
            {
            "groupName": group_name,
            "keywords": keywords
            }   
        ]
    }
    url = 'https://openapi.naver.com/v1/datalab/search'
    client_id = 'NAVER API'
    client_secret = 'NAVER API'
    
    headers = {
        'Content-Type': 'application/json; charset=UTF-8',
        'X-Naver-Client-Id': client_id,
        'X-Naver-Client-Secret': client_secret
    }
    params = json.dumps(query)
    response = requests.post(url, data=params, headers=headers) # post로 보낼경우 url, data, headers

    result = response.text
    context = {
        'result': result
    }
    return render(request, 'result.html', context)
