from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout

# Create your views here.
def signup(request):
    if request.method == "POST":
        form = UserCreationForm(request.POST)
        if form.is_valid(): # 정보가 유효한지
            user = form.save()
            auth_login(request, user) # 로그인
            return redirect('boards:index') # insta 메인페이지로 보내기
        else:
            return render(request, 'signup.html')
    else: 
        # 로그인 되어 있는 상태인지 확인하기
        if request.user.is_authenticated:
            return redirect('boards:index')
        else:
            return render(request, 'signup.html')
        
def login(request):
    if request.method == "POST":
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            auth_login(request, form.get_user())
            return redirect('boards:index')
        else:
            return render(request, 'login.html')
    else:
        if request.user.is_authenticated:
            return redirect('boards:index')
        else:
            return render(request, 'login.html')    

def logout(request):
    auth_logout(request)
    return redirect('boards:index')
