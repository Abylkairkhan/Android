package com.example.sneakerstreet.domain.models

import com.google.firebase.firestore.DocumentReference

data class Product(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var category: String? = null,
    var brand: Brand? = null,
    var size: HashMap<String, Int>? = null,
    var image: List<String>? = null,
    var price: Long? = null,
    var color: String? = null,
    var isLiked: Boolean = false
)
