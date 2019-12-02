from django.test import TestCase

# Create your tests here.
from django.shortcuts import render, HttpResponse
from .models import Room, Message
import secrets
import json
import pusher

pusher_client = pusher.Pusher(
  app_id='908830',
  key='3996cf098f3e8b647f56',
  secret='99e34b109eea6cbe02eb', # 보안에 신경써야합니다!
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
        room.master_id = 1
        room.save()
        
        context = {
            'id': room.id,
            'title': title,
            'max_count': max_count,
            'master': room.master.username
        }
        # pusher_client.trigger('my-channel', 'my-event', {'message': 'hello world'}) # 채널 이름, 이벤트 이름, 무엇을!?
        # pusher 서버로 요청보내는곳
        pusher_client.trigger('main', 'create-room', json.dumps(context)) # 채널 이름, 이벤트 이름, 무엇을!?
        return HttpResponse('', status=204)
    else:
        rooms = Room.objects.all()
        context = {
            'rooms': rooms
        }
        return render(request, 'index.html', context)

def show(request, room_id):
    room = Room.objects.get(id=room_id)
    messages = Message.objects.filter(room_id=room.id).order_by("created_at")
    context = {
        'room': room,
        'messages': messages
    }
    return render(request, 'show.html', context)