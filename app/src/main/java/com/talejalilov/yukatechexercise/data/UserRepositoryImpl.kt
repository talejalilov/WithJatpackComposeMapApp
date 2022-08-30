package com.talejalilov.yukatechexercise.data

import com.google.firebase.firestore.FirebaseFirestore
import com.talejalilov.yukatechexercise.domain.model.User
import com.talejalilov.yukatechexercise.domain.repo.UserRepository
import com.talejalilov.yukatechexercise.util.Constants
import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository{

    private var operationSuccessful = false

    override fun getUserData(adminId: String, userId: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapshotListener = firebaseFirestore.collection(Constants.COLLECTION_NAME_ADMINS)
            .document(adminId).collection(Constants.COLLECTION_NAME_USERS).document(userId)
            .addSnapshotListener{
                snapshot,error ->
                val response = if(snapshot!=null){
                    val userInfo = snapshot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                }else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapshotListener.remove()
        }
    }

//    override fun getUserDetail(userId: String): Flow<Response<User>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun setUserRoute(
//        adminId: String,
//        userId: String,
//        latitude: String,
//        longitude: String
//    ): Flow<Response<Boolean>> = flow {
//
//        operationSuccessful = false
//        try {
//            val userObj = mutableMapOf<String,String>()
//            userObj["longitude"] = longitude
//            userObj["latitude"] = latitude
//
//            firebaseFirestore.collection(Constants.COLLECTION_NAME_ADMINS).document(adminId)
//                .collection(Constants.COLLECTION_NAME_USERS).document(userId).update(userObj as Map<String,Any>)
//                .addOnSuccessListener {
//
//                }.await()
//
//            if(operationSuccessful){
//                emit(Response.Success(operationSuccessful))
//            }else {
//                emit(Response.Error("You can not send Data"))
//
//            }
//        }catch (e:Exception){
//
//        }
//    }

}