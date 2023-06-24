package com.enchantmart

import com.enchantmart.data.user.MongoUserDataSource
import com.enchantmart.data.user.User
import io.ktor.server.application.*
import com.enchantmart.plugins.*
import com.enchantmart.security.hashing.SHA256HashingService
import com.enchantmart.security.token.JwtTokenService
import com.enchantmart.security.token.TokenConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    //підключення до монго дб у випадку якшо база даних віддалена
//    val mongoPw = System.getenv("MONGO_PW")
//    val dbName = "ktor-auth"
//    val db = KMongo.createClient(
//        connectionString = "mongodb+srv://hornihanton:$mongoPw@cluster0.t6qerxj.mongodb.net/$dbName?retryWrites=true&w=majority"
//    ).coroutine
//        .getDatabase(dbName)

    val db = KMongo.createClient("mongodb://localhost:2717/").coroutine.getDatabase("enchantMart")
    //передаєм нашу ініціалізовану бд в MongoUserDataSource і з якою ми в подальшому бедм працювати
    val userDataSource = MongoUserDataSource(db)
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()


    configureSecurity(tokenConfig)
    configureMonitoring()
    configureSerialization()
    configureRouting(userDataSource, hashingService, tokenService, tokenConfig)
}
