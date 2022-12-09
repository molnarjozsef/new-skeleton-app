@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jozsefmolnar.newskeletonapp.NewsHomeContent
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@Composable
fun MainScreen(
    data: StateFlow<List<Article>?>,
    refresh: () -> Unit,
    onNewsItemClicked: (Article) -> Unit,
) {
    val info = data.map { it?.firstOrNull()?.content ?: "emptyness" }.collectAsState("initial")
    val articles = data.collectAsState()
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = info.value)
        NewsHomeContent(
            articles = articles.value ?: emptyList(),
            onNewsItemClicked = onNewsItemClicked,
        )
    }
}
