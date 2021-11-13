package com.beok.kakaobooksearch.di

import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsBookTitleSearchUseCase(
        impl: BookTitleSearchUseCaseImpl
    ): BookTitleSearchUseCase
}

