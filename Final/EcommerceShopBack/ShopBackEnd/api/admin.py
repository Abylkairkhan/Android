from django.contrib import admin
from .models import *


# Register your models here.

class ItemAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'price', 'category', 'time_create', 'time_update')
    list_display_links = ('id', 'name')
    search_fields = ('id', 'name')


class CategoryAdmin(admin.ModelAdmin):
    list_display = ('id', 'name')
    list_display_links = ('id', 'name')
    search_fields = ('name',)


class ImageAdmin(admin.ModelAdmin):
    list_display = ('id', 'item', 'image')


admin.site.register(Item, ItemAdmin)
admin.site.register(Category, CategoryAdmin)
admin.site.register(Image, ImageAdmin)
