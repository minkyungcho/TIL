from django.db import models
from django.conf import settings
from django.contrib.auth.models import User

from imagekit.models import ImageSpecField, ProcessedImageField
from imagekit.processors import ResizeToFill, Thumbnail

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE) # 게시글 작성한 user 누군지 알기 위해
    user_likes = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name="article_likes")
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
    def article_images(self):
        return ArticleImages.objects.filter(article_id=self.id)
    def is_permitted(self, target_id):
        return self.user_id == target_id # 같으면 권한 있고 다르면 권한 없음.
        


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

class HashTag(models.Model):
    tag = models.CharField(max_length=16, unique=True)
    articles = models.ManyToManyField(Article, related_name="tags") 
    # article에서 해쉬태그를 부를때 article.tags로 뽑을 수 있고
    # 


class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True)