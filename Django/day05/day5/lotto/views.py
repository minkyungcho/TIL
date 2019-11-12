from django.shortcuts import render
import random, requests
from bs4 import BeautifulSoup

# Create your views here.
def lotto(request):
    return render(request, 'lotto.html')

def winning(request):
    # 1. 1~45까지의 숫자 중에 n개의 숫자를 랜덤추출
    # 1-1. 1~45까지의 번호를 가진 배열을 만든다.
    num_list = list(range(1,46))
    num_count = request.GET['count'] # count 값을 stringr으로 인식함
    # 1-2. 해당 배열에서 count만큼의 숫자를 샘플링. 
    result = random.sample(num_list, int(num_count)) # string값 int형으로 변환
    result.sort() # 숫자 정렬
    # 2. 로또 당첨번호 공개 사이트로 가서
    # - 몇회차인지, 언제 당첨번호인지, 1등 금액
    url = 'https://www.dhlottery.co.kr/gameResult.do?method=byWin'
    response = requests.get(url)
    html = BeautifulSoup(response.text, 'html.parser')
    winning_numbers = html.select('div.win span')
    winning_count = 0
    winning_list = []
    for number in winning_numbers:
        # result list 변수에 number가 포함되어 있나요?
        winning_list.append(int(number.text))
        if int(number.text) in result:
            winning_count += 1

    return render(request, 'winning.html', {'result': result, 'winning_list': winning_list, 'winning_count': winning_count})