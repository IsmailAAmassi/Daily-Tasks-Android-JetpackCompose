package com.ismailaamassi.dailytasks.feature_auth.presentation.register

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
import com.ismailaamassi.dailytasks.core.presentation.util.asString
import com.ismailaamassi.dailytasks.core.presentation.util.asToast
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.feature_auth.presentation.util.AuthError
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit = { navigator.popBackStack() },
    onNavigateUp: () -> Unit = { navigator.navigateUp() },
) {
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowToast -> uiEvent.uiText.asToast(context)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.uiText.asString(context)
                    )
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
                text = stringResource(id = R.string.screen_register),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = usernameState.text,
                hint = stringResource(id = R.string.hint_username),
                keyboardType = KeyboardType.Text,
                isLastField = false,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredUsername(it))
                },
                error = when (usernameState.error) {
                    is AuthError.InputTooShort -> stringResource(id = R.string.error_input_too_short)
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = emailState.text,
                hint = stringResource(id = R.string.hint_email),
                keyboardType = KeyboardType.Email,
                isLastField = false,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredEmail(it))
                },
                error = when (emailState.error) {
                    is AuthError.InvalidEmail -> stringResource(id = R.string.error_not_a_valid_email)
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                }
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = passwordState.text,
                hint = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                isPasswordVisible = passwordState.isPasswordVisible,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                error = when (emailState.error) {
                    is AuthError.InvalidPassword -> stringResource(id = R.string.error_not_a_valid_password)
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                }
            )

            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardPrimaryButton(text = stringResource(id = R.string.screen_register)) {
                viewModel.onEvent(RegisterEvent.Register)
            }

            Spacer(modifier = Modifier.height(SpaceLarge))
            if (state.isLoading) StandardLoadingAnimation(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    onNavigateUp()
                },
            style = MaterialTheme.typography.body1,
            text = buildAnnotatedString {
                append(stringResource(R.string.register_you_have_account))
                withStyle(
                    style = SpanStyle(
                        color = SecondaryColor,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(R.string.screen_login))
                }
            }
        )
    }
}