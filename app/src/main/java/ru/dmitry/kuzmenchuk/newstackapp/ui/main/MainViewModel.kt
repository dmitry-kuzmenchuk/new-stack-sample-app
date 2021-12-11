package ru.dmitry.kuzmenchuk.newstackapp.ui.main

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo
import ru.dmitry.kuzmenchuk.newstackapp.domain.interactor.UnsplashInteractor
import ru.dmitry.kuzmenchuk.newstackapp.domain.util.loadable.LoadableScope

class MainViewModel(private val interactor: UnsplashInteractor) : ViewModel(), LoadableScope {

    val stateFlow: Flow<MainState> get() = stateHolder.asSharedFlow()

    private val stateHolder: MainStateHolder = MainStateHolder()
    private val mainHandler: Handler = Handler(Looper.getMainLooper())

    fun sendEvent(event: MainEvent) {
        mainHandler.post { handleEvent(event) }
    }

    private fun handleEvent(event: MainEvent) {
        updateState(event)
        handleLogic(event)
    }

    private fun updateState(event: MainEvent) {
        val oldState = stateHolder.value
        val newState = when (event) {
            is MainEvent.EditorialPhotosRequest -> oldState.copy(editorialPhotosLoadable = event.loadable)
            is MainEvent.RandomPhotoRequest -> oldState.copy(randomPhotoLoadable = event.loadable)
            is MainEvent.SearchPhotosRequest -> oldState.copy(searchPhotosLoadable = event.loadable)
            is MainEvent.SearchQueryChanged -> oldState.copy(searchQuery = event.newSearchQuery)
            is MainEvent.TabChanged -> oldState.copy(selectedTab = event.tab)
            else -> oldState
        }
        stateHolder.value = newState
    }

    private fun handleLogic(event: MainEvent) {

    }

    private var loadEditorialPhotosJob: Job? = null
    private fun loadEditorialPhotos() {
        loadEditorialPhotosJob?.cancel()
        loadEditorialPhotosJob = asLoadable { interactor.getEditorialPhotos() }
            .onEach { loadable -> sendEvent(MainEvent.EditorialPhotosRequest(loadable)) }
            .launchIn(viewModelScope)
    }

    private var searchPhotosJob: Job? = null
    private fun searchPhotos(searchQuery: String) {
        searchPhotosJob?.cancel()
        searchPhotosJob = asLoadable { interactor.searchPhotos(searchQuery) }
            .onEach { loadable -> sendEvent(MainEvent.SearchPhotosRequest(loadable)) }
            .launchIn(viewModelScope)
    }

    private var loadRandomPhotoJob: Job? = null
    private fun loadRandomPhoto() {
        loadRandomPhotoJob?.cancel()
        loadRandomPhotoJob = asLoadable { interactor.getRandomPhoto() }
            .onEach { loadable -> sendEvent(MainEvent.RandomPhotoRequest(loadable)) }
            .launchIn(viewModelScope)
    }
}