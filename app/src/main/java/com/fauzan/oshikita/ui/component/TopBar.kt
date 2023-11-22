package com.fauzan.oshikita.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fauzan.oshikita.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit) = {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.navigate_back)
        )
    },
    trailingIcon: @Composable (() -> Unit) = {},
    leadingIconOnClick: () -> Unit = {},
    trailingIconOnClick: () -> Unit = {},
    content : @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = content,
        navigationIcon = {
            IconButton(onClick = leadingIconOnClick, content = leadingIcon)
        },
        actions = {
            if (trailingIcon != {}) {
                IconButton(onClick = trailingIconOnClick, content = trailingIcon)
            }
        },
        modifier = modifier,
    )
}