package com.moneybox.minib.data.balance.api

import com.moneybox.minib.data.balance.model.AllProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BalanceApi {
    @GET("investorproducts")
    suspend fun fetchBalance(@Header("Authorization") token: String): Response<AllProductsResponse>
}