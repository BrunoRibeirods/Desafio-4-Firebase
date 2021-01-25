package com.digitalhouse.desafiofirebase.entities

import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.serialization.Serializable

@IgnoreExtraProperties
@Serializable
data class Game(
        val title: String? = "",
        val date: String? = "",
        val description: String? = "",
        val img: String? = ""
)




