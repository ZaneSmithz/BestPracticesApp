package com.moneybox.minib.data.balance.model

import com.google.gson.annotations.SerializedName
import com.moneybox.minib.data.balance.model.ProductDetailsResponse

data class ProductResponse(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Product")
    val product: ProductDetailsResponse,
    @SerializedName("Moneybox")
    val moneybox: Float,
    @SerializedName("PlanValue")
    val planValue: Float
)