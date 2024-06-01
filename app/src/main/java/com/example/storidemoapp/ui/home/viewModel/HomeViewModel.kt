package com.example.storidemoapp.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.MovementsEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.UIState
import com.example.shared.idle.CountingIdling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: UseCase<Any, List<MovementsEntity>>) :
    ViewModel() {
    @VisibleForTesting
    var _state = MutableStateFlow<UIState<List<MovementsEntity>>>(UIState.Loading)
    var state = _state.asStateFlow()

    fun fetchMovements() = viewModelScope.launch {
        useCase.execute(Unit)
            .onStart {
                CountingIdling.increment()
                _state.value = UIState.Loading
            }
            .catch {
                _state.value = UIState.Error(it.message ?: "")
            }
            .collect {
                CountingIdling.decrement()
                _state.value = UIState.Success(it)
            }
    }
}