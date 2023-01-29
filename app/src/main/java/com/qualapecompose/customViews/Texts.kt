package com.qualapecompose.customViews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

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