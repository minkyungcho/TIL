from django.shortcuts import render, redirect
from django.http.response import HttpResponse
from .models import Article, Comment, ArticleImages, Board, HashTag
import json

# Create your views here.
def js_test(request):
    return render(request, 'js_test.html')

def jq_test(request):
    boards = Board.objects.all().order_by("created_at").reverse()
    context = {
        'boards': boards
    }
    return render(request, 'jq_test.html', context)

def submit_boards(request):
    if request.method == "POST":
        contents = request.POST["board"]
        board = Board.objects.create(contents=contents)
        context = {
            'board': board
        }
        return render(request, 'empty.html', context)

def delete_boards(request):
    if request.method == "POST":
        id = request.POST["board_id"]
        board = Board.objects.get(id=id)
        board.delete()
        context = {
            'board_id':id
        }
        return HttpResponse(json.dumps(context), content_type="appication/json")

def edit_boards(request):
    if request.method == "POST":
        id = request.POST["board_id"]
        contents = request.POST["contents"]
        board = Board.objects.get(id=id)
        board.contents = contents
        board.save()
        return HttpResponse('', status=204) # 서버가 데이터를 받지 않고 성공.
        
    

def index(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            article = Article()
            article.contents = request.POST['contents']
            article.user_id = request.user.id
            article.save()

            tags = request.POST["hashtag"]
            for tag in tags.split(","):
                tag = tag.strip()
                if not HashTag.objects.filter(tag=tag):
                    # 태그가 없으면 새로운 태그 생성
                    tag = HashTag.objects.create(tag=tag)
                else:
                    # 태그가 있던거였으면 있던 태그에 추가
                    tag = HashTag.objects.filter(tag=tag)[0]
                article.tags.add(tag)

            # 원본 이미지를 저장
            # article.image = request.FILES["image"]
            # 원본 이미지를 프로세싱 한 이미지를 저장
            # article.image_resized = request.FILES['image']
            for image in request.FILES.getlist("image"):
                ArticleImages.objects.create(article_id=article.id, image=image)
            return redirect('articles')
        else:
            return redirect('accounts:login')
    else:
        # hashtag find
        # query = request.GET["tags"]
        # articles = HashTag.objets.filter(tag=query)[0].articles

        articles = Article.objects.all().order_by("created_at").reverse()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)

def delete(request, article_id):
    article = Article.objects.get(id=article_id)
    if article.is_permitted(request.user,id):
        article.delete()
        return redirect('articles')
    else:
        return redirect('articles')

def edit(request, article_id):
    article = Article.objects.get(id=article_id)
    if article.is_permitted(request.user.id):
        if request.method == "POST":
            article.contents = request.POST['contents']
            article.save()
            return redirect('articles')
        else:
            context = {
                'article': article
            }
            return render(request, 'article/edit.html', context)
    return redirect('articles')

# def comments(request):
#     if request.method == "POST":
#         contents = request.POST['contents']
#         article_id = request.POST['article_id']
#         comment = Comment()
#         comment.contents = contents
#         comment.article_id = article_id
#         comment.save()
#         return redirect('articles')
        
# def delete_comment(request, comment_id):
#     comment = Comment.objects.get(id=comment_id)
#     comment.delete()
#     return redirect('articles')

# def edit_comment(request, comment_id):
#     comment = Comment.objects.get(id=comment_id)
#     if request.method == "POST":
#         comment.contents = request.POST['contents']
#         comment.save()
#         return redirect('articles')
#     else:
#         context = {
#             'comment': comment
#         }
#         return render(request, 'comment/edit.html', context)

def comments(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            contents = request.POST["contents"]
            article_id = request.POST["article_id"]
            if request.POST["form_method"] == "create":
                comment = Comment()
                comment.user_id = request.user.id
            elif request.POST["form_method"] == "edit":
                comment_id = request.POST["comment_id"]
                comment = Comment.objects.get(id=comment_id)
                if comment.user_id != request.user.id:
                    return HttpResponse('', status=401)
            comment.contents = contents
            comment.article_id = article_id
            comment.user_id = request.user.id
            comment.save()
            context = {
                'method': request.POST["form_method"],
                'comment': comment.contents,
                'username': comment.user.username,
                'comment_id': comment.id,
                'article_id': comment.article_id
            }
            return HttpResponse(json.dumps(context), content_type='application/json')
        else:
            context = {
                'status': 401,
                'message': '로그인이 필요합니다.'
            }
            return HttpResponse(json.dumps(context), status=401, content_type="application/json")

def delete_comments(request):
    if request.method == "POST":
        comment_id = request.POST["comment_id"]
        comment = Comment.objects.get(id=comment_id)
        if comment.user_id == request.user.id:
            comment.delete()
            return HttpResponse('', status=204)
        else:
            return HttpResponse('', status=401)
        

def edit_comments(request):
    return ''

def likes(request):
    if request.user.is_authenticated and request.method == 'POST':
        article_id = request.POST["article_id"]
        article = Article.objects.get(id=article_id)
        if request.user in article.user_likes.all(): # 게시글에 좋아요 한 모든 사람들 중에 현재 로그인 한 사람이 있는지 없는지 확인
            article.user_likes.remove(request.user) # 좋아요 취소
        else:
            article.user_likes.add(request.user) # 좋아요
        likes_count = len(article.user_likes.all())
        context = {
            'count': likes_count
        }
        return HttpResponse(json.dumps(context), content_type="application/json")
    else:
        return HttpResponse('', status=403)