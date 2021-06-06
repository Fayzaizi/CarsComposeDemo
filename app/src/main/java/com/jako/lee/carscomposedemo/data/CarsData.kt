package com.jako.lee.carscomposedemo.data

import com.jako.lee.carscomposedemo.R

/**
 * Created by Fayzaizi on 2021/6/6.
 */

data class Car(
    val name: String,
    val color: String,
    val image: Int
)

val garage = listOf(
    Car(
        name = "闪电麦昆",
        color = "红色",
        image = R.drawable.ic_cars_logo
    ),
    Car(
        name = "哈德逊医生",
        color = "蓝色",
        image = R.drawable.ic_cars_logo
    ),
    Car(
        name = "板牙",
        color = "绿色",
        image = R.drawable.ic_cars_logo
    ),
    Car(
        name = "黑旋风",
        color = "黑色",
        image = R.drawable.ic_cars_logo
    )
)