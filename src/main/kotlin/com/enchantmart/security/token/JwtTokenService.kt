package com.enchantmart.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import java.util.*

// клас у якого є метод generate який створює токен
class JwtTokenService : TokenService {
    override fun generate(config: TokenConfig, vararg claim: TokenClaim): String {
        var token = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))
        claim.forEach { claim ->
            token = token.withClaim(claim.name, claim.value) }
        return token.sign(Algorithm.HMAC256(config.secret))

    }
}