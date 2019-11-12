from django.shortcuts import render
import requests
from bs4 import BeautifulSoup

# Create your views here.
def ascii(request):
    # 입력하고자 하는 text를 받아야함
    # artii에서 제공하는 폰트 중 선택
    url = 'http://artii.herokuapp.com/fonts_list'
    response = requests.get(url)
    fonts_list = response.text.split('\n') # 폰트들 줄바꿈(개행) 문자를 기준으로 나눠서 배열에 넣는다
    context = {
        'fonts': fonts_list
    }
    # html = BeautifulSoup(response.text, 'html.parser')
    return render(request, 'ascii.html', context)

def result(request):
    # ascii에서 입력한 텍스트와 폰트를
    # artii에 보내서 결과값을 받아서 보여줌
    font = request.GET['font']
    text = request.GET['text']
    url = f'http://artii.herokuapp.com/make?font={font}&text={text}'
    response = requests.get(url)
    context = {
        'result': response.text
    }
    return render(request, 'result.html', context)