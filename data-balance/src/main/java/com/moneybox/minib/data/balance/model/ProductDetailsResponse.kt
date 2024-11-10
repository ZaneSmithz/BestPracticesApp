package com.moneybox.minib.data.balance.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("FriendlyName")
    val friendlyName: String
)