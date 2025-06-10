package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.model.FlashcardRepository
import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.ui.flashcard.FlashcardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<FlashcardUiState> =
        MutableStateFlow(FlashcardUiState())

    val uiState: StateFlow<FlashcardUiState> = _uiState

    fun loadCardsForCategory(categoryId: Long) {
        viewModelScope.launch {
            val cards: List<FlashCard> =
                FlashcardRepository.flashcards.filter { it.categoryId == categoryId }

            _uiState.value = FlashcardUiState(
                currentIndex = 0,
                cards = cards,
                isSessionFinished = cards.isEmpty()
            )
        }
    }

    fun moveToNextCard() {
        viewModelScope.launch {
            val current = _uiState.value
            val nextIndex = current.currentIndex + 1
            val finished = nextIndex >= current.cards.size

            _uiState.value = current.copy(
                currentIndex = nextIndex.coerceAtMost(current.cards.size),
                isSessionFinished = finished
            )
        }
    }
}
