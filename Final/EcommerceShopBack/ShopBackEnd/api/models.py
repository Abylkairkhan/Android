from django.db import models


# class Comment(models.Model):
#     user = models.ForeignKey('User', on_delete=models.CASCADE)
#     item = models.ForeignKey('Item', on_delete=models.CASCADE)
#     description = models.TextField(blank=True)
#     time_create = models.DateTimeField(auto_now_add=True)


class Item(models.Model):
    name = models.CharField(max_length=50)
    description = models.TextField(blank=True)
    price = models.IntegerField(null=True)
    category = models.ForeignKey('Category', on_delete=models.PROTECT)
    time_create = models.DateTimeField(auto_now_add=True)
    time_update = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name

    class Meta:
        verbose_name = 'Item'
        verbose_name_plural = 'Items'
        ordering = ['id']


class Category(models.Model):
    name = models.CharField(max_length=50, db_index=True)

    def __str__(self):
        return self.name

    class Meta:
        verbose_name = 'Category'
        verbose_name_plural = 'Categories'
        ordering = ['id']


class Image(models.Model):
    item = models.ForeignKey('Item', on_delete=models.PROTECT)
    image = models.ImageField(upload_to='images/%Y/%m/%d/', blank=True)

    class Meta:
        verbose_name = 'Image'
        verbose_name_plural = 'Images'
        ordering = ['item']
