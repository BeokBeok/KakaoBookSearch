package com.beok.kakaobooksearch.di

import com.beok.kakaobooksearch.data.remote.BookSearchRepositoryImpl
import com.beok.kakaobooksearch.domain.repository.BookSearchRepository
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
    abstract fun bindsBookSearchRepository(impl: BookSearchRepositoryImpl): BookSearchRepository
}
