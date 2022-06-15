package com.ismailaamassi.dailytasks.feature_auth.presentation.splash

import androidx.compose.runtime.Composable
import com.ismailaamassi.dailytasks.Greeting
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun SplashScreen() {
    Greeting(name = "SplashScreen")
}