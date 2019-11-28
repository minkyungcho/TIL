# Day17
> 19.11.28 목

### today todo

#### insta 기능 남은것
- 로그인이 되지 않은 상태에서 글을 작성 할 수 없어야 함(Login이 반드시 필요한 기능)
    - **1:N** 글작성, 댓글작성  
    - **M:N** 좋아요

#### Pusher
- 실시간 기능(채팅)
- 외부 API

---
## user 
- 게시글과 댓글을 누가 작성했는지 알아야 한다.
- `article/models.py`에 user를 컬럼으로 넣어주기
~~~ python
# article/models.py
from django.db import models
from django.conf import settings
from django.contirb.auth.models import User

from imagekit.models import ImageSpecField, ProcessedImageField
from imagekit.processors import ResizeToFill, Thumbnail

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE) # 게시글 작성한 user 누군지 알기 위해
    def comments(self):
        return Comment.objects.filter(article_id=self.id)
    def article_images(self):
        return ArticleImages.objects.filter(article_id=self.id)

class ArticleImages(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE) # 부모 모델 삭제되면 자식 모델도 삭제됨
    image = models.ImageField(blank=True)
    image_thumbnail = ImageSpecField(
        source='image',
        processors=[Thumbnail(300,300)],
        format='JPEG',
        options={'quality':90}
    )

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE) # 부모 모델 삭제되면 자식 모델도 삭제됨
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE) # 댓글 단 user 누군지 알기 위해

class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True)
~~~
``
- article/views.py 수정
~~~ python
# article/views.py
def index(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            article = Article()
            article.contents = request.POST['contents']
            # 원본 이미지를 저장
            # article.image = request.FILES["image"]
            # 원본 이미지를 프로세싱 한 이미지를 저장
            # article.image_resized = request.FILES['image']
            article.save()
            for image in request.FILES.getlist("image"):
                ArticleImages.objects.create(article_id=article.id, image=image)
            return redirect('articles')
        else:
            return redirect('accounts:login')
    else:
        articles = Article.objects.all().order_by("created_at").reverse()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)
~~~
- `login.html` 에 a 태그 추가함 회원가입 페이지로 가도록
~~~ html
<!-- login.html -->
<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
<a class="btn btn-lg btn-info btn-block" href="{% url 'accounts:signup' %}">Sign up</a>
~~~

- 게시글 작성자 표시해주기
~~~ html
<!-- index.html -->
...
<div class="text-center">
    <div class="row float-right">
        <span class="muted">created by {{article.user.username}}</span>
    </div>
    <a href="{% url 'edit' article.id %}" class="btn btn-warning"><i class="far fa-edit"></i></a>
    <a href="{% url 'delete' article.id %}" class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
</div>
...
~~~

- 댓글 작성자 표시해주기
~~~ html
<!-- index.html -->
<body>
...
<li class="list-group-item" id="comment-{{comment.id}}">
    <i class="fas fa-comment-dots"></i> 
    <span>{{comment.user.username}} - </span>
    <span class="contents">{{comment.contents}}</span>
...
</body>
~~~
~~~ javascript
$.ajax({
...
success: function(data){
    console.log("성공");
    console.log(data);
    if(data.method == "edit"){
        $('#commentForm-' + data.article_id + ' .formMethod').val("create");
        $('#comment-' + data.comment_id + ' .contents').text(data.comment);
        // console.log($('#comment-' + data.comment_id + ' .editComment').data());
        console.log($('#comment-' + data.comment_id + ' .editComment').data('contents', data.comment));
        // console.log($('#comment-' + data.comment_id + ' .editComment').data());
    } else{
        var comment = `
            <li class="list-group-item" id="comment-${data.comment_id}">
                <i class="fas fa-comment-dots"></i> 
                    <span>${data.username} - </span><span class="contents">${data.comment}</span>
                    <sapn class="float-right">
                        <button data-id="${data.comment_id}" data-contents="${data.comment}" data-article-id="${data.article_id}" class="btn btn-warning editComment"><i class="far fa-edit"></i> 수정</button>
                        <button data-id="${data.comment_id}" class="btn btn-danger deleteComment"><i class="far fa-trash-alt"></i> 삭제</button>
                    </sapn>
            </li>
        `
        // 새로운 댓글 element 를 추가한다.
        $('#comment-list-group-' + data.article_id).append(comment);
    }
},
error: function(data){
    if(data.status == 401){
        location.href = '{% url "accounts:login" %}';
    }
    console.log("실패");
}
...
})
~~~
~~~ python
# article/views.py
def comments(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            contents = request.POST["contents"]
            article_id = request.POST["article_id"]
            if request.POST["form_method"] == "create":
                comment = Comment()
            elif request.POST["form_method"] == "edit":
                comment_id = request.POST["comment_id"]
                comment = Comment.objects.get(id=comment_id)

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
~~~

- 로그인한 사람한테만 게시글 보이게 하기
`index.html`
~~~ html
<div class="text-center">
    <div class="row float-right">
        <span class="muted">created by {{article.user.username}}</span>
    </div>
    {% if article.user_id == user.id %}
        <a href="{% url 'edit' article.id %}" class="btn btn-warning"><i class="far fa-edit"></i></a>
        <a href="{% url 'delete' article.id %}" class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
    {% endif %}
</div>
~~~
- 로그인한 사람한테만 댓글 보이게 하기
`index.html`
~~~ html
<li class="list-group-item" id="comment-{{comment.id}}">
    <i class="fas fa-comment-dots"></i> 
    <span>{{comment.user.username}} - </span><span class="contents">{{comment.contents}}</span>
    <span class="float-right">
        {% if comment.user_id == user.id %}
            <button data-id="{{comment.id}}" data-contents="{{comment.contents}}" data-article-id="{{article.id}}" class="btn btn-warning editComment"><i class="far fa-edit"></i> 수정</button>
            <button data-id="{{comment.id}}" class="btn btn-danger deleteComment"><i class="far fa-trash-alt"></i> 삭제</button>
        {% endif %}
    </span>
</li>
~~~
- url로 게시글 수정/삭제 하는것 막기
~~~ python
# models.py
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE) # 게시글 작성한 user 누군지 알기 위해
    def comments(self):
        return Comment.objects.filter(article_id=self.id)
    def article_images(self):
        return ArticleImages.objects.filter(article_id=self.id)
    def is_permitted(self, target_id):
        return self.user_id == target_id # 같으면 권한 있고 다르면 권한 없음.
~~~
~~~ python
# article/views.py
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
~~~

- 댓글 수정/삭제하는것 막기
~~~ python
# views.py
def comments(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            contents = request.POST["contents"]
            article_id = request.POST["article_id"]
            if request.POST["form_method"] == "create":
                comment = Comment()
            elif request.POST["form_method"] == "edit":
                comment_id = request.POST["comment_id"]
                comment = Comment.objects.get(id=comment_id)
            if comment.user_id != request.user.id:
                return HttpResponse('', status=401)
            else:
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
~~~

## 좋아요
- 게시글에 좋아요 버튼 추가. default는 회색. 좋아요 버튼 누르면 핑크색으로 변하게.
`<button type="button" class="btn btn-secondary"><i class="far fa-heart"></i></button>` 추가
- Article에 좋아요 컬럼 만들기
~~~ python
# `models.py`
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE) # 게시글 작성한 user 누군지 알기 위해
    user_likes = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name="article_likes")
~~~
-  `urls.py`에 추가 `path('articles/likes/', article_views.likes, name="likes"),`
- `views.py`에 추가
~~~ python
def likes(request):
    return ''
~~~

- `index.html`
~~~ html
<button type="button" class="btn btn-secondary likes" data-article-id="{{article.id}}"><i class="far fa-heart"></i></button>
~~~
- js
~~~ javascript
...
// 좋아요 버튼 클릭
$('.likes').on('click', function () {
    var button = $(this);
    console.log("좋아용");
    var article_id = $(this).data('article-id');
    console.log(article_id);
    $.ajax({
        url: '{% url "likes" %}',
        method: 'POST',
        data: {
            article_id: article_id,
            csrfmiddlewaretoken: '{{csrf_token}}'
        },
        success: function(data) {
            console.log("성공");
            console.log(data);
            button.toggleClass(['btn-secondary', 'btn-success']);
        },
        error: function(data) {
            if(data.status == 403){
                location.href = "{% url 'accounts:login' %}"
            }
            console.log("실패");
        }
    })
})
...
~~~
~~~ python
# views.py
def likes(request):
    if request.method == 'POST':
        article_id = request.POST["article_id"]
        print(article_id)
        article = Article.objects.get(id=article_id)
        print(article)
        article.user_likes.add(request.user) # 좋아요
        # article.user_likes.remove(request.user) # 좋아요 취소
        likes_count = len(article.user_likes.all())
        context = {
            'count': likes_count
        }
        return HttpResponse(json.dumps(context), content_type="application/json")
    else:
        return HttpResponse('', status=403)
~~~

- 좋아요 누른 게시글의 버튼이 잘 뜨도록, 좋아요 취소하면 좋아요 count 줄어들게하기.
~~~ html
<!-- 좋아요 버튼 -->
{% if user in article.user_likes.all %}
<button type="button" class="btn btn-success likes" data-article-id="{{article.id}}">
    <i class="far fa-heart"></i>
</button>
{% else %}
<button type="button" class="btn btn-secondary likes" data-article-id="{{article.id}}">
    <i class="far fa-heart"></i>
</button>
{% endif %}
~~~
~~~ python
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
~~~

- 댓글이 안써져요,,
~~~ python
# views.py
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
~~~

## 해쉬태그
- `models.py`에 해쉬태그 추가하기
~~~ python
class HashTag(models.Model):
    tag = models.CharField(max_length=16, unique=True)
    articles = models.ManyToManyField(Article, related_name="tags") 
    # article에서 해쉬태그를 부를때 article.tags로 뽑을 수 있고
    # 
~~~
- `python3 manage.py makemigrations`, `python3 manage.py migtrate`
~~~ python

~~~
---

- `index.html`에 추가 `<input placeholder="Insert HashTag" type="text" class="form-control mt-3" name="hashtag">`
~~~ html
<div class="card-body" style="min-height: 9rem;">
    <p class="card-text">{{article.contents}}</p>
    {% for tag in article.tags.all %}
        <span>#{{tag.tag}}</span>
    {% endfor %}
</div>
~~~
~~~ python
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
            for image in request.FILES.getlist("image"):
                ArticleImages.objects.create(article_id=article.id, image=image)
            return redirect('articles')
        else:
            return redirect('accounts:login')
    else:
        articles = Article.objects.all().order_by("created_at").reverse()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)
~~~

- 해쉬태그 눌렀을때 그 해쉬태그 있는 게시글 보여주기
~~~ python
# hashtag find
query = request.GET["tags"]
articles = HashTag.objets.filter(tag=query)[0].articles
~~~
- 좋아요 몇개인지 표시하기
~~~ html
<button type="button" class="btn btn-success likes" data-article-id="{{article.id}}">
    <i class="far fa-heart"></i> {{len(article.user_likes.all)}}
</button>
~~~

---

