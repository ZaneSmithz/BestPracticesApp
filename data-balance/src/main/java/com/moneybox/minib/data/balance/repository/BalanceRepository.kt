package com.moneybox.minib.data.balance.repository

import com.moneybox.minib.data.balance.api.BalanceApi
import com.moneybox.minib.data.balance.model.AllProductsResponse
import retrofit2.HttpException
import javax.inject.Inject

interface BalanceRepository {
    suspend fun fetchBalance(token: String): Result<AllProductsResponse>
}

internal class BalanceRepositoryImpl @Inject constructor(
    private val balanceApi: BalanceApi
): BalanceRepository {
    override suspend fun fetchBalance(token: String): Result<AllProductsResponse> {
        try {
            val result = balanceApi.fetchBalance(token = "Token $token")
            return if (result.isSuccessful) {
                Result.success(result.body() ?: throw RuntimeException(""))
            } else {
                Result.failure(HttpException(result))
            }
        } catch (e: HttpException) {
            return Result.failure(Exception(""))
        }
    }
}