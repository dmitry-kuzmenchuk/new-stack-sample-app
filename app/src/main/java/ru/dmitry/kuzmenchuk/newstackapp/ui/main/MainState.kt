package ru.dmitry.kuzmenchuk.newstackapp.ui.main

import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo
import ru.dmitry.kuzmenchuk.newstackapp.domain.util.loadable.Loadable

data class MainState(
    val searchQuery: String,
    val selectedTab: MainTab,
    val editorialPhotosLoadable: Loadable<Collection<Photo>>,
    val searchPhotosLoadable: Loadable<Collection<Photo>>,
    val randomPhotoLoadable: Loadable<Photo>
) {

    companion object {

        val INITIAL: MainState = MainState(
            searchQuery = "",
            selectedTab = MainTab.EDITORIAL_PHOTOS,
            editorialPhotosLoadable = Loadable.Initial(),
            searchPhotosLoadable = Loadable.Initial(),
            randomPhotoLoadable = Loadable.Initial()
        )
    }
}