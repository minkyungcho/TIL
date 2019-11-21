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
    def article_images(self):
        return ArticleImages.objects.filter(article_id=self.id)

class ArticleImages(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
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
    article = models.ForeignKey(Article, on_delete=models.CASCADE)