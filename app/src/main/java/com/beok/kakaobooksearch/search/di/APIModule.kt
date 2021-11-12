package com.beok.kakaobooksearch.search.di

import com.beok.kakaobooksearch.search.data.remote.BookSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class APIModule {

    @Provides
    @Singleton
    fun providesBookSearchAPI(retrofit: Retrofit): BookSearchAPI =
        retrofit.create(BookSearchAPI::class.java)
}
