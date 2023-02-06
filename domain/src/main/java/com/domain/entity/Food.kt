package com.domain.entity

import com.domain.entity.enums.FoodType

data class Food(
    val ownerId: String = "",
    val title: String = "",
    val description: String = "",
    val value: Double = 0.0,
    val type: FoodType = FoodType.COMIDA
)

