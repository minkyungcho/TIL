from django.urls import path
from . import views

app_name = 'board'

urlpatterns = [
    path('', views.article_list, name="article_list"),
    path('<int:article_id>/', views.article_detial, name="article_detail"),
]