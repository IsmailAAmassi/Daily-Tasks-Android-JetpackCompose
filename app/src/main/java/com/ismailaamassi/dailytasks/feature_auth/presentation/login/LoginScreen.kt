package com.ismailaamassi.dailytasks.feature_auth.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.presentation.components.StandardLoadingAnimation
import com.ismailaamassi.dailytasks.core.presentation.components.StandardPrimaryButton
import com.ismailaamassi.dailytasks.core.presentation.components.StandardTextField
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SecondaryColor
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceLarge
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceMedium
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceSmall
import com.ismailaamassi.dailytasks.core.presentation.util.asString
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.DirectionDestination
import com.ismailaamassi.dailytasks.destinations.ForgotPasswordScreenDestination
import com.ismailaamassi.dailytasks.destinations.RegisterScreenDestination
import com.ismailaamassi.dailytasks.destinations.TaskListScreenDestination

import com.ismailaamassi.dailytasks.feature_auth.presentation.util.AuthError
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit = {
        navigator.popBackStack()
    },
    onNavigate: (DirectionDestination) -> Unit = {
        navigator.navigate(it)
    },
) {

    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.destination)
                }
                is UiEvent.OnLogin -> {
                    navigator.popBackStack()
                    navigator.navigate(TaskListScreenDestination)
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.screen_login),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = emailState.text,
                hint = stringResource(id = R.string.hint_email),
                keyboardType = KeyboardType.Email,
                isLastField = false,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                error = when (emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = passwordState.text,
                hint = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                isPasswordVisible = passwordState.isPasswordVisible,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                error = when (passwordState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                onPasswordToggleClick = {
                    viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                }
            )

            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        onNavigate(ForgotPasswordScreenDestination)
                    },
                text = stringResource(R.string.login_forgot_password)
            )

            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardPrimaryButton(text = stringResource(id = R.string.screen_login)) {
                viewModel.onEvent(LoginEvent.Login)
            }

            Spacer(modifier = Modifier.height(SpaceLarge))

            if (state.isLoading) StandardLoadingAnimation(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    onNavigate(RegisterScreenDestination)
                },
            style = MaterialTheme.typography.body1,
            text = buildAnnotatedString {
                append(stringResource(R.string.login_dont_have_account))
                withStyle(
                    style = SpanStyle(
                        color = SecondaryColor,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(R.string.screen_register))
                }
            }
        )
    }

}