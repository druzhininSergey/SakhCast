package com.example.sakhcast.ui.log_in_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Lock
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sakhcast.Colors
import com.example.sakhcast.HOME_SCREEN
import com.example.sakhcast.R

@Composable
fun LogInScreen(
    navController: NavHostController,
    logInScreenViewModel: LogInScreenViewModel = hiltViewModel()
) {
    val logInScreenState =
        logInScreenViewModel.userDataState.observeAsState(LogInScreenViewModel.UserDataState())
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding()
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 50.dp),
            text = stringResource(id = R.string.sakh_cast),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier.padding(top = 20.dp),
            shape = RoundedCornerShape(10.dp),
            value = login,
            onValueChange = { login = it },
            label = {
                Text(
                    "Логин",
                    color = MaterialTheme.colorScheme.onPrimary,
//                    modifier = Modifier.clip(CircleShape).background(color = Color.Green)
                )
            },
            leadingIcon = { Icon(imageVector = Icons.TwoTone.Person, contentDescription = null) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,

            ),
            shape = RoundedCornerShape(10.dp),
            value = password,
            leadingIcon = { Icon(imageVector = Icons.TwoTone.Lock, contentDescription = null) },
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Пароль", color = MaterialTheme.colorScheme.onPrimary) },
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    logInScreenViewModel.checkUserData(login, password)
                    if (logInScreenState.value.isLogged == true) navController.navigate(HOME_SCREEN)
                }
            ),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) painterResource(id = R.drawable.ic_visibility) else painterResource(
                            id = R.drawable.ic_visibility_off
                        )
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(painter = visibilityIcon, contentDescription = description)
                }
            }
        )
        if (logInScreenState.value.isLogged == false) {
            Text(
                text = "Неверный логин или пароль",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(shape = RoundedCornerShape(10.dp), color = Colors.errorColor)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Button(
            onClick = {
//                logInScreenViewModel.checkUserData(login, password) //TODO убрать коментарий, код-рабочий
                if (logInScreenState.value.isLogged == true) navController.navigate(HOME_SCREEN)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(Colors.blueColor)
        ) {
            Text(text = "Войти")
        }
    }
}