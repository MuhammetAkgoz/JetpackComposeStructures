package com.example.data.di

import com.example.data.repository.EmailRepositoryImpl
import com.example.domain.repository.EmailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEmailRepository(emailRepositoryImpl: EmailRepositoryImpl): EmailRepository
}
