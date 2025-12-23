package com.example.data.remote.handler

import com.example.core.error.Failure
import com.example.core.functional.Either
import com.example.core.mapper.ResultMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Wraps a Retrofit API call to safely handle network requests and potential failures.
 * It executes the API call on a specified coroutine dispatcher (defaults to IO).
 *
 * It handles success and error cases:
 * - On a successful response (HTTP 2xx), it attempts to parse the response body.
 *   - If the body is not null, it's mapped using the provided `mapper` and wrapped in an `Either.Right`.
 *   - If the body is null, it's treated as a `Failure.ServerError` and wrapped in an `Either.Left`.
 * - On an unsuccessful response (non-2xx HTTP status), it's treated as a `Failure.ServerError` with the corresponding
 *   error code and message, wrapped in an `Either.Left`.
 * - If any exception occurs during the API call (e.g., network issues like `IOException`),
 *   it's caught and converted to a corresponding `Failure` type, wrapped in an `Either.Left`.
 *
 * @param T The type of the raw data model received from the API response body.
 * @param R The type of the domain model after mapping.
 * @param ioDispatcher The [CoroutineDispatcher] on which the network call will be executed. Defaults to [Dispatchers.IO].
 * @param apiCall A suspend lambda function that performs the actual network request and returns a Retrofit [Response].
 * @param mapper An instance of [ResultMapper] to transform the successful API response data [T] into the desired domain model [R].
 * @return An [Either] instance containing either a [Failure] on the left side or the successfully mapped result [R] on the right side.
 */
suspend fun <T, R> safeApiCall(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>,
    mapper: ResultMapper<T, R>
): Either<Failure, R> {
    return withContext(ioDispatcher) {
        runCatching {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    Either.Right(mapper.map(it))
                } ?: Either.Left(
                    Failure.ServerFailure(
                        code = response.code(),
                        message = response.message()
                    )
                )
            } else {
                Either.Left(Failure.ServerFailure(response.code(), response.message()))
            }
        }
    }.getOrElse {
        it.toEither()
    }
}