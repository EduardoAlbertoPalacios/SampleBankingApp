package com.example.storidemoapp.di

import com.example.data.datasource.auth.AuthDataSource
import com.example.data.datasource.auth.AuthDataSourceImpl
import com.example.data.datasource.movements.MovementsDataSource
import com.example.data.datasource.movements.MovementsDataSourceImpl
import com.example.data.repository.AuthDataRepository
import com.example.data.repository.MovementsDataRepository
import com.example.domain.entities.AuthEntity
import com.example.domain.entities.MovementsEntity
import com.example.domain.entities.UserEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MovementsRepository
import com.example.domain.usecase.UseCase
import com.example.domain.usecase.auth.AuthDataUseCase
import com.example.domain.usecase.movements.GetMovementsDataUseCase
import com.example.domain.usecase.register.RegisterDataUseCase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //DataSources
    @Provides
    @Singleton
    fun providesAuthDataSource(): AuthDataSource = AuthDataSourceImpl(db = Firebase.firestore)

    @Provides
    @Singleton
    fun providesMovementsDataSource(): MovementsDataSource =
        MovementsDataSourceImpl(db = Firebase.firestore)

    //Repositories
    @Provides
    @Singleton
    fun providesUserDataRepository(dataSource: AuthDataSource): AuthRepository =
        AuthDataRepository(client = dataSource, coroutineDispatcher = Dispatchers.IO)

    @Provides
    @Singleton
    fun providesMovementsDataRepository(dataSource: MovementsDataSource): MovementsRepository =
        MovementsDataRepository(client = dataSource, coroutineDispatcher = Dispatchers.IO)

    //UseCases
    @Provides
    @Singleton
    fun provideRegisterUserUseCase(repository: AuthRepository): UseCase<UserEntity, Result<String>> =
        RegisterDataUseCase(repository)


    @Provides
    @Singleton
    fun provideAuthenticationUserUseCase(repository: AuthRepository): UseCase<AuthEntity, Result<String>> =
        AuthDataUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllMovementsUseCase(repository: MovementsRepository): UseCase<Any, List<MovementsEntity>> =
        GetMovementsDataUseCase(repository)
}
