package ru.dmitry.kuzmenchuk.newstackapp.domain.entity

data class Photo(
    val id: String,
    val description: String,
    val sizes: PhotoSizes,
    val urls: PhotoUrls
)