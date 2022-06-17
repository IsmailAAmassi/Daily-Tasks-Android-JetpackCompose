@file:OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)

package com.ismailaamassi.dailytasks.feature_auth.presentation.on_boarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.DirectionDestination
import com.ismailaamassi.dailytasks.feature_auth.domain.model.OnBoardingPage
import com.ismailaamassi.dailytasks.feature_auth.presentation.on_boarding.components.FinishButton
import com.ismailaamassi.dailytasks.feature_auth.presentation.on_boarding.components.PagerScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest


@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
    viewModel: OnBoardingViewModel = hiltViewModel(),
    onNavigate: (DirectionDestination) -> Unit = {
        navigator.navigate(it)
    }
) {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    navigator.popBackStack()
                    onNavigate(uiEvent.destination)
                }
                is UiEvent.ShowSnackbar -> TODO()
                else -> Unit
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            viewModel.onEvent(OnBoardingEvent.SaveOnBoardingState(true))
        }
    }
}

