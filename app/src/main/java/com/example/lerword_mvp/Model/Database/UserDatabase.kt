package com.example.lerword.Model.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lerword_mvp.Model.User
import com.example.lerword_mvp.Utilities.DATABASE_NAME

/*
 * Created by Skyain1 on 24.06.2023.
 */

@Database (entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao():UserDao

    companion object {
        @Volatile
        private var INSTANCE : UserDatabase? = null
        fun getDatabase(context: Context):UserDatabase{

            return  INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}