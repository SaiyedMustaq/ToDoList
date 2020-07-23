package com.mustaq.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class ToDoRoomDataBase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ToDoRoomDataBase? = null
        private const val DATABASE_NAME = "TOTO_DATABASE"

        fun getDatabaseInstance(context: Context): ToDoRoomDataBase {
            val roomInstances = INSTANCE
            if (roomInstances != null) {
                return roomInstances
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoRoomDataBase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}