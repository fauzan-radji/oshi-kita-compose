package com.fauzan.oshikita.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fauzan.oshikita.R
import com.fauzan.oshikita.ui.theme.Shapes

@Composable
fun MemberCard(
    photoUrl: String,
    name: String,
    generation: Int,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5f / 6f)
                .clip(Shapes.medium)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
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
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MemberCardPreview() {
    MemberCard(
        photoUrl = "https://avatars.githubusercontent.com/u/72055502?v=4",
        name = "Fauzan",
        generation = 1
    )
}