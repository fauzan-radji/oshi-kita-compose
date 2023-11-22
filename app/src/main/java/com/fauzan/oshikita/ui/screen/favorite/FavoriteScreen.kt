package com.fauzan.oshikita.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fauzan.oshikita.data.FakeDataSource
import com.fauzan.oshikita.di.Injection
import com.fauzan.oshikita.model.Member
import com.fauzan.oshikita.ui.ViewModelFactory
import com.fauzan.oshikita.ui.component.MemberGrid
import com.fauzan.oshikita.ui.component.SearchBar
import com.fauzan.oshikita.ui.theme.OshiKitaTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (id: Int) -> Unit
) {
    val members by viewModel.oshi.collectAsState()
    val query by viewModel.query

    FavoriteContent(
        members = members,
        modifier = modifier,
        navigateToDetail = navigateToDetail,
        addToOshi = viewModel::setOshi,
        query = query,
        onQueryChange = viewModel::setQuery
    )
}

@Composable
fun FavoriteContent(
    members: List<Member>,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (id: Int) -> Unit,
    addToOshi: (id: Int, value: Boolean) -> Unit,
) {
    Column {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )

        MemberGrid(
            members = members,
            modifier = modifier,
            navigateToDetail = navigateToDetail,
            addToOshi = addToOshi
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritePreview() {
    OshiKitaTheme {
        FavoriteContent(
            members = FakeDataSource.dummyMembers,
            navigateToDetail = {},
            addToOshi = { _, _ -> },
            query = "",
            onQueryChange = {}
        )
    }
}