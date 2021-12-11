package ru.dmitry.kuzmenchuk.newstackapp.data.util

interface TransformableMapping<OUT : Any> {

    fun transform(): OUT

    object Extensions {

        fun <T : Any> Collection<TransformableMapping<T>>.transform(): Collection<T> {
            return map { mapping -> mapping.transform() }
        }
    }
}