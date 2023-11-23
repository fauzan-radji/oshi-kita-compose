package com.fauzan.oshikita.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fauzan.oshikita.R
import com.fauzan.oshikita.ui.theme.OshiKitaTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIconOnClick: () -> Unit = {},
    trailingIconOnClick: () -> Unit = {},
    content : @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            IconButton(
                onClick = leadingIconOnClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                content = leadingIcon
            )
        }
        Box(modifier = Modifier.weight(1f).padding(bottom = 8.dp)) {
            content()
        }
        if (trailingIcon != null) {
            IconButton(
                onClick = trailingIconOnClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = Color.Transparent
                ),
                content = trailingIcon
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    OshiKitaTheme {
        TopBar(
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back)
                )
            },
            leadingIconOnClick = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            },
            trailingIconOnClick = {}
        ) {
            TopBarTitle(text = stringResource(R.string.app_name))
        }
    }
}