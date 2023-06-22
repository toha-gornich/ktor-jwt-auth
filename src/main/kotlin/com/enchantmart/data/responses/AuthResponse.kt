package com.enchantmart.data.responses

import kotlinx.serialization.Serializable


@Serializable
data class AuthResponse(
    val token: String
)
