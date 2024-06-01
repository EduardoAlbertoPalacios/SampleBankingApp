package com.example.data.datasource.movements

import com.example.data.model.response.MovementsResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class MovementsDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : MovementsDataSource {
    override suspend fun getAllMovements(): Flow<List<MovementsResponse>> = callbackFlow {
        db.collection(COLLECTION_PATH)
            .get()
            .addOnCompleteListener { task ->
                if (task.result.isEmpty.not()) {
                    val movements = task.result.map {
                        it.toObject(MovementsResponse::class.java)
                    }
                    this@callbackFlow.trySendBlocking(movements)
                }
            }
        awaitClose()
    }

    companion object {
        private const val COLLECTION_PATH = "movements"
    }
}