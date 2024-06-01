package com.example.storidemoapp.picture

import android.net.Uri
import com.example.domain.entities.MovementsEntity
import com.example.domain.entities.UserEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.ResponseType
import com.example.shared.commonResult.UIState
import com.example.storidemoapp.mocks.mockRegisterFormModel
import com.example.storidemoapp.ui.register.form.RegisterFormModel
import com.example.storidemoapp.ui.register.picture.TakePhotoViewModel
import com.example.storidemoapp.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class TakePhotoViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()
    private val useCase: UseCase<UserEntity, Result<String>> = mockk()

    private val viewModel by lazy {
        TakePhotoViewModel(useCase)
    }

    @Test
    fun `verify if the user has pressed the take photo button`() = runTest {
        viewModel.takePhoto(true)
        advanceUntilIdle()
        assert(viewModel.state.value.isTakePhotoPressed)
    }

    @Test
    fun `verify if the photo was not taken`() = runTest {
        viewModel.verifyInputs(RegisterFormModel())
        advanceUntilIdle()
        assert(viewModel.state.value.isPhotoUnavailable)
    }

    @Test
    fun `register user with success status`() = runTest {
        coEvery { useCase.execute(any()) } coAnswers {
            flowOf(
                Result.success(
                    SUCCESS_MESSAGE_REGISTRATION
                )
            )
        }

        viewModel.registerUser(mockRegisterFormModel())

        advanceUntilIdle()

        assert(viewModel.state.value.responseType == ResponseType.SUCCESS)
    }

    @Test
    fun `register user with error status`() = runTest {
        coEvery { useCase.execute(any()) } coAnswers {
            flowOf(
                Result.failure(Exception(ERROR_MESSAGE_REGISTRATION))
            )
        }

        viewModel.registerUser(mockRegisterFormModel())

        advanceUntilIdle()

        assert(viewModel.state.value.responseType == ResponseType.ERROR)
    }

    private companion object {
        const val FAKE_URI_IMAGE = "/images/JPEG_2024_05_30_10:05:20_4964036488515602648.jpg"
        const val SUCCESS_MESSAGE_REGISTRATION = "User has been registered successfully"
        const val ERROR_MESSAGE_REGISTRATION = "An error has occurred"
    }
}
