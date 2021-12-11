package ru.dmitry.kuzmenchuk.newstackapp.data.data_source

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dmitry.kuzmenchuk.newstackapp.data.entity.PhotoMapping

@Deprecated("add api token to requests")
interface UnsplashRemoteApi {

    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") searchQuery: String,
        @Query("per_page") itemsPerPage: Int = ITEMS_PER_PAGE
    ): Collection<PhotoMapping>

    @GET("/photos")
    suspend fun getEditorialPhotos(
        @Query("per_page") itemsPerPage: Int = ITEMS_PER_PAGE
    ): Collection<PhotoMapping>

    @GET("/photos/random")
    suspend fun getRandomPhoto(): PhotoMapping

    private companion object {

        const val ITEMS_PER_PAGE: Int = 40
    }
}