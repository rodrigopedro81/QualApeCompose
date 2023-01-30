package com.qualapecompose.di

import com.authentication.FirebaseAuthenticator
import com.authentication.FirebaseAuthenticatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthenticator() : FirebaseAuthenticator = FirebaseAuthenticatorImpl()
}