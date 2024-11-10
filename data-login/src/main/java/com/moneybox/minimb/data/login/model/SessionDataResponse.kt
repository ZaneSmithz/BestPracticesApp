package com.moneybox.minimb.data.login.model

import com.google.gson.annotations.SerializedName

data class SessionDataResponse(
    @SerializedName("BearerToken")
    val bearerToken: String
)