from django.shortcuts import render, redirect, get_object_or_404
from .models import Article, Comment
from .forms import ArticleForm
from django.contrib.auth.decorators import login_required

# Create your views here.
@login_required
def new_article(request):
    if request.method == 'POST':
        # 사용자가 제출한 후
        # pass
        form = ArticleForm(request.POST) # POST로 온 데이터 알아서 각각 넣어줌
        print('Data 넘어옴')
        print(form)
        if form.is_valid(): # 데이터 유효한지 검사
            article = form.save()
            return redirect('board:article_detail', article.id)
    else: # GET
        form = ArticleForm()
    context = {
        'form': form,
    }
    print(form.errors)
    return render(request, 'board/article_form.html', context)

def article_list(request):
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    # article = Article.objects.get(pk=article_id) # pk=article_id | id=article_id
    context = {
        'article': article
    }
    return render(request, 'board/article_detail.html', context)

@login_required
def edit_article(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    if request.method == 'POST':
        form = ArticleForm(request.POST, instance=article) # 
        if form.is_valid(): # 데이터 유효한지 검사
            article = form.save()
            return redirect('board:article_detail', article.id)
    else:
        form = ArticleForm(instance=article)
    context = {
        'form': form,
    }
    return render(request, 'board/article_form.html', context)
