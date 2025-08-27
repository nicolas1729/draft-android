package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body() {
    var nameString by remember { mutableStateOf("") }
    var surnameString by remember { mutableStateOf( "") }
    var passwordString by remember { mutableStateOf("") }
    var isKotlinCool by remember { mutableStateOf(true) }
    var sliderValue by remember { mutableFloatStateOf(0.5F) }
    var compoteIndex by remember { mutableIntStateOf(0) }
    var number by remember { mutableIntStateOf(0) }
    val modifier: Modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
    val manager = LocalFocusManager.current
    val compotes = listOf<String>("Pomme", "Banane", "Poire", "Abricot", "Peche")

    Surface(modifier = Modifier.fillMaxSize(), onClick = { manager.clearFocus()}) {
        Column {
            NameTextField(nameString = nameString, onNameChanged = {nameString = it}, modifier = modifier, manager = manager)
            SecureTextField(password = passwordString, onChanged = { passwordString = it}, modifer = modifier, manager = manager)
            SurnameTextField(surname = surnameString, onChanged = { surnameString = it}, modifier = modifier, manager = manager)
            KotlinToggle(checked = isKotlinCool, onChange = {isKotlinCool = it})
            Myslider(value = sliderValue, onChanged = {sliderValue = it}, onFinished = {println("Nous avons fini")})
            Text(text = "Valeur du Slider: ${sliderValue.toInt()}")
            Radios(index = compoteIndex, list = compotes, modifier = modifier, onClick = { compoteIndex = it})
            Stepper(modifier = modifier, number = number, onTap = {number = it})
        }
    }

}

@Composable
fun Stepper(modifier: Modifier, number: Int, onTap: (Int) -> Unit) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Nombre: $number")
        Surface(modifier = Modifier
            .height(45.dp)
            .width(100.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(percent = 15)
        ) {
            Row {
                IconButton(onClick = { onTap(number + 1)}) {
                    Text("+")
                }
                IconButton(onClick = { onTap(number - 1) }) {
                    Text(text = "-")
                }
            }
        }
    }
}

@Composable
fun Radios(index: Int, list: List<String>, modifier: Modifier, onClick: (Int) -> Unit) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
        for (int in list.indices) {
            Column() {
                RadioButton(
                    selected = index == int,
                    onClick = { onClick(int) }
                )
                Text(text = list.get(int))
            }

        }
    }
}

@Composable
fun Myslider(value: Float, onChanged: (Float) -> Unit, onFinished: () -> Unit) {
    Slider(
        value = value,
        onValueChange = onChanged,
        onValueChangeFinished = onFinished,
        valueRange = 0.0F..100.0F,
        steps = 10,
        colors = SliderDefaults.colors(

        )

    )
}

@Composable
fun KotlinToggle(checked: Boolean, onChange: (Boolean) -> Unit) {
    Row() {
        Text(text = if (checked) "Kotlin est Cool" else "Je préfère Dart")
        Switch(
            checked = checked,
            onCheckedChange = onChange,
            colors = SwitchDefaults.colors(
                /*checkedTrackColor = Color.Blue,
                checkedThumbColor = Color.Blue,
                checkedTrackAlpha = 0.5F,
                uncheckedThumbColor = Color.Green,
                uncheckedTrackColor = Color.Magenta*/
            )
        )
    }
}

@Composable
fun SurnameTextField(surname: String, onChanged: (String) -> Unit, modifier: Modifier, manager: FocusManager) {
    val error = (surname == "" || surname.length < 3)
    Column() {
        OutlinedTextField(
            value = surname,
            onValueChange = onChanged,
            modifier = modifier,
            label = { Text("Entrez un prénom")},
            placeholder = { Text(text = "Quel est votre prénom?")},
            isError = error,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { manager.clearFocus()}
            )
        )
        if (error) {
            Text(
                text = "Le prénom est trop court",
                style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.error
            )

        }
    }

}

@Composable
fun NameTextField(nameString: String, onNameChanged: (String) -> Unit, modifier: Modifier, manager: FocusManager) {
    TextField(
        value = nameString,
        onValueChange = onNameChanged,
        modifier = modifier,
        label = { Text("Entrez votre nom")},
        singleLine = true,
        enabled = true,
        //readOnly = true,
        placeholder = { Text("Nom inconnu")},
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        trailingIcon = { IconButton(onClick = { println("Click") }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null)
        }},
        isError = (nameString == ""),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                manager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

@Composable
fun SecureTextField(
    password: String,
    onChanged: (String) -> Unit,
    modifer: Modifier,
    manager: FocusManager
) {
    var isSecure by remember { mutableStateOf(true) }
    OutlinedTextField(
        value = password,
        onValueChange = onChanged,
        modifier = modifer,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {manager.moveFocus(FocusDirection.Down)}
        ),
        label = { Text(text = "Mot de passe")},
        visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(
                onClick = { isSecure = !isSecure }) {
                Icon(
                    imageVector = if (isSecure) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Body()
    }
}