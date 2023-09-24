package com.example.sneakerstreet.data.firestore

import android.util.Log
import com.example.sneakerstreet.domain.models.Product
import com.example.sneakerstreet.domain.parser.ProductParser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class DataBaseRepository(
    private val db: FirebaseFirestore,
    private val productParser: ProductParser
) {

    suspend fun getProduct(): ArrayList<Product> {
        val docRef = db.collection("Product")
        var result = arrayListOf<Product>()

        try {
            val products =docRef.get().await()
            if (!products.isEmpty) {
                productParser.toProduct(products, result)
            }
        } catch (e: Exception) {
            Log.e("MyLog", "Error getting products: ${e.message}", e)
            throw e // Rethrow the exception or handle it as needed
        }

//            .addOnSuccessListener { products ->
//                if (!products.isEmpty) {
//                    productParser.toProduct(products, result)
//                } else {
//                    Log.d("MyLog", "Document doesn't exist ")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("MyLog", "get failed with ", exception)
//            }
        return result
    }
}