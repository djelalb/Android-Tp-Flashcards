package com.example.tp_flashcard.ui.flashcard

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import com.example.tp_flashcard.viewmodel.FlashcardViewModel
import androidx.compose.animation.with
import androidx.compose.animation.core.Animatable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlashcardScreen(
    categoryId: Long,
    flashcardViewModel: FlashcardViewModel = viewModel(),
    onBack: () -> Unit
) {
    LaunchedEffect(categoryId) {
        flashcardViewModel.loadCardsForCategory(categoryId)
    }

    val uiState by flashcardViewModel.uiState.collectAsState()
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current.density

    LaunchedEffect(uiState.currentIndex) {
        rotation.snapTo(0f)
    }

    val progress = remember(uiState.currentIndex, uiState.cards) {
        if (uiState.cards.isEmpty()) 0f
        else (uiState.currentIndex + 1).toFloat() / uiState.cards.size
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = uiState.currentIndex,
                transitionSpec = {
                    val animSpec = tween<Int>(durationMillis = 300)

                    if (initialState < targetState) {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        ) with slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    } else {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        ) with slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                }
            ) { index ->
                if (uiState.cards.isEmpty()) {
                    Text("Aucune carte disponible")
                } else {
                    val card = uiState.cards[index]
                    val isFront = rotation.value < 90f
                    val displayText = if (isFront) card.question else card.answer

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .aspectRatio(1.2f)
                            .clickable {
                                scope.launch {
                                    val target = if (isFront) 180f else 0f
                                    rotation.animateTo(target, tween(400))
                                }
                            }
                            .graphicsLayer {
                                rotationY = rotation.value
                                cameraDistance = 8 * density
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isFront) Color(0xFFBBDEFB) else Color(0xFFFFF9C4)
                        ),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = displayText,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.graphicsLayer {
                                    if (!isFront) rotationY = 180f
                                }
                            )
                        }
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBack) {
                Text("Retour")
            }
            val isLast = uiState.currentIndex >= uiState.cards.lastIndex
            Button(
                onClick = {
                    if (isLast) onBack()
                    else flashcardViewModel.moveToNextCard()
                }
            ) {
                Text(if (isLast) "Terminer" else "Suivant")
            }
        }
    }
}