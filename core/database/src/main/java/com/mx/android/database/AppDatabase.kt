package com.mx.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mx.android.common.constants.Generals
import com.mx.android.database.dao.HeroDao
import com.mx.android.database.entities.Hero

@Database(
    entities = [Hero::class],
    version = Generals.RoomConstants.DB_VERSION
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Generals.RoomConstants.DB_NAME
        ).build()
    }

    abstract fun heroDao(): HeroDao

}