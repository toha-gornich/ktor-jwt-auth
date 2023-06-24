package com.enchantmart.plugins

import com.enchantmart.authenticate
import com.enchantmart.data.user.UserDataSource
import com.enchantmart.getSecretInfo
import com.enchantmart.security.hashing.HashingService
import com.enchantmart.security.token.TokenConfig
import com.enchantmart.security.token.TokenService
import com.enchantmart.signIn
import com.enchantmart.signUp
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
