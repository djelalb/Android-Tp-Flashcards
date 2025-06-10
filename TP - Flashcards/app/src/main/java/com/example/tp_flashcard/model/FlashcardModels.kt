package com.example.tp_flashcard.model

/**
 * @param id
 * @param categoryId
 * @param question
 * @param answer
 */
data class FlashCard(
    val id: Long,
    val categoryId: Long,
    val question: String,
    val answer: String
)

/**
 * @param id
 * @param name
 */
data class FlashCardCategory(
    val id: Long,
    val name: String
)