package com.example.tp_flashcard.ui.flashcard

import com.example.tp_flashcard.model.FlashCard

/**
 * @param currentIndex
 * @param cards
 * @param isSessionFinished
 */
data class FlashcardUiState(
    val currentIndex: Int = 0,
    val cards: List<FlashCard> = emptyList(),
    val isSessionFinished: Boolean = false
)
