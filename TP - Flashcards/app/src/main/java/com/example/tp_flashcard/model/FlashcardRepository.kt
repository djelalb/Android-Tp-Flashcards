package com.example.tp_flashcard.model

object FlashcardRepository {

    val categories: List<FlashCardCategory> = listOf(
        FlashCardCategory(id = 1L, name = "Mathématiques"),
        FlashCardCategory(id = 2L, name = "Histoire"),
        FlashCardCategory(id = 3L, name = "Langues"),
        FlashCardCategory(id = 5L, name = "Géographie"),
        FlashCardCategory(id = 7L, name = "Art & Culture"),
    )

    val flashcards: List<FlashCard> = listOf(
        // Mathématiques
        FlashCard( id = 1L, categoryId = 1L, question = "Quel est le résultat de 2 + 2 ?", answer = "4" ),
        FlashCard( id = 2L, categoryId = 1L, question = "Quelle est la dérivée de sin(x) ?", answer = "cos(x)" ),
        FlashCard( id = 8L, categoryId = 1L, question = "Quelle est la formule du théorème de Pythagore ?", answer = "a² + b² = c²" ),

        // Histoire
        FlashCard( id = 3L, categoryId = 2L, question = "En quelle année a eu lieu la Révolution française ?", answer = "1789" ),
        FlashCard( id = 10L, categoryId = 2L, question = "Quel empereur a fondé Rome selon la légende ?", answer = "Romulus" ),
        FlashCard( id = 11L, categoryId = 2L, question = "En quelle année la chute du mur de Berlin a-t-elle eu lieu ?", answer = "1989" ),

        // Langues
        FlashCard( id = 4L, categoryId = 3L, question = "Comment dit-on « bonjour » en espagnol ?", answer = "Hola" ),
        FlashCard( id = 12L, categoryId = 3L, question = "Comment dit-on « merci » en allemand ?", answer = "Danke" ),
        FlashCard( id = 13L, categoryId = 3L, question = "Traduisez « bonne nuit » en italien.", answer = "Buona notte" ),

        // Géographie
        FlashCard( id = 17L, categoryId = 5L, question = "Quelle est la capitale de l'Australie ?", answer = "Canberra" ),
        FlashCard( id = 18L, categoryId = 5L, question = "Quel est le plus long fleuve du monde ?", answer = "Nil" ),

        // Art & Culture
        FlashCard( id = 21L, categoryId = 7L, question = "Quel peintre a réalisé 'La Nuit étoilée' ?", answer = "Vincent van Gogh" ),
        FlashCard( id = 22L, categoryId = 7L, question = "En quelle année Picasso a-t-il peint 'Guernica' ?", answer = "1937" ),
    )
}