from django.urls import path
from . import views as article_views

urlpatterns = [
    path('', article_views.index, name="articles"),
    # JS 
    path('js-test', article_views.js_test),
    # JQ
    path('jq-test', article_views.jq_test),
    path('jq-test/boards/', article_views.submit_boards, name="submit_boards"),
    path('jq-test/boards/edit', article_views.edit_boards, name="edit_boards"),
    path('jq-test/boards/delete', article_views.delete_boards, name="delete_boards"),
    
    path('articles/<int:article_id>/delete', article_views.delete, name="delete"),
    path('articles/<int:article_id>/edit', article_views.edit, name="edit"),

    path('comments/', article_views.submit_comments, name="submit_comments"),
    # path('comments/<int:comment_id>/delete', article_views.delete_comments, name="delete_comments"),
    path('comments/delete', article_views.delete_comments, name="delete_comments"),
    path('comments/<int:comment_id>/edit', article_views.edit_comments, name="edit_comments"),
]