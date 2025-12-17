package com.example.core.functional

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 *
 * This is a functional programming concept used to represent a value that can be one of two things.
 * By convention, `Left` is used to indicate an error or failure case, and `Right` is used for a
 * success case (implying "the right value").
 *
 * @param L The type of the `Left` value.
 * @param R The type of the `Right` value.
 */
sealed class Either<out L, out R> {
    data class Left<out T>(val value: T) : Either<T, Nothing>()
    data class Right<out T>(val value: T) : Either<Nothing, T>()
}

/**
 * Combines two [Either] instances into a single [Either] containing a [Pair] of their `Right` values.
 * If either of the original [Either] instances is a `Left`, the result is the first `Left` encountered.
 *
 * This is useful for chaining operations that can fail, where you only want to proceed if all previous
 * operations were successful.
 *
 * @param L The type of the `Left` value.
 * @param R1 The type of the `Right` value of the first [Either].
 * @param R2 The type of the `Right` value of the second [Either].
 * @param other The second [Either] to combine with this one.
 * @return An `Either.Right<Pair<R1, R2>>` if both this and `other` are `Right`, otherwise the first `Left` found.
 *
 * @sample
 * val right1: Either<String, Int> = Either.Right(5)
 * val right2: Either<String, String> = Either.Right("hello")
 * val left1: Either<String, Int> = Either.Left("Error")
 *
 * right1.cross(right2) // Returns Either.Right(Pair(5, "hello"))
 * right1.cross(left1)  // Returns Either.Left("Error")
 * left1.cross(right1)  // Returns Either.Left("Error")
 */
inline fun <L, R, T> Either<L, R>.cross(
    left: (L) -> T,
    right: (R) -> T
) = when (this) {
    is Either.Left -> left(value)
    is Either.Right -> right(value)
}