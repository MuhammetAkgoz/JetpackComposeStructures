package com.example.data.di

import com.example.data.repository.DefaultRickAndMortyRepository
import com.example.domain.repository.RickAndMortyRepository
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
    abstract fun bindRickAndMortyRepository(
        impl: DefaultRickAndMortyRepository
    ): RickAndMortyRepository
}
