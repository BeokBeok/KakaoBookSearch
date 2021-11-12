package com.beok.search.di

import com.beok.search.data.remote.BookSearchRepositoryImpl
import com.beok.search.domain.repository.BookSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsBookSearchRepository(impl: BookSearchRepositoryImpl): BookSearchRepository
}
