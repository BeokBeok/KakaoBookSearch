package com.beok.search.di

import com.beok.search.domain.usecase.BookTitleSearchUseCase
import com.beok.search.domain.usecase.BookTitleSearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsBookTitleSearchUseCase(
        impl: BookTitleSearchUseCaseImpl
    ): BookTitleSearchUseCase
}

