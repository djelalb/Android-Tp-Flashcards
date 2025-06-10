package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.tp_flashcard.model.FlashcardRepository
import com.example.tp_flashcard.model.FlashCardCategory

class HomeViewModel : ViewModel() {

    private val _categories: MutableStateFlow<List<FlashCardCategory>> =
        MutableStateFlow(emptyList())

    val categories: StateFlow<List<FlashCardCategory>> = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = FlashcardRepository.categories
    }
}