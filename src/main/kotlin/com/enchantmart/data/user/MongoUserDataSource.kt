package com.enchantmart.data.user

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq


// клас наслідує інтерфейс UserDataSource який містить два методи getByUserName insertUser
//конструктор приймає дб ми з дб витягуєм колекцію
//в методі getByUserName ми повертаєм за допомогою методу  findOne юзера за юзернеймом
//в методі insertUser ми добавляєм нового юзера ....Returns true if the write was acknowledged.
class MongoUserDataSource(
    db: CoroutineDatabase
) : UserDataSource {

    private val users = db.getCollection<User>()

    override suspend fun getByUserName(username: String): User? {
        return users.findOne(User::username eq username)
    }

    override suspend fun insertUser(user: User): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }
}