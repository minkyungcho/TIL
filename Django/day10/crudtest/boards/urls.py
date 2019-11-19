from django.contrib import admin
from django.urls import path, include

from . import views

app_name = 'boards'

urlpatterns = [
    path('', views.index, name='index'),
    path('<int:id>/', views.show, name='show'),
    path('new/', views.new, name='new'),
    # path('create/', views.create, name='create'),
    path('<int:id>/edit/', views.edit, name='edit'),
    # path('<int:id>/update/', views.update, name='update'),
    path('<int:id>/delete/', views.delete, name='delete'),

]