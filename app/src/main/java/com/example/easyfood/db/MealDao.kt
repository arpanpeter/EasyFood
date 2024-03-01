package com.example.easyfood.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easyfood.pojo.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Update(meal: Meal)
    @Delete
    suspend fun delete(meal: Meal)
    @Query("SELECT * FROM mealinformation")
    fun getAllMeals():LiveData<List<Meal>>

}