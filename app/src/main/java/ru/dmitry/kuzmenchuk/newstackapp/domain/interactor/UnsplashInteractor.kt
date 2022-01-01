package ru.dmitry.kuzmenchuk.newstackapp.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dmitry.kuzmenchuk.newstackapp.data.data_source.UnsplashRepository
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo

class UnsplashInteractor(private val repository: UnsplashRepository) {

    suspend fun searchPhotos(searchQuery: String): Collection<Photo> {
        return withContext(Dispatchers.IO) {
            repository.searchPhotos(searchQuery)
        }
    }

    suspend fun getEditorialPhotos(): Collection<Photo> {
        return withContext(Dispatchers.IO) {
            repository.getEditorialPhotos()
        }
    }

    suspend fun getRandomPhoto(): Photo {
        return withContext(Dispatchers.IO) {
            repository.getRandomPhoto()
        }
    }
}