package ru.dmitry.kuzmenchuk.newstackapp.domain.util.loadable

import kotlinx.coroutines.flow.*

interface LoadableScope {

    fun <T : Any> Flow<T>.asLoadable(): Flow<Loadable<T>> {
        return this
            .map<T, Loadable<T>> { data -> Loadable.Succeed(data) }
            .catch { error -> emit(Loadable.Failed(error)) }
            .onStart { emit(Loadable.Loading()) }
    }

    fun <T : Any> asLoadable(block: suspend () -> T): Flow<Loadable<T>> {
        return flow { emit(block()) }.asLoadable()
    }
}