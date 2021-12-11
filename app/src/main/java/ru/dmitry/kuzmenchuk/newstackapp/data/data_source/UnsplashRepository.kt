package ru.dmitry.kuzmenchuk.newstackapp.data.data_source

import retrofit2.Retrofit
import ru.dmitry.kuzmenchuk.newstackapp.data.util.TransformableMapping.Extensions.transform
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo

class UnsplashRepository(retrofit: Retrofit) {

    private val remoteApi: UnsplashRemoteApi = retrofit.create(UnsplashRemoteApi::class.java)

    suspend fun searchPhotos(searchQuery: String): Collection<Photo> {
        return remoteApi.searchPhotos(searchQuery).transform()
    }

    suspend fun getEditorialPhotos(): Collection<Photo> {
        return remoteApi.getEditorialPhotos().transform()
    }

    suspend fun getRandomPhoto(): Photo {
        return remoteApi.getRandomPhoto().transform()
    }
}