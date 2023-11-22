package com.fauzan.oshikita.ui.screen.home

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
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (id: Int) -> Unit
) {
    val members by viewModel.members.collectAsState()
    val query by viewModel.query

    HomeContent(
        members = members,
        modifier = modifier,
        navigateToDetail = navigateToDetail,
        addToOshi = viewModel::setOshi,
        query = query,
        onQueryChange = viewModel::setQuery
    )
}

@Composable
fun HomeContent(
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
fun HomePreview() {
    OshiKitaTheme {
        HomeContent(
            members = FakeDataSource.dummyMembers,
            navigateToDetail = {},
            addToOshi = { _, _ -> },
            query = "",
            onQueryChange = {}
        )
    }
}