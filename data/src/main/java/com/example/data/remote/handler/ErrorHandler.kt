package com.example.data.remote.handler

import com.example.core.error.Failure
import com.example.core.functional.Either
import retrofit2.HttpException
import java.io.IOException


/**
 * Converts a [Throwable] into a [Either.Left] containing a corresponding [Failure].
 * This extension function is used to handle exceptions in the data layer and wrap them
 * in a domain-specific error type.
 *
 * - [IOException] is mapped to [Failure.NetworkFailure].
 * - [HttpException] is mapped to [Failure.ServerFailure], extracting the HTTP status code and message.
 * - Any other [Throwable] is mapped to [Failure.UnknownFailure].
 *
 * @return An [Either.Left] holding the appropriate [Failure].
 */
fun Throwable.toEither(): Either<Failure, Nothing> {
    return when(this) {
        is IOException -> Either.Left(Failure.NetworkFailure(this))
        is HttpException -> {
            val code = code()
            val message = message()
            Either.Left(Failure.ServerFailure(code, message))
        }
        else -> Either.Left(Failure.UnknownFailure(this))
    }
}
