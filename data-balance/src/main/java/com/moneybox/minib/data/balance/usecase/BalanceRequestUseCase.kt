package com.moneybox.minib.data.balance.usecase

import com.moneybox.minib.data.balance.repository.BalanceRepository
import com.moneybox.minib.data.balance.model.AllProductsResponse
import javax.inject.Inject

interface BalanceRequestUseCase {
    suspend operator fun invoke(token: String): Result<AllProductsResponse>
}
internal class BalanceRequestUseCaseImpl @Inject constructor(
    private val loginRepository: BalanceRepository
): BalanceRequestUseCase {
    override suspend fun invoke(token: String): Result<AllProductsResponse> =
        loginRepository.fetchBalance(token)

}