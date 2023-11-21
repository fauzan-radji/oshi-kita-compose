package com.fauzan.oshikita.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fauzan.oshikita.data.FakeDataSource
import com.fauzan.oshikita.di.Injection
import com.fauzan.oshikita.model.Member
import com.fauzan.oshikita.ui.ViewModelFactory
import com.fauzan.oshikita.ui.common.UiState
import com.fauzan.oshikita.ui.component.MemberCard
import com.fauzan.oshikita.ui.theme.OshiKitaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (id: Int) -> Unit
) {
    val mContext = LocalContext.current
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMember()
            }

            is UiState.Success -> {
                HomeContent(
                    members = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    addToOshi = { id, value ->
                        Toast.makeText(mContext, "Add to Oshi $id $value", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    members: List<Member>,
    modifier: Modifier = Modifier,
    navigateToDetail: (id: Int) -> Unit,
    addToOshi: (id: Int, value: Boolean) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(members, key = { it.id }) { member ->
            MemberCard(
                id = member.id,
                photoUrl = member.photoUrl,
                name = member.name,
                generation = member.generation,
                isOshi = member.isOshi,
                addToOshi = addToOshi,
                modifier = Modifier.clickable {
                    navigateToDetail(member.id)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    OshiKitaTheme {
        HomeContent(members = FakeDataSource.dummyMembers, navigateToDetail = {}, addToOshi = { _, _ -> })
    }
}