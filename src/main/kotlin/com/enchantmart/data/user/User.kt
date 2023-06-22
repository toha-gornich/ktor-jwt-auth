package com.enchantmart.data.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class User(
    val username: String,
    val password: String,
    val salt: String,
    //буде генеруватись унікальний рандомний ід
    //BsonId це для монго щоб він знав що то буде ід проперті
    //і зручна штука для пошуку
    @BsonId val id: ObjectId = ObjectId()
)
