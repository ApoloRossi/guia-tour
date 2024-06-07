package com.guiatour.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.home.viewModel.HomeUIState

@Composable
fun ErrorComponent(homeUIState: HomeUIState, tryAgainClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(0.dp)

    ) {
        AnimationPreLoader(
            Modifier
                .size(200.dp)
                .align(
                    Alignment.CenterHorizontally
                ))

        Text(
            text = stringResource((homeUIState as HomeUIState.Error).errorMessage),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Button(onClick = tryAgainClick::invoke,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)) {
            Text(text = "Tentar novamente", color = Color.White)
        }
    }

}