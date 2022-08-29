package com.talejalilov.yukatechexercise.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.talejalilov.yukatechexercise.data.AuthenticationRepoImpl
import com.talejalilov.yukatechexercise.data.UserRepositoryImpl
import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import com.talejalilov.yukatechexercise.domain.repo.UserRepository
import com.talejalilov.yukatechexercise.domain.use_cases.authenticationUseCases.*
import com.talejalilov.yukatechexercise.domain.use_cases.userUsecases.GetUserDataUseCase
import com.talejalilov.yukatechexercise.domain.use_cases.userUsecases.SetUserRouteUseCase
import com.talejalilov.yukatechexercise.domain.use_cases.userUsecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepoImpl(auth = auth, firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun provideAuthUsesCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository),
        firebaseSignUpUser = FirebaseSignUpUsers(repository = repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(
        firebaseFirestore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(
        userRepository: UserRepository
    ) = UserUseCases(
        getUserDataUseCase = GetUserDataUseCase(repository = userRepository),
        setUserRouteUseCase = SetUserRouteUseCase(repository = userRepository)
    )
}
