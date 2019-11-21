# Day12
> 19.11.21 목

### today todo


---
## static files
> 배포할 때에는 하나의 폴더에 몰아서 보여진다. \
개발환경에서는 우리가 만든 `article`에서 관리하게 됨.
`article/static`, `article/static/article`, `article/static/article/images` 폴더 생성
~~~ python 
# settings.py

~~~
- images 폴더에 이미지 추가하고 게시글에 이미지 띄우기
`index.html`에 추가
~~~ html
{% extends 'base.html' %}
{% load static %}

{% block stylesheet %}
<style>
.container {
    padding: 10rem !important;
    padding: 10rem !important;
}
</style>
{% endblock %}
~~~
- img 업로드 할 수 있는 입력창? 만들기 && form 태그에 `enctype="multipart/form-data"` 속성 추가
~~~ html
<!-- index.html -->
<form action="{% url 'articles' %}" method="POST" enctype="multipart/form-data">
...
<div class="card-body">
                <textarea class="form-control" rows="5" name="contents"></textarea>
                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                    </div>
                    <div class="custom-file">
                        <input name="image" type="file" class="custom-file-input" id="inputGroupFile01"
                            aria-describedby="inputGroupFileAddon01">
                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                    </div>
                </div>
            </div>
~~~
- model에 이미지 추가
~~~ python
# mpdels.py
class Article(models.Model):
    ...
    image = models.ImageField(blank=True)
~~~
`python3 -mpip install Pillow`
`python3 manage.py makemigrations`
`manage.py migrate`
~~~ python
# views.py
def index(request):
    if request.method == "POST":
        article = Article()
        article.contents = request.POST['contents']
        # 
        article.image = request.FILES["image"]
        article.save()
        return redirect('articles')
    ...
~~~
- 사진 등록하고 게시글 작성하기 하면 사진이 `instagram` 폴더 내에 생성된것 확인.

- 이미지 저장경로 바꿔주기 -> `instagram/media` 폴더 생성되고 거기에 이미지 저장됨.
~~~ python
# settings.py
MEDIA_ROOT = os.path.join(BASE_DIR, 'media')
MEDIA_URL = '/media/'
~~~
~~~ python
# urls.py
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static
urlpatterns = [
    path('admin/', admin.site.urls),
    path('insta/', include('article.urls'))
]
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
~~~
~~~ html
<!-- index.html -->
<img src="{{article.image.url}}" alt="{{article.image}}" class="card-img-top">
~~~

- model
~~~ python
# models.py
from imagekit.models import ImageSpecField, ProcessedImageField
from imagekit.processors import ResizeToFill
class Article(models.Model):
    ...
    image_resized = ProcessedImageField(
        source='image',
        upload_to='articles/images',
        processors=[ResizeToFill(200,300)],
        format='JPEG',
        options={'quality':90}

~~~

~~~ python
INSTALLED_APPS = [
    'article',
    'imagekit',
    ...
]
~~~
DB 날리기 : 


...

~~~ python
# models.py
from django.db import models
from imagekit.models import ImageSpecField, ProcessedImageField
from imagekit.processors import ResizeToFill, Thumbnail
# Create your models here.
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    # image = models.ImageField(blank=True)
    # image_resized = ProcessedImageField(
    #     # source='image',
    #     upload_to='articles/images',
    #     processors=[ResizeToFill(200,300)],
    #     format='JPEG',
    #     options={'quality':90}
    # )
    def comments(self):
        return Comment.objects.filter(article_id=self.id)

class ArticleImages(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    image = models.ImageField(blank=True)
    image_thumbnail = ImageSpecField(
        source='image',
        processors=[Thumbnail(300,300)],
        format='JPEG',
        options={'quality':90}
    )
~~~
~~~ python
# views.py
def index(request):
    if request.method == "POST":
        article = Article()
        article.contents = request.POST['contents']
        # 원본 이미지를 저장
        # article.image = request.FILES["image"]
        # 원본 이미지를 프로세싱 한 이미지를 저장
        # article.image_resized = request.FILES['image']
        article.save()
        return redirect('articles')
    else:
        articles = Article.objects.all().order_by("created_at").reverse()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)
~~~
~~~ html
<!-- index.html -->
...
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
            {% if article.image %}
            <div id="carouselExample" class="carlousel slide" data-ride="carlousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="{{article.image_thumbnail.url}}" alt="..." class="d-block w-100">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            {% endif %}
            <div class="card-body" style="min-height: 9rem;">
                <p class="card-text">{{article.contents}}</p>
            </div>
    ...
~~~

- 파일 여러개 업로드
`<input multiple name="image" type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">`

~~~ html
<!-- index.html -->
...
<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12">
        {% if article.article_images %}
            <div id="carouselExample" class="carlousel slide" data-ride="carlousel">
                <div class="carousel-inner">
                {% for image in article.article_images %}
                    
                    <div class="carousel-item active">
                        <img src="{{image.image_thumbnail.url}}" alt="..." class="d-block w-100">
                    </div>
                {% endfor %}
            </div>
    ...
~~~
active 1개만 있어야 움직인다.
~~~ html
{% for image in article.article_images %}
<div class="carousel-item {% if forloop.counter == 1 %}active{% endif %}">
    <img src="{{image.image_thumbnail.url}}" alt="..." class="d-block w-100">
</div>
{% endfor %}
~~~
---
### tip
jQuery vs 