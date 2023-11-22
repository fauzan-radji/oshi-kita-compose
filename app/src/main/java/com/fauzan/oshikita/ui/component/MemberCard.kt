package com.fauzan.oshikita.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fauzan.oshikita.R
import com.fauzan.oshikita.ui.theme.Shapes

@Composable
fun MemberCard(
    id: Int,
    photoUrl: String,
    name: String,
    generation: Int,
    isOshi: Boolean,
    addToOshi: (id: Int, value: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(isOshi) }

    val padding = 16.dp
    OutlinedCard(
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5f / 6f)
                .clip(Shapes.medium)
                .padding(bottom = padding)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(horizontal = padding)
        )

        Text(
            text = pluralStringResource(
                id = R.plurals.generation,
                count = generation,
                generation
            ),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(horizontal = padding)
        )

        Button(
            onClick = {
                addToOshi(id, !isOshi)
                isFavorite = !isFavorite
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.add_to_my_oshi),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberCardPreview() {
    MemberCard(
        id = 1,
        photoUrl = "https://avatars.githubusercontent.com/u/72055502?v=4",
        name = "Fauzan",
        generation = 1,
        isOshi = false,
        addToOshi = {_, _ -> }
    )
}