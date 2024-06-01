package com.example.data.repository

import com.example.data.datasource.movements.MovementsDataSource
import com.example.data.repository.mocks.getMockMovements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class MovementsDataRepositoryTest {
    private val client = mock<MovementsDataSource>()
    private val repository by lazy {
        MovementsDataRepository(client, Dispatchers.IO)
    }

    @Test
    fun `fetch card movements with success status`() = runTest {
        client.stub {
            onBlocking { getAllMovements() } doReturn flow { getMockMovements() }
        }

        repository.getAllMovements().collect { result ->
            assert(result == getMockMovements())
        }
    }

    @Test
    fun `fetch card movements with error status`() = runTest {
        client.stub {
            onBlocking { getAllMovements() } doReturn flow { throw Exception(MESSAGE_ERROR) }
        }

        repository.getAllMovements().catch { error ->
            assert(error.message == MESSAGE_ERROR)
        }.catch { }
    }

    companion object {
        private const val MESSAGE_ERROR = "AN ERROR HAS OCCURRED"
    }
}
