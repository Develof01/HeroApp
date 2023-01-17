package com.mx.android.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mx.android.database.entities.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Query("SELECT * FROM Hero")
    fun findAll(): Flow<List<Hero>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListHero(heros: List<Hero>)

    @Query("SELECT isFavorite FROM Hero where Hero.uid = :id")
    fun isFavorite(id: Int): Flow<Boolean>

    @Update (onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHerosAsFavorites(vararg hero: Hero)

    @Delete
    suspend fun deleteHeros(vararg  hero: Hero)

}