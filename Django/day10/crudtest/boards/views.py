from django.shortcuts import render, redirect

from .models import Board

# Create your views here.

def index(request):
    boards = Board.objects.all()
    context = {
        'boards': boards
    }
    return render(request, 'index.html', context)
def show(request, id):
    board = Board.objects.get(id=id)
    context = {
        'board': board
    }
    return render(request, 'show.html', context)
def new(request):
    if request.method == 'POST':
        title = request.POST['title']
        creator = request.POST['creator']
        contents = request.POST['contents']
        board = Board.objects.create(title=title, creator=creator, contents=contents)
        return redirect('boards:show', board.id)
    return render(request, 'new.html')
def create(request):
    title = request.GET['title']
    creator = request.GET['creator']
    contents = request.GET['contents']
    board = Board.objects.create(title=title, creator=creator, contents=contents)
    return redirect('boards:show', board.id)
def edit(request, id):
    if request.method == 'POST':
        board = Board.objects.get(id=id)
        title = request.POST['title']
        creator = request.POST['creator']
        contents = request.POST['contents']
        board.title = title
        board.creator = creator
        board.contents = contents
        board.save()
        context = {
            'board': board
        }
        return redirect('boards:show', board.id)
    else:
        board = Board.objects.get(id=id)
        context = {
            'board': board
        }
        return render(request, 'edit.html', context)
def update(request, id):
    board = Board.objects.get(id=id)
    title = request.GET['title']
    creator = request.GET['creator']
    contents = request.GET['contents']
    board.title = title
    board.creator = creator
    board.contents = contents
    board.save()
    context = {
            'board': board
        }
    return redirect('boards:show', board.id)
def delete(request, id):
    board = Board.objects.get(id=id)
    board.delete()
    return redirect('boards:index')
