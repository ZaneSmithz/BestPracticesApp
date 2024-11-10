package com.moneybox.minib.data.balance.di

import com.moneybox.minib.data.balance.api.BalanceApi
import com.moneybox.minib.data.balance.repository.BalanceRepository
import com.moneybox.minib.data.balance.repository.BalanceRepositoryImpl
import com.moneybox.minib.data.balance.usecase.BalanceRequestUseCase
import com.moneybox.minib.data.balance.usecase.BalanceRequestUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BalanceModule {
    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): BalanceApi =
        retrofit.create(BalanceApi::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(balanceApi: BalanceApi): BalanceRepository =
        BalanceRepositoryImpl(balanceApi)

    @Provides
    @Singleton
    fun provideLoginUseCase(balanceRepository: BalanceRepository): BalanceRequestUseCase =
        BalanceRequestUseCaseImpl(balanceRepository)
}