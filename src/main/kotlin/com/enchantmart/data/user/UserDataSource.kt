package com.enchantmart.data.user

interface UserDataSource {
    //отримати юзера по юзернейму
    suspend fun getByUserName(username: String):User?
    //new user
    suspend fun insertUser(user: User):Boolean
}
