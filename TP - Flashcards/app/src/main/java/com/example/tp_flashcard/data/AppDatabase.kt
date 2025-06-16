package com.example.tp_flashcard.data

import androidx.room.*
import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory

@Database(
    entities = [FlashCardCategory::class, FlashCard::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
