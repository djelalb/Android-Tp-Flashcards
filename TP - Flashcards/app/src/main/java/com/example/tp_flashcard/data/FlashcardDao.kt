package com.example.tp_flashcard.data

import androidx.room.*
import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<FlashCardCategory>>

    @Query("SELECT * FROM flashcards WHERE categoryId = :catId ORDER BY id")
    fun getFlashcardsForCategory(catId: Long): Flow<List<FlashCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<FlashCardCategory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashcards(flashcards: List<FlashCard>)
}
