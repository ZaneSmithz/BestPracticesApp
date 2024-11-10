package com.moneybox.minimb.data.login.di

import com.moneybox.minimb.data.login.api.LoginApi
import com.moneybox.minimb.data.login.repository.LoginRepository
import com.moneybox.minimb.data.login.repository.LoginRepositoryImpl
import com.moneybox.minimb.data.login.usecase.LoginRequestUseCase
import com.moneybox.minimb.data.login.usecase.LoginRequestUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository =
        LoginRepositoryImpl(loginApi)

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginRequestUseCase =
        LoginRequestUseCaseImpl(loginRepository)
}