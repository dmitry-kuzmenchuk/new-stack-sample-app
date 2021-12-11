package ru.dmitry.kuzmenchuk.newstackapp.data.entity

import com.google.gson.annotations.SerializedName
import ru.dmitry.kuzmenchuk.newstackapp.data.util.TransformableMapping
import ru.dmitry.kuzmenchuk.newstackapp.domain.entity.PhotoUrls

data class PhotoUrlsMapping(
    @SerializedName("raw") val raw: String,
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("small") val small: String,
    @SerializedName("thumb") val thumb: String
) : TransformableMapping<PhotoUrls> {

    override fun transform(): PhotoUrls {
        return PhotoUrls(raw, full, regular, small, thumb)
    }
}