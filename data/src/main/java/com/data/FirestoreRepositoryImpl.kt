package com.data

import com.domain.entity.Food
import com.domain.entity.Product
import com.domain.entity.Service
import com.domain.entity.User
import com.domain.util.Constants.ATTRIBUTE_FOOD_ID_LIST
import com.domain.util.Constants.ATTRIBUTE_PRODUCT_ID_LIST
import com.domain.util.Constants.ATTRIBUTE_SERVICE_ID_LIST
import com.domain.util.Constants.FOOD_COLLECTION
import com.domain.util.Constants.PRODUCT_COLLECTION
import com.domain.util.Constants.SERVICE_COLLECTION
import com.domain.util.Constants.TEST_FOOD_COLLECTION
import com.domain.util.Constants.TEST_PRODUCT_COLLECTION
import com.domain.util.Constants.TEST_SERVICE_COLLECTION
import com.domain.util.Constants.TEST_USER_COLLECTION
import com.domain.util.Constants.USER_COLLECTION
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class FirestoreRepositoryImpl : FirestoreRepository {

    private fun getFirestoreInstance() = Firebase.firestore

    override fun saveUserData(
        user: User,
        onComplete: (wasSuccessful: Boolean) -> Unit
    ) {
        getUserCollection().document(user.email).set(user).addOnCompleteListener {
            onComplete.invoke(it.isSuccessful)
        }
    }

    override fun fetchUserDataForSessionModule(
        userEmail: String,
        onComplete: (wasSuccessful: Boolean) -> Unit
    ) {
        getUserCollection().document(userEmail).get().addOnCompleteListener {
            onComplete.invoke(it.isSuccessful)
            if (it.isSuccessful) {
                val user = it.result.toObject(User::class.java)
                User.Session.loggedUser = user ?: return@addOnCompleteListener
            }
        }
    }

    override fun saveFood(
        newFood: Food,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val newFoodDocumentReference = getFoodCollection().document()
        val userDocumentReference = getUserCollection().document(User.Session.loggedUser.email)
        val newFoodId = newFoodDocumentReference.id
        val newUserFoodIdList = User.Session.loggedUser.foodIdList + newFoodId
        newFoodDocumentReference.set(newFood).addOnSuccessListener {
            userDocumentReference.update(ATTRIBUTE_FOOD_ID_LIST, newUserFoodIdList)
                .addOnSuccessListener {
                    User.Session.loggedUser.foodIdList = newUserFoodIdList
                    onSuccess.invoke()
                }
                .addOnFailureListener {
                    onFailure.invoke()
                }
        }.addOnFailureListener {
            onFailure.invoke()
        }
    }

    override fun saveProduct(
        newProduct: Product,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val newProductDocumentReference = getProductCollection().document()
        val userDocumentReference = getUserCollection().document(User.Session.loggedUser.email)
        val newProductId = newProductDocumentReference.id
        val newUserProductIdList = User.Session.loggedUser.productIdList + newProductId
        newProductDocumentReference.set(newProduct).addOnSuccessListener {
            userDocumentReference.update(ATTRIBUTE_PRODUCT_ID_LIST, newUserProductIdList)
                .addOnSuccessListener {
                    User.Session.loggedUser.productIdList = newUserProductIdList
                    onSuccess.invoke()
                }
                .addOnFailureListener {
                    onFailure.invoke()
                }
        }.addOnFailureListener {
            onFailure.invoke()
        }
    }

    override fun saveService(
        newService: Service,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val newServiceDocumentReference = getServiceCollection().document()
        val userDocumentReference = getUserCollection().document(User.Session.loggedUser.email)
        val newServiceId = newServiceDocumentReference.id
        val newUserServiceIdList = User.Session.loggedUser.serviceIdList + newServiceId
        newServiceDocumentReference.set(newService).addOnSuccessListener {
            userDocumentReference.update(ATTRIBUTE_SERVICE_ID_LIST, newUserServiceIdList)
                .addOnSuccessListener {
                    User.Session.loggedUser.serviceIdList = newUserServiceIdList
                    onSuccess.invoke()
                }
                .addOnFailureListener {
                    onFailure.invoke()
                }
        }.addOnFailureListener {
            onFailure.invoke()
        }
    }

    override fun getFoods(
        onComplete: (foodList: List<Food>?) -> Unit
    ) {
        getFoodCollection().get().addOnCompleteListener {
            onComplete.invoke(it.result.documents.mapNotNull { it.toObject<Food>() })
        }
    }

    override fun getServices(
        onComplete: (serviceList: List<Service>?) -> Unit
    ) {
        getServiceCollection().get().addOnCompleteListener {
            onComplete.invoke(it.result.documents.mapNotNull { it.toObject<Service>() })
        }
    }

    override fun getProducts(
        onComplete: (productList: List<Product>?) -> Unit
    ) {
        getProductCollection().get().addOnCompleteListener {
            onComplete.invoke(it.result.documents.mapNotNull { it.toObject<Product>() })
        }
    }

    private fun getUserCollection(): CollectionReference = getFirestoreInstance().collection(
        if (BuildConfig.DEBUG) TEST_USER_COLLECTION else USER_COLLECTION
    )

    private fun getFoodCollection(): CollectionReference = getFirestoreInstance().collection(
        if (BuildConfig.DEBUG) TEST_FOOD_COLLECTION else FOOD_COLLECTION
    )

    private fun getServiceCollection(): CollectionReference = getFirestoreInstance().collection(
        if (BuildConfig.DEBUG) TEST_SERVICE_COLLECTION else SERVICE_COLLECTION
    )

    private fun getProductCollection(): CollectionReference = getFirestoreInstance().collection(
        if (BuildConfig.DEBUG) TEST_PRODUCT_COLLECTION else PRODUCT_COLLECTION
    )
}