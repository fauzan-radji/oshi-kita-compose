package com.fauzan.oshikita.ui.screen.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fauzan.oshikita.R
import com.fauzan.oshikita.di.Injection
import com.fauzan.oshikita.ui.ViewModelFactory
import com.fauzan.oshikita.ui.theme.OshiKitaTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val member by viewModel.member.collectAsState()

    member?.let {
        DetailContent(
            name = it.name,
            nicknames = it.nicknames,
            fanbase = it.fanbase,
            generation = it.generation,
            jiko = it.jiko,
            description = it.description,
            photoUrl = it.photoUrl,
            modifier = modifier
        )
    }
}

@Composable
fun DetailContent(
    name: String,
    nicknames: List<String>,
    fanbase: String,
    generation: Int,
    jiko: String,
    description: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    val textStyle = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(
                R.string.jkt48, pluralStringResource(
                    id = R.plurals.generation,
                    count = generation,
                    generation
                )
            ),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            AsyncImage(
                model = photoUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(2f / 3f)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = MaterialTheme.shapes.medium
                    )
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.full_name),
                    style = textStyle
                )
                Text(text = name)

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(R.string.nickname),
                    style = textStyle
                )
                Text(text = nicknames.joinToString(", "))

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(R.string.fanbase),
                    style = textStyle
                )
                Text(text = fanbase)
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "\"$jiko\"",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(description)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun DetailPreview() {
    OshiKitaTheme {
        DetailContent(
            photoUrl = "https://i.imgur.com/4r6mZ0F.jpeg",
            name = "Fauzan",
            generation = 1,
            nicknames = listOf("Zan", "Jan", "Cok", "Papaw"),
            fanbase = "Zanfans",
            jiko = "Seperti biasa, sekian dan terima kasih.",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl.",
        )
    }
}