@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.AppTypography
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun NewsHomeContent(
    articles: List<Article>,
    onNewsItemClicked: (Article) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(Sizes.Size200)
    ) {
        items(
            items = articles,
            itemContent = {
                NewsListItem(
                    article = it,
                    onNewsItemClicked = onNewsItemClicked,
                )
            }
        )
    }
}

@Composable
fun NewsListItem(
    article: Article,
    onNewsItemClicked: (Article) -> Unit,
) {
    Card(onClick = { onNewsItemClicked(article) }) {
        Column(modifier = Modifier.padding(Sizes.Size200)) {
            Text(
                text = article.title,
                style = AppTypography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(Sizes.Size100))

            Text(
                text = article.content ?: "",
                style = AppTypography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun NewsListItemPreview() {
    NewsListItem(
        article = ArticleGenerator.generateArticle(),
        onNewsItemClicked = { },
    )
}
