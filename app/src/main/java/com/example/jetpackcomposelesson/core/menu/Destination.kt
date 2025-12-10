package com.example.jetpackcomposelesson.core.menu

import kotlinx.serialization.Serializable
import java.util.Locale.getDefault

private interface Destination {
    val key: MenuKey
    val name: ScreenName
    val deepLink: String
}

@Serializable
abstract class BaseDestination(
    override val key: MenuKey,
    override val name: ScreenName
) : Destination {

    override val deepLink: String
        get() = "jetpackcomposelesson://${key.name.lowercase(getDefault())}"
}

