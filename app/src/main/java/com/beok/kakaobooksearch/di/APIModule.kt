package com.beok.kakaobooksearch.di

import com.beok.kakaobooksearch.data.remote.BookSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @Singleton
    fun providesBookSearchAPI(retrofit: Retrofit): BookSearchAPI =
        retrofit.create(BookSearchAPI::class.java)
}
