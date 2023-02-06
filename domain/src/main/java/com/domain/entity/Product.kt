package com.domain.entity

import com.domain.entity.enums.ProductType

data class Product(
    val ownerId: String = "",
    val title: String = "",
    val description: String = "",
    val value: Double = 0.0,
    val type: ProductType = ProductType.PRODUTO
)
