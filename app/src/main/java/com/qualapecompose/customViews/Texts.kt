package com.qualapecompose.customViews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qualapecompose.ui.theme.QualApeComposeTheme

@Composable
fun SimpleHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun SimpleCaption(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview
@Composable
fun TextsPreview() {
    QualApeComposeTheme {
        WhiteBoard {
            SimpleHeader("Esse é o cabeçalho simples")
            VerticalSpacer(dp = 24.dp)
            SimpleCaption(text = "Essa é a legenda simples")
        }
    }
}