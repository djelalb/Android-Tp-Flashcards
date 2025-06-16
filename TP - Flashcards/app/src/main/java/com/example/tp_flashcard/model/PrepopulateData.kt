package com.example.tp_flashcard.model

object PrepopulateData {
    val categories = listOf(
        FlashCardCategory(1L, "Mathématiques"),
        FlashCardCategory(2L, "Histoire"),
        FlashCardCategory(3L, "Langues"),
        FlashCardCategory(5L, "Géographie"),
        FlashCardCategory(7L, "Art & Culture"),
    )

    val flashcards = listOf(
        FlashCard(1L, 1L, "Quel est le résultat de 2 + 2 ?", "4"),
        FlashCard(2L, 1L, "Quelle est la dérivée de sin(x) ?", "cos(x)"),
        FlashCard(8L, 1L, "Formule du théorème de Pythagore ?", "a² + b² = c²"),

        FlashCard(3L, 2L, "Année de la Révolution française ?", "1789"),
        FlashCard(10L,2L, "Fondateur légendaire de Rome ?", "Romulus"),
        FlashCard(11L,2L, "Chute du mur de Berlin ?", "1989"),

        FlashCard(4L, 3L, "« Bonjour » en espagnol ?", "Hola"),
        FlashCard(12L,3L, "« Merci » en allemand ?", "Danke"),
        FlashCard(13L,3L, "« Bonne nuit » en italien ?", "Buona notte"),

        FlashCard(17L,5L, "Capitale de l'Australie ?", "Canberra"),
        FlashCard(18L,5L, "Plus long fleuve du monde ?", "Nil"),

        FlashCard(21L,7L, "Auteur de « La Nuit étoilée » ?", "Vincent van Gogh"),
        FlashCard(22L,7L, "Année de « Guernica » ?", "1937"),
    )
}
