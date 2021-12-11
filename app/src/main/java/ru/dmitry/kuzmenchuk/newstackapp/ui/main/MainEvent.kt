package ru.dmitry.kuzmenchuk.newstackapp.ui.main

import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo
import ru.dmitry.kuzmenchuk.newstackapp.domain.util.loadable.Loadable

sealed class MainEvent {

    data class SearchQueryChanged(val newSearchQuery: String) : MainEvent()

    data class TabChanged(val tab: MainTab) : MainEvent()

    data class PhotoClicked(val photo: Photo): MainEvent()

    data class SearchPhotosRequest(val loadable: Loadable<Collection<Photo>>) : MainEvent()

    data class EditorialPhotosRequest(val loadable: Loadable<Collection<Photo>>) : MainEvent()

    data class RandomPhotoRequest(val loadable: Loadable<Photo>) : MainEvent()
}