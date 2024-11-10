package com.moneybox.minib.data.balance.model

import com.google.gson.annotations.SerializedName

data class AllProductsResponse(
    @SerializedName("TotalPlanValue")
    val totalPlanValue: Float,
    @SerializedName("TotalEarnings")
    val totalEarnings: Float,
    @SerializedName("TotalEarningsAsPercentage")
    val totalEarningsAsPercentage: Float? = null,
    @SerializedName("ProductResponses")
    val products: List<ProductResponse>,
)