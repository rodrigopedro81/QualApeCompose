package com.qualapecompose.app.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshotFlow

@Composable
fun <T> MutableState<T>.OnStateChanged(callback: (newState: T) -> Unit) {
    val state = this
    LaunchedEffect(state) {
        snapshotFlow { state.value }.collect {
            callback.invoke(it)
        }
    }
}

@Composable
fun <T> List<MutableState<T>>.OnEachStateChanged(
    callback: (observedStates: List<MutableState<T>>, newState: T) -> Unit
) {
    val observedStates = this
    forEach {
        val state = it
        LaunchedEffect(state) {
            snapshotFlow { state.value }.collect { newState ->
                callback.invoke(observedStates, newState)
            }
        }
    }
}