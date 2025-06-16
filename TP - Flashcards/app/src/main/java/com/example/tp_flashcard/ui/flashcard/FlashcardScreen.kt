package com.example.tp_flashcard.ui.flashcard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tp_flashcard.viewmodel.FlashcardViewModel
import com.example.tp_flashcard.ui.flashcard.FlashcardUiState
import kotlinx.coroutines.launch

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
                    val enter = slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    ) + fadeIn(
                        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                    ) + scaleIn(
                        initialScale = 1.05f,
                        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                    )
                    val exit = slideOutHorizontally(
                        targetOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    ) + fadeOut(
                        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
                    ) + scaleOut(
                        targetScale = 0.95f,
                        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
                    )
                    enter with exit
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
                            .fillMaxWidth(0.85f)
                            .height(600.dp)
                            .clickable {
                                scope.launch {
                                    val target = if (rotation.value < 90f) 180f else 0f
                                    rotation.animateTo(
                                        target,
                                        animationSpec = tween(
                                            durationMillis = 400,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                            }
                            .graphicsLayer {
                                rotationY = rotation.value
                                cameraDistance = 8 * density
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isFront) Color(0xFF00796B) else Color(0xFF004D40)
                        ),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = displayText,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                ),
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
            Button(onClick = onBack) { Text("Retour") }
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