package com.campodonico.ec_final.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.campodonico.ec_final.model.Michi

@Database(entities = [Michi::class], version = 1)
abstract class MichiDataBase: RoomDatabase() {
    abstract fun michiDao() : MichiDao

    companion object{
        @Volatile
        private var instance: MichiDataBase?= null
        fun getDatabase(contex: Context): MichiDataBase{
            if (instance==null){
                synchronized(this){
                    instance= buildDatabase(contex)
                }
            }
            return instance!!
        }
        private fun buildDatabase(contex: Context): MichiDataBase?{
            return Room.databaseBuilder(
                contex.applicationContext,
                MichiDataBase::class.java,
                "michi_database"
            ).build()
        }
    }
}