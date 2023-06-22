package com.enchantmart.security.token


//зберігається інфа про токен
data class TokenClaim(
    val name: String,
    val value: String
)
