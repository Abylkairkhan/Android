from rest_framework import serializers
from .models import *


class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Image
        fields = '__all__'


class CategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = Category
        fields = ('name',)


class ItemSerializer(serializers.ModelSerializer):
    image = serializers.SerializerMethodField()

    def get_image(self, obj):
        first_image = obj.image_set.first()
        if first_image:
            serializer = ImageSerializer(first_image)
            return serializer.data.get('image')
        return None

    class Meta:
        model = Item
        fields = ('name', 'price', 'image', 'category')


class ItemSerializerByID(serializers.ModelSerializer):
    images = serializers.SerializerMethodField()
    category = CategorySerializer()

    def get_images(self, obj):
        all_images = obj.image_set.all()
        if all_images:
            serializer = ImageSerializer(all_images, many=True)
            return serializer.data
        return None

    class Meta:
        model = Item
        fields = ('name', 'description', 'price', 'category', 'images')
