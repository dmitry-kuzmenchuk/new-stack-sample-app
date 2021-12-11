package ru.dmitry.kuzmenchuk.newstackapp.domain.interactor

import ru.dmitry.kuzmenchuk.newstackapp.data.data_source.UnsplashRepository
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo

class UnsplashInteractor(private val repository: UnsplashRepository) {

    suspend fun searchPhotos(searchQuery: String): Collection<Photo> {
        return repository.searchPhotos(searchQuery)
    }

    suspend fun getEditorialPhotos(): Collection<Photo> {
        return repository.getEditorialPhotos()
    }

    suspend fun getRandomPhoto(): Photo {
        return repository.getRandomPhoto()
    }
}