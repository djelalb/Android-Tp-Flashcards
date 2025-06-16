package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.tp_flashcard.model.FlashcardRepository
import com.example.tp_flashcard.model.FlashCardCategory
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _categories = MutableStateFlow<List<FlashCardCategory>>(emptyList())
    val categories: StateFlow<List<FlashCardCategory>> = _categories

    init {
        viewModelScope.launch {
            FlashcardRepository.getCategories()
                .collect { list ->
                    _categories.value = list
                }
        }
    }
}