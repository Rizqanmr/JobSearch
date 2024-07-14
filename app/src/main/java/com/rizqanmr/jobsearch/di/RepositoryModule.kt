package com.rizqanmr.jobsearch.di

import com.rizqanmr.jobsearch.data.network.ApiService
import com.rizqanmr.jobsearch.data.repository.JobRepository
import com.rizqanmr.jobsearch.data.repository.JobRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) : JobRepository = JobRepositoryImpl(apiService)
}