package com.example.storidemoapp.home

import com.example.domain.entities.MovementsEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.UIState
import com.example.storidemoapp.mocks.getMockMovements
import com.example.storidemoapp.ui.home.viewModel.HomeViewModel
import com.example.storidemoapp.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()
    private val useCase = mock<UseCase<Any, List<MovementsEntity>>>()
    private var uiStateMock = mock<MutableStateFlow<UIState<List<MovementsEntity>>>>()

    private val viewModel by lazy {
        HomeViewModel(useCase)
    }

    @Before
    fun setup() {
        viewModel._state = uiStateMock
    }

    @Test
    fun `fetch data from fake server`() = runTest {
        useCase.stub {
            onBlocking { execute(Unit) } doReturn flowOf(getMockMovements())
        }

        viewModel.fetchMovements()

        advanceUntilIdle()

        verify(uiStateMock).value = UIState.Success(getMockMovements())
    }

    @Test
    fun `fetch data from fake server with thrown exception`() = runTest {
        useCase.stub {
            onBlocking { execute(Unit) } doReturn flow {
                throw IOException(FAKE_ERROR)
            }
        }

        viewModel.fetchMovements()

        advanceUntilIdle()

        verify(uiStateMock).value = UIState.Error(FAKE_ERROR)
    }

    companion object {
        private const val FAKE_ERROR = "fake error"
    }
}
