from rest_framework import generics, status
from rest_framework.parsers import *
from rest_framework.permissions import *
from rest_framework.views import APIView
from rest_framework.response import Response

from .models import Item
from .serializers import *

class ImageAPIView(generics.ListCreateAPIView):
    parser_classes = [MultiPartParser, FormParser]
    queryset = Image.objects.all()
    serializer_class = ImageSerializer


class ItemAPIView(generics.ListCreateAPIView):
    queryset = Item.objects.all()
    serializer_class = ItemSerializer
    permission_classes = [IsAuthenticatedOrReadOnly, ]


class ItemAPIByID(generics.ListAPIView):
    queryset = Item.objects.all()
    serializer_class = ItemSerializerByID
    lookup_field = 'id'

    def get_queryset(self):
        return self.queryset.filter(id=self.kwargs['id'])


