package com.qualapecompose.customViews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qualapecompose.R
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
            MainEditText(
                label = "Nome",
                leadingIcon = painterResource(id = R.drawable.ic_email),
                placeholderText = "Digite seu nome"
            ) {
                it.length > 6
            }
        }
    }
}


@Composable
fun MainEditText(
    modifier: Modifier = Modifier,
    label: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    placeholderText: String? = null,
    validation: ((String) -> Boolean)? = null
) {
    val text = remember { mutableStateOf(TextFieldValue()) }
    val secondaryColor = MaterialTheme.colors.secondary
    val onBackgroundColor = MaterialTheme.colors.onBackground
    val currentColor = remember { mutableStateOf(secondaryColor) }
    val shape = RoundedCornerShape(30.dp)
    Column(modifier) {
        label?.let {
            Text(
                modifier = Modifier.offset(25.dp),
                text = it,
                color = currentColor.value,
                style = TextStyle(fontSize = 12.sp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .clip(shape)
                .background(white)
                .border(1.dp, currentColor.value, shape)
                .height(44.dp)
                .fillMaxWidth(),
            value = text.value,
            onValueChange = { textFieldValue ->
                text.value = textFieldValue
                validation?.let { textIsValid ->
                    currentColor.value =
                        if (textIsValid(textFieldValue.text) || text.value.text.isEmpty())
                            secondaryColor else errorColor
                }
            },
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
                leadingIcon?.let {
                    Icon(leadingIcon, null, tint = currentColor.value)
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box {
                    if (text.value.text.isEmpty() && placeholderText != null) {
                        Text(
                            placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = onBackgroundColor,
                                fontSize = 16.sp
                            )
                        )
                    }
                    innerTextField()
                }
                trailingIcon?.let {
                    Icon(trailingIcon, null, tint = currentColor.value)
                }
            }
        }
    }

}