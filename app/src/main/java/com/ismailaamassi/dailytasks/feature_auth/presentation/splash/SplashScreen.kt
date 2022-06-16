package com.ismailaamassi.dailytasks.feature_auth.presentation.splash

import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.feature_auth.presentation.destinations.DirectionDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit = {
        navigator.popBackStack()
    },
    onNavigate: (DirectionDestination) -> Unit = {
        navigator.navigate(it)
    },
) {

    val scale = remember { Animatable(0f) }
    val overshootInterpolator = remember { OvershootInterpolator(2.0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onPopBackStack()
                    onNavigate(event.destination)
                }
                else -> Unit
            }
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.scale(scale.value),
                painter = painterResource(id = R.drawable.logo_128),
                contentDescription = stringResource(
                    id = R.string.app_name
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

}