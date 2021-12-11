package ru.dmitry.kuzmenchuk.newstackapp.domain.util.loadable

sealed class Loadable<T : Any> {

    class Initial<T : Any> : Loadable<T>()

    class Loading<T : Any> : Loadable<T>()

    data class Succeed<T : Any>(val data: T) : Loadable<T>()

    data class Failed<T : Any>(val error: Throwable) : Loadable<T>()

    val isLoading: Boolean get() = this is Loading

    val isSucceed: Boolean get() = this is Succeed

    val isFailed: Boolean get() = this is Failed

    val isComplete: Boolean get() = isSucceed || isFailed

    fun getDataOrNull(): T? {
        return (this as? Succeed<T>)?.data
    }

    fun getDataOrThrow(): T {
        return getDataOrNull() ?: error("Has no data")
    }

    fun getErrorOrNull(): Throwable? {
        return (this as? Failed<T>)?.error
    }

    fun getErrorOrThrow(): Throwable {
        return getErrorOrNull() ?: error("Has no error")
    }

    fun <R : Any> map(mapper: (T) -> R): Loadable<R> {
        return when(this) {
            is Initial -> Initial()
            is Loading -> Loading()
            is Failed -> Failed(error)
            is Succeed -> Succeed(mapper(data))
        }
    }
}