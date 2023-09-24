package com.example.sneakerstreet.domain.parser

import com.example.sneakerstreet.domain.models.Product
import com.google.firebase.firestore.QuerySnapshot

class ProductParser {

    fun toProduct(productList: QuerySnapshot, returnList: ArrayList<Product>) {
        productList.forEach { product ->
            returnList.add(
                Product(
                    id = product.id,
                    name = product["name"].toString(),
                    description = product["description"].toString(),
                    category = product["category"].toString(),
                    image = product["image"] as List<String>,
                    price = product["price"] as Long,
                    color = product["color"].toString()
                )
            )
        }
    }
}