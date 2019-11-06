# 1. 요청을 보내기 위한 requests 모듈을 import 한다
import requests
# 2. url에 주소를 저장한다.
url = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_1"
# 3. 요청을 보내고 응답을 변수에 저장한다.
headers = {
    'User-Agent' : 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36'
}
response = requests.get(url, headers=headers)
# 4. 변수에 저장된 내용을 출력한다.
# print(response.text) # 406 ERROR

# (추가사항) 5. new! 공연, 2019 전국투어, 뮤지컬&연극
# 각 부분을 출력해보기
# [parameter 부분을 잘 확인할 것!]
url1 = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_1"
url2 = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_2"
url3 = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_3"
response1 = requests.get(url1, headers=headers)
response2 = requests.get(url2, headers=headers)
response3 = requests.get(url3, headers=headers)

print(response3.text)