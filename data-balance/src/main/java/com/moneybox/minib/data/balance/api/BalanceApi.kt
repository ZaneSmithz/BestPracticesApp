package com.moneybox.minib.data.balance.api

import com.moneybox.minib.data.balance.model.AllProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface BalanceApi {
    @Headers("name: Authorzation")
    @GET("investorproducts")
    suspend fun fetchBalance(@Header("value") token: String): Response<AllProductsResponse>
}