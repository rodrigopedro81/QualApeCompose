package com.qualapecompose.firebase.firestore

import com.domain.entity.Food
import com.domain.entity.Product
import com.domain.entity.Service
import com.domain.entity.UserInfo

interface FirestoreRepository {

    fun saveUserData(
        userInfo: com.domain.entity.UserInfo,
        onComplete: (wasSuccessful: Boolean) -> Unit
    )

    fun fetchUserDataForSessionModule(
        userEmail: String,
        onComplete: (wasSuccessful: Boolean) -> Unit
    )

    fun saveFood(
        newFood: com.domain.entity.Food,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun saveProduct(
        newProduct: com.domain.entity.Product,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun saveService(
        newService: com.domain.entity.Service,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun getFoods(
        onComplete: (foodList: List<com.domain.entity.Food>?) -> Unit,
    )

    fun getServices(
        onComplete: (serviceList: List<com.domain.entity.Service>?) -> Unit,
    )

    fun getProducts(
        onComplete: (productList: List<com.domain.entity.Product>?) -> Unit,
    )
}