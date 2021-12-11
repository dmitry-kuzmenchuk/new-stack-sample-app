package ru.dmitry.kuzmenchuk.newstackapp.data.entity

import com.google.gson.annotations.SerializedName
import ru.dmitry.kuzmenchuk.newstackapp.data.util.TransformableMapping
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.Photo
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.PhotoSizes

data class PhotoMapping(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("urls") val urls: PhotoUrlsMapping
) : TransformableMapping<Photo> {

    override fun transform(): Photo {
        return Photo(id, description, PhotoSizes(width, height), urls.transform())
    }
}