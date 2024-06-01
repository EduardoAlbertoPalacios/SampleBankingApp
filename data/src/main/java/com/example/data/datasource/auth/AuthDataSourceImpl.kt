package com.example.data.datasource.auth

import com.example.data.model.request.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AuthDataSource {
    override suspend fun auth(email: String, password: String): Flow<Result<String>> =
        callbackFlow {
            db.collection(COLLECTION_PATH)
                .whereEqualTo(REFERENCE_EMAIL_PATH, email)
                .whereEqualTo(REFERENCE_PASSWORD_PATH, password)
                .get()
                .addOnCompleteListener {
                    if (it.result.isEmpty) {
                        this@callbackFlow.trySendBlocking(
                            Result.failure(
                                Exception(
                                    BAD_CREDENTIALS_ERROR
                                )
                            )
                        )
                    } else {
                        this@callbackFlow.trySendBlocking(Result.success(""))
                    }
                }
            awaitClose()
        }

    override suspend fun register(user: User): Flow<Result<String>> = callbackFlow {
        db.collection(COLLECTION_PATH)
            .whereEqualTo(REFERENCE_EMAIL_PATH, user.email)
            .get()
            .addOnCompleteListener {
                if (it.result.isEmpty) {
                    val request = user.run {
                        hashMapOf(
                            REFERENCE_NAME_PATH to name,
                            REFERENCE_SURNAME_PATH to surname,
                            REFERENCE_EMAIL_PATH to email,
                            REFERENCE_PASSWORD_PATH to password,
                            REFERENCE_IMAGE_PATH to imageUrl
                        )
                    }
                    db.collection(COLLECTION_PATH)
                        .add(request)
                        .addOnSuccessListener { documentReference ->
                            this@callbackFlow.trySendBlocking(Result.success(documentReference.id))
                        }
                        .addOnFailureListener { exception ->
                            this@callbackFlow.trySendBlocking(Result.failure(exception))
                        }
                } else {
                    this@callbackFlow.trySendBlocking(Result.failure(Exception(EXIST_USER_ERROR)))
                }
            }
        awaitClose()
    }

    private companion object {
        const val COLLECTION_PATH = "users"
        const val REFERENCE_NAME_PATH = "name"
        const val REFERENCE_SURNAME_PATH = "surname"
        const val REFERENCE_EMAIL_PATH = "email"
        const val REFERENCE_PASSWORD_PATH = "password"
        const val REFERENCE_IMAGE_PATH = "imageID"
        const val EXIST_USER_ERROR = "The user already exist"
        const val BAD_CREDENTIALS_ERROR = "Bad credentials."
    }
}