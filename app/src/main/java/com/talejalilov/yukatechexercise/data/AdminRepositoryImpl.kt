package com.talejalilov.yukatechexercise.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.talejalilov.yukatechexercise.domain.model.User
import com.talejalilov.yukatechexercise.domain.repo.AdminRepository
import com.talejalilov.yukatechexercise.util.Constants
import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore) :AdminRepository {
    override fun getAdminUsers(adminId: String): Flow<Response<List<User>>> = callbackFlow {

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(Constants.COLLECTION_NAME_ADMINS).document(adminId)
            .collection(Constants.COLLECTION_NAME_USERS).addSnapshotListener{
                snapshot,errror ->
                val response = if(snapshot!=null){
                    val adminUserList  =snapshot.toObjects(User::class.java)
                    Response.Success<List<User>>(adminUserList)
                }
                else {
                    Response.Error(errror?.message?:errror.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapshotListener.remove()
        }

    }

}