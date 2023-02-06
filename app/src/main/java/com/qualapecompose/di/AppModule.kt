package com.qualapecompose.di

import com.domain.authentication.FirebaseAuthenticator
import com.authentication.firebaseauth.FirebaseAuthenticatorImpl
import com.domain.repository.FirestoreRepository
import com.data.FirestoreRepositoryImpl
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
    fun provideAuthenticator() : FirebaseAuthenticator =
        FirebaseAuthenticatorImpl()

    @Provides
    @Singleton
    fun provideFirestoreDatabase() : FirestoreRepository =
        FirestoreRepositoryImpl()
}