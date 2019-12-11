from django.shortcuts import render, redirect, get_object_or_404
from .models import Article, Comment
from .forms import ArticleForm, CommentForm
from django.contrib.auth.decorators import login_required
from django.views.decorators.http import require_GET, require_POST


# Create your views here.
@login_required
def new_article(request):
    if request.method == 'POST':
        # 사용자가 제출한 후
        # pass
        form = ArticleForm(request.POST) # POST로 온 데이터 알아서 각각 넣어줌
        if form.is_valid(): # 데이터 유효한지 검사
            article = form.save(commit=False)
            article.author = request.user
            article = form.save()
            return redirect('board:article_detail', article.id)
    else: # GET
        form = ArticleForm()
    context = {
        'form': form,
    }
    return render(request, 'board/article_form.html', context)

@require_GET
def article_list(request):
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    # article = Article.objects.get(pk=article_id) # pk=article_id | id=article_id
    comment_form = CommentForm()
    comments = Comment.objects.all()
    if request.user.is_authenticated:
        is_like = article.like_users.filter(id=request.user.id).exists()
    else:
        is_like = None
    context = {
        'article': article,
        'comment_form': comment_form,
        'comments': comments,
        'is_like': is_like
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

@login_required
@require_POST
def new_comment(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    form = CommentForm(request.POST)
    if form.is_valid():
        comment = form.save(commit=False)
        comment.author = request.user
        comment.article = article
        comment.save()
    return redirect('board:article_detail', article.id)

@require_POST
def toggle_like(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    user = request.user
    if article.like_users.filter(id=user.id).exists():
        # if user in article.like_users.all():
        article.like_users.remove(user)
    else:
        article.like_users.add(user)
    return redirect('board:article_detail', article.id)
