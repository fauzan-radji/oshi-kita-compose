package com.fauzan.oshikita.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fauzan.oshikita.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.self),
            contentDescription = stringResource(R.string.my_name),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.my_name),
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.my_email)
        )
    }
}