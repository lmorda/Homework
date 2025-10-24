package com.lmorda.homework.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lmorda.homework.R
import com.lmorda.homework.ui.login.LoginContract.Event
import com.lmorda.homework.ui.login.LoginContract.Event.Internal.OnLogin
import com.lmorda.homework.ui.login.LoginContract.State
import com.lmorda.homework.ui.login.LoginContract.State.Initial
import com.lmorda.homework.ui.login.LoginContract.State.LoggedIn
import com.lmorda.homework.ui.login.LoginContract.State.LoggingIn
import com.lmorda.homework.ui.login.LoginContract.State.LoginError
import com.lmorda.homework.ui.shared.HomeworkNextPageIndicator
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.outlinedTextButtonColors
import com.lmorda.homework.ui.theme.sizeDefault
import com.lmorda.homework.ui.theme.sizeLarge
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.sizeXXLarge
import kotlinx.coroutines.launch

@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel,
    onNavigateToAccountSelect: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.state.collectAsState()
    when (state) {
        is LoggedIn -> onNavigateToAccountSelect()
        else -> LoginScreen(
            state = state,
            push = viewModel::push,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun LoginScreen(
    state: State,
    push: (Event) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        when (state) {
            is LoggingIn -> HomeworkNextPageIndicator()
            else -> LoginScreenContent(
                push = push,
                state = state,
                snackbarHostState = snackbarHostState,
            )
        }
    }
}

@Composable
private fun LoginScreenContent(
    push: (Event) -> Unit,
    state: State,
    snackbarHostState: SnackbarHostState,
) {
    var emailOrUsername by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeDefault)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = stringResource(R.string.accessibility_fleetio_logo),
            modifier = Modifier
                .size(dimensionResource(R.dimen.login_logo))
                .padding(top = sizeLarge)
        )

        Text(
            text = stringResource(R.string.email_or_username),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = sizeDefault, bottom = sizeSmall)
                .align(Alignment.Start),
        )

        OutlinedTextField(
            value = emailOrUsername,
            onValueChange = { emailOrUsername = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = outlinedTextButtonColors(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        Text(
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = sizeDefault, bottom = sizeSmall)
                .align(Alignment.Start),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = outlinedTextButtonColors(),
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                val iconImage = if (passwordVisibility) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = iconImage,
                        contentDescription = stringResource(R.string.accessibility_toggle_password),
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    push(OnLogin(username = emailOrUsername, password = password))
                }
            )
        )

        LoginButton(
            push = push,
            emailOrUsername = emailOrUsername,
            password = password,
        )
        if (state is LoginError) {
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.login_error),
                    )
                }
            }
        }
    }
}

@Composable
private fun LoginButton(
    push: (Event) -> Unit,
    emailOrUsername: String,
    password: String,
) {
    Button(
        onClick = {
            push(OnLogin(username = emailOrUsername, password = password))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = sizeXLarge)
            .height(sizeXXLarge),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Text(
            text = stringResource(R.string.log_in),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
@DayAndNightPreview
private fun LoginScreenPreview() {
    HomeworkTheme {
        LoginScreen(
            state = Initial,
            push = {},
            snackbarHostState = remember { SnackbarHostState() },
        )
    }
}
