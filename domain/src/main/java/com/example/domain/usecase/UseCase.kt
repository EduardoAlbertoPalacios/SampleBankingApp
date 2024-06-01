package com.example.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Abstract [UseCase] that encapsulate the business logic of the login application.
 * Perform the task in a flow context
 */
abstract class UseCase<Params, T> {
    /**
     *
     * @param params represent the data that will be set to the request object.
     */
    suspend fun execute(params: Params): Flow<T> = buildUseCase(params)

    abstract suspend fun buildUseCase(params: Params): Flow<T>
}