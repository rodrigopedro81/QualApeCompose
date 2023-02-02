package com.qualapecompose.designSystem.customWidgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qualapecompose.app.screens.login.TextFieldState
import com.qualapecompose.ui.theme.*

@Preview
@Composable
fun MainEditTextPreview() {
    QualApeComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val textFieldState = TextFieldState(
                onTextChange = { state, newText ->
                }
            )
            MainEditText(textFieldState = textFieldState)
        }
    }
}

@Composable
fun MainEditText(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    label: String? = null,
    hint: String? = null,
    startIcon: Int? = null,
    endIcon: Int? = null,
) {
    val shape = RoundedCornerShape(30.dp)
    val defaultColor = MaterialTheme.colors.secondary
    val currentColor = remember {
        derivedStateOf {
            if (textFieldState.isValid.value || textFieldState.text.value.isEmpty())
                defaultColor
            else
                errorColor
        }
    }
    Column(modifier) {
        label?.let { Label(it, currentColor.value) }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .clip(shape)
                .background(white)
                .border(1.dp, currentColor.value, shape)
                .height(44.dp)
                .fillMaxWidth(),
            value = textFieldState.text.value,
            onValueChange = { textFieldState.onTextChange.invoke(textFieldState, it) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                color = currentColor.value,
                fontSize = 16.sp
            )
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    Icon(painterResource(id = it), null, tint = currentColor.value)
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box {
                    hint?.let {
                        if (textFieldState.text.value.isEmpty()) {
                            Hint(hint = it)
                        }
                    }
                    innerTextField()
                }
                endIcon?.let {
                    Icon(painterResource(it), null, tint = currentColor.value)
                }
            }
        }
    }
}

@Composable
fun Label(text: String, color: Color) {
    Text(
        modifier = Modifier.offset(25.dp),
        text = text,
        color = color,
        style = TextStyle(fontSize = 12.sp)
    )
}

@Composable
fun Hint(hint: String) {
    Text(
        text = hint,
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onBackground,
            fontSize = 16.sp
        )
    )
}
