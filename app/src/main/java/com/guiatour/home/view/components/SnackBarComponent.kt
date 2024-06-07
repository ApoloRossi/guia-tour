package com.guiatour.home.view.components

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
 fun SnackBarComponent(snackBarHostState: SnackbarHostState, performAction: () -> Unit) {
    LaunchedEffect(snackBarHostState) {
        val result = snackBarHostState.showSnackbar(
            message = "Erro ao carregar os lugares",
            actionLabel = "Tentar novamente",
            duration = SnackbarDuration.Short
        )

        if (result == SnackbarResult.ActionPerformed) {
            performAction()
        }
    }
}