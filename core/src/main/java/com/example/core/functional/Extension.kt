package com.example.core.functional

/**
 * Returns this Boolean value if it is not null, or the default value otherwise.
 *
 * @param default The value to return if this Boolean is null. Defaults to `false`.
 * @return The original Boolean value, or the default if the original was null.
 */
fun Boolean?.orDefault(default : Boolean = false) : Boolean = this ?: default

/**
 * Maps a nullable list of type `T` to a non-nullable list of type `R`, providing a default list if the original is null.
 *
 * This extension function first checks if the list is not null.
 * If it's not null, it filters out any null elements within the list, then applies the `transform` function to each remaining element.
 * If the original list is null, it returns the `defaultListValue`.
 *
 * @param T The type of elements in the source list.
 * @param R The type of elements in the resulting list. Must be a non-nullable type.
 * @param defaultListValue The list to return if the original list is `null`. Defaults to an empty list.
 * @param transform The function to apply to each non-null element of the source list.
 * @return A new list of type `R` containing the transformed elements, or the `defaultListValue` if the source list was `null`.
 */
fun <T,R : Any> List<T>?.mapOrDefault(defaultListValue : List<R> = emptyList(), transform: (T) -> R): List<R> {
    return this?.filterNotNull()?.map(transform) ?: defaultListValue
}