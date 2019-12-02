from django.shortcuts import render, redirect
from django.http.response import HttpResponse
from .models import Room, Message
import secrets
import json
import pusher

pusher_client = pusher.Pusher(
    app_id='908830',
    key='3996cf098f3e8b647f56',
    secret='99e34b109eea6cbe02eb',
    cluster='ap3',
    ssl=True
)

# Create your views here.
def index(request):
    if request.method == "POST":
        title = request.POST["room-title"]
        max_count = request.POST["room-max-count"]
        code = secrets.token_urlsafe(16)
        room = Room()
        room.title = title
        room.max_connection = max_count
        room.code = code
        room.master_id = request.user.id
        room.save()
        room.users.add(request.user) # class Room에서 users라는 컬럼을 만들어줬음
        current_connection = len(room.users.all())
        
        context = {
            'id': room.id,
            'title': title,
            'max_connection': max_count,
            'current_connection': current_connection,
            'master': room.master.username
        }
        pusher_client.trigger('main', 'create-room', json.dumps(context))
        return HttpResponse('', status=204)
    else:
        rooms = Room.objects.all()
        context = {
            'rooms': rooms
        }
        return render(request, 'index.html', context)

def show(request, room_id):
    if request.user.is_authenticated:
        room = Room.objects.get(id=room_id)
        room.users.add(request.user)
        # 입장/퇴장 메세지 출력하기
        join_message = {
            'user':request.user.username,
            'contents': f'{request.user.username}님이 입장했습니다.'
        }
        pusher_client.trigger(room.code, 'chat', json.dumps(join_message))

        messages = Message.objects.filter(room_id=room.id).order_by("created_at")
        context = {
            'room_id': room_id,
            'current_connection': len(room.users.all())
        }
        pusher_client.trigger('main', 'update-room', json.dumps(context))

        context = {
            'room': room,
            'messages': messages
        }
        return render(request, 'show.html', context)
    else:
        return redirect('accounts:login')

def chat(request, room_id):
    room = Room.objects.get(id=room_id)
    message = Message()
    message.room_id = room.id
    message.contents = request.POST["contents"]
    message.user_id = request.user.id
    message.save()
    context = {
        'user': request.user.username,
        'contents': message.contents
    }
    pusher_client.trigger(room.code, 'chat', json.dumps(context)) # room channel에 있는 사람들이 뵈야함.
    return HttpResponse('', status=204)

def exit(request, room_id):
    room = Room.objects.get(id=room_id)
    room.users.remove(request.user)

    # 입장/퇴장 메세지 출력하기
    exit_message = {
        'user':request.user.username,
        'contents': f'{request.user.username}님이 퇴장했습니다.'
    }
    pusher_client.trigger(room.code, 'chat', json.dumps(exit_message))

    context = {
        'room_id': room_id,
        'current_connection': len(room.users.all())
    }
    pusher_client.trigger('main', 'update-room', json.dumps(context))
    return redirect('boards:index')