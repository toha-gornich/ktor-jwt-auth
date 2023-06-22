package com.enchantmart.security.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashingService:HashingService {

    override fun generateSaltedHash(value: String, saltLength: Int): SaltedHash {

        //рандомна генерація salt це штука яка додасться до пароля перед його хешуванням
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$value$salt")
        //повернення хешу і солт як 16_ сист
        return SaltedHash(
            hash = hash,
            salt = saltAsHex
        )
    }
//метод перевіряє чи хеш збігається
    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        return DigestUtils.sha256Hex(saltedHash.salt+value) == saltedHash.hash
    }
}