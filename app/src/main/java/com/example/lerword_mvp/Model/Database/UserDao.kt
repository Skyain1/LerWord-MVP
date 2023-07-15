package com.example.lerword.Model.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lerword_mvp.Model.User

/*
 * Created by Skyain1 on 24.06.2023.
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user : User)

    @Query("SELECT COUNT(*) FROM users_table WHERE email = :email and password=:password")
    suspend fun checkUserExists(email: String?, password: String?): Int
}