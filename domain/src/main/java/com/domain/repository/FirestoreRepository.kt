package com.domain.repository

import com.domain.entity.Food
import com.domain.entity.Product
import com.domain.entity.Service
import com.domain.entity.User

interface FirestoreRepository {

    fun saveUserData(
        user: User,
        onComplete: (wasSuccessful: Boolean) -> Unit
    )

    fun fetchUserDataForSessionModule(
        userEmail: String,
        onComplete: (wasSuccessful: Boolean) -> Unit
    )

    fun saveFood(
        newFood: Food,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun saveProduct(
        newProduct: Product,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun saveService(
        newService: Service,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun getFoods(
        onComplete: (foodList: List<Food>?) -> Unit,
    )

    fun getServices(
        onComplete: (serviceList: List<Service>?) -> Unit,
    )

    fun getProducts(
        onComplete: (productList: List<Product>?) -> Unit,
    )
}