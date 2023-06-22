package com.enchantmart.security.token


//визначає інтерефейс. Буде метод який приймає інфу про токені повертає стрінговий токен
interface TokenService {
    fun generate(
        config: TokenConfig,
        vararg claim: TokenClaim
    ):String

}