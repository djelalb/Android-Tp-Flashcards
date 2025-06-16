package com.example.tp_flashcard.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @param id
 * @param categoryId
 * @param question
 * @param answer
 */
@Entity(
    tableName = "flashcards",
    foreignKeys = [
        ForeignKey(
            entity = FlashCardCategory::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId")]
)
data class FlashCard(
    @PrimaryKey val id: Long,
    val categoryId: Long,
    val question: String,
    val answer: String
)

/**
 * @param id
 * @param name
 */
@Entity(tableName = "categories")
data class FlashCardCategory(
    @PrimaryKey val id: Long,
    val name: String
)