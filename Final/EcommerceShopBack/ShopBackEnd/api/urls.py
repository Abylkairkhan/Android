from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from .views import *

urlpatterns = [
    path('items/', ItemAPIView.as_view()),
    path('items/<int:id>/', ItemAPIByID.as_view()),
    path('image/', ImageAPIView.as_view())
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
