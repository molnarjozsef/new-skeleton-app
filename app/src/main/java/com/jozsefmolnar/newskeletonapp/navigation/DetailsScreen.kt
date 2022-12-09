@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.AppTypography
import com.jozsefmolnar.newskeletonapp.ui.theme.Constants
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailsScreen(item: StateFlow<Article?>) {
    val article = item.collectAsStateWithLifecycle()

    article.value?.let {
        DetailsScreenContent(article = it)
    }
}

@Composable
fun DetailsScreenContent(article: Article) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(Modifier.padding(Sizes.Size300)) {
            Card {
                GlideImage(
                    imageModel = { article.urlToImage ?: "" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(Constants.AspectRatio),
                    previewPlaceholder = R.drawable.ic_launcher_background
                )
            }

            Spacer(Modifier.height(Sizes.Size300))

            Column {
                Text(
                    text = article.title,
                    style = AppTypography.headlineMedium,
                )

                Spacer(Modifier.height(Sizes.Size200))

                Text(
                    text = article.description ?: "DESCRIPTION",
                    style = AppTypography.bodyLarge,
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreenContent(article = ArticleGenerator.generateArticle())
}
