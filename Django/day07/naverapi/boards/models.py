from django.db import models

# Create your models here.
class Board(models.Model):
    title = models.CharField(max_length=30)
    contents = models.TextField()
    creator = models.CharField(max_length=10, null=True)

class Person(models.Model):
    name = models.CharField(max_length=10)
    age = models.IntegerField()
    birth_day = models.CharField(max_length=8)