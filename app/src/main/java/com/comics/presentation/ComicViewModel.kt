package com.comics.presentation

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comics.domain.ComicUseCase
import com.comics.domain.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class ComicState {
    data class ShowLoading(val visibility: Boolean) : ComicState()
    data class ContentScreen(val items: List<Item>, val processors: ShowLoading) : ComicState()
    data class Error(val processors: ShowLoading) : ComicState()
}

class ComicViewModel(
    private val useCase: ComicUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private val _state = MutableLiveData<ComicState>()
    val state: LiveData<ComicState>
        get() = _state

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getAllComics()
    }

    fun getAllComics() {
        _state.value = ComicState.ShowLoading(showLoading)
        viewModelScope.launch {
            useCase.getAllComics().catch {
                _state.postValue(
                    ComicState.Error(
                        processors = ComicState.ShowLoading(showLoading.not())
                    )
                )
            }.collect {
                _state.value =
                    ComicState.ContentScreen(
                        items = it,
                        processors = ComicState.ShowLoading(showLoading.not())
                    )
            }
        }
    }

    companion object {
        private const val showLoading = true
    }
}