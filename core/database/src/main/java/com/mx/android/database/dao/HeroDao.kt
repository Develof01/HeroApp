package com.mx.android.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mx.android.database.entities.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM Hero")
    fun findAll(): List<Hero>?

}