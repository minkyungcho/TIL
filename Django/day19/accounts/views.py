from django.shortcuts import render, redirect
from django.contrib.auth import login as auth_login, logout as auth_logout
from .forms import CustomAuthenticationForm, CustomUserCreationForm

def signup(request):
    if not request.user.is_authenticated:
        if request.method == 'POST':
            form = CustomUserCreationForm(request.POST)
            if form.is_valid():
                user = form.save()
                auth_login(request, user)
                return redirect('board:article_list')
        else: # GET
            form = CustomUserCreationForm()
        context = {
            'form': form,
        }
        return render(request, 'accounts/signup.html', context)
    else:
        return redirect('board:article_list')

def login(request):
    if request.user.is_authenticated:
        return redirect('board:article_list')
    else:
        if request.method == 'POST':
            form = CustomAuthenticationForm(request, request.POST)
            if form.is_valid():
                user = form.get_user()
                auth_login(request, user)
                return redirect(request.GET.get('next') or 'board:article_list')
        else: # GET
            form = CustomAuthenticationForm()
        context = {
            'form': form,
        }
        return render(request, 'accounts/login.html', context)

def logout(request):
    auth_logout(request)
    return redirect('board:article_list')

