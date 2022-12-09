@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.ArticleList
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.HomeViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNewsItemClicked: (Article) -> Unit,
) {
    val articles by viewModel.items.collectAsStateWithLifecycle()

    HomeScreenContent(
        articles = articles?.toPersistentList(),
        onNewsItemClicked = onNewsItemClicked
    )
}

@Composable
fun HomeScreenContent(
    articles: ImmutableList<Article>?,
    onNewsItemClicked: (Article) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("News") }) }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding)) {

            ArticleList(
                articles = articles,
                onNewsItemClicked = onNewsItemClicked,
            )
        }
    }
}
