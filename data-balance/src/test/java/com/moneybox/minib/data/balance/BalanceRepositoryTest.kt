package com.moneybox.minib.data.balance

import com.moneybox.minib.data.balance.api.BalanceApi
import com.moneybox.minib.data.balance.model.AllProductsResponse
import com.moneybox.minib.data.balance.repository.BalanceRepository
import com.moneybox.minib.data.balance.repository.BalanceRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Response

class BalanceRepositoryTest {
    private val balanceApi: BalanceApi = mockk(relaxed = true)

    private lateinit var balanceRepository: BalanceRepository

    private val productsResponse = AllProductsResponse(
        products = listOf(),
        totalEarnings = 0.0f,
        totalPlanValue = 5.0f
    )
    @Before
    fun setUp() {
        balanceRepository = BalanceRepositoryImpl(balanceApi)
    }

    @Test
    fun `test fetch balance when result is success` () = runTest {
        coEvery { balanceApi.fetchBalance("Token token") } returns Response.success(productsResponse)
        val result = balanceRepository.fetchBalance("token")

        assertEquals(productsResponse.totalPlanValue, result.getOrNull()?.totalPlanValue)
        assertEquals(true, result.isSuccess)
        assertEquals(false, result.isFailure)
    }

    @Test
    fun `test fetch balance when result is failure` () = runTest {
        coEvery { balanceApi.fetchBalance("token") } returns Response.error(400, ResponseBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"key\":[\"somestuff\"]}"
        ))

        val result = balanceRepository.fetchBalance("token")

        assertEquals(null, result.getOrNull())
        assertEquals(false, result.isSuccess)
        assertEquals(true, result.isFailure)
    }
}