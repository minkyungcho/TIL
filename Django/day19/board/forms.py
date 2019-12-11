from django import forms
# from django.db import models
from .models import Article, Comment

class ArticleForm(forms.ModelForm):
    # ModelForm, Meta, model, fields 그대로 써야함.
    
    # 미세 조정 (브라우저단에서 제한하는것. 속성 지우면 서버로 넘어감.)
    title = forms.CharField(min_length=2, strip=True) # 최소 문자수 설정, 앞뒤 띄어쓰기한것 자르기(왜 필요? 서버단에서는 1바이트도 돈이다.)
    email = forms.EmailField()
    keyword = forms.CharField(min_length=1, max_length=10)
    class Meta:
        model = Article
        # fields = '__all__' # 모든 컬럼
        # 원하는 컬럼만 표현하고 싶을때
        # fields = ('title', 'content')
        # 
        ###########################3
        # date만 빼고 싶을때 
        exclude = ['datetime','author','like_users']

class CommentForm(forms.ModelForm):
    content = forms.CharField(min_length=1, max_length=200)
    class Meta:
        model = Comment
        fields = ['content', ]