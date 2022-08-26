package com.talejalilov.yukatechexercise.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.talejalilov.yukatechexercise.domain.model.Admin
import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import com.talejalilov.yukatechexercise.util.Constants
import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthenticationRepository {

    var operationIsSuccessful : Boolean = false

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow {

        operationIsSuccessful= false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                operationIsSuccessful = true
            }.await()
            emit(Response.Success(operationIsSuccessful))

        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"Login Error"))
        }
    }


    override fun firebaseSignUp(username:String,
                                email: String,
                                password: String
    ): Flow<Response<Boolean>> = flow{

        operationIsSuccessful = false
        try {

            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                operationIsSuccessful = true

            }
                if(operationIsSuccessful){

                    val userId = auth.currentUser?.uid!!
                    val obj = Admin(username = username, userId = userId,   password = password, email = email)
                    firestore.collection(Constants.COLLECTION_NAME_USERS).document(userId).set(obj).addOnSuccessListener {

                    }.await()
                    emit(Response.Success(operationIsSuccessful))


                }else{


                }



        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"SignUp Error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {

        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))

        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"Logout Error"))
        }

    }


}