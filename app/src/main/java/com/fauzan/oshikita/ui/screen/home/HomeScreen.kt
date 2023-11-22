package com.fauzan.oshikita.ui.screen.home

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

    HomeContent(
        members = members,
        modifier = modifier,
        navigateToDetail = navigateToDetail,
        addToOshi = viewModel::setOshi
    )
}

@Composable
fun HomeContent(
    members: List<Member>,
    modifier: Modifier = Modifier,
    navigateToDetail: (id: Int) -> Unit,
    addToOshi: (id: Int, value: Boolean) -> Unit,
) {
    MemberGrid(
        members = members,
        modifier = modifier,
        navigateToDetail = navigateToDetail,
        addToOshi = addToOshi
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    OshiKitaTheme {
        HomeContent(members = FakeDataSource.dummyMembers, navigateToDetail = {}, addToOshi = { _, _ -> })
    }
}