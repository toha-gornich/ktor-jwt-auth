package com.enchantmart.security.hashing

interface HashingService {
    //saltLength це наскільки довгий хеш буде згенерований
    fun generateSaltedHash(value: String, saltLength: Int = 32):SaltedHash

    //цей метод повертатиме чи правильний пароль
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}