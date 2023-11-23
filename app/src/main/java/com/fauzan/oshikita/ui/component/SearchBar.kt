package com.fauzan.oshikita.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fauzan.oshikita.R
import com.fauzan.oshikita.ui.theme.OshiKitaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    cursorColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    androidx.compose.material3.SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_your_oshi),
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.search_your_oshi))
        },
        trailingIcon = {
            if(query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.search_your_oshi),
                    modifier = Modifier.clickable { onQueryChange("") }
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = backgroundColor,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                cursorColor = cursorColor,
            ),
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .heightIn(48.dp)
            .fillMaxWidth()
    ) {}
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    OshiKitaTheme {
        SearchBar(query = "", onQueryChange = {})
    }
}