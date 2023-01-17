package com.mx.android.heros.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mx.android.domain.modules.hero.Hero
import com.mx.android.domain.modules.hero.HeroThumbnail
import com.mx.android.heros.viewModel.HeroViewModel

@Composable
fun HeroScreen() {
    val heroViewModel = hiltViewModel<HeroViewModel>()
    heroViewModel.setUp()
    HeroRecyclerView(viewModel = heroViewModel)
}

@Composable
fun HeroRecyclerView(viewModel: HeroViewModel) {
    val vmHeros by viewModel.heros.collectAsState(initial = emptyList())
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(vmHeros) { hero ->
                HeroItem(
                    viewModel,
                    hero = hero,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        })
}

@Composable
fun HeroItem(viewModel: HeroViewModel, hero: Hero, modifier: Modifier) {
    var vmIsFavorite by rememberSaveable { mutableStateOf(hero.isFavorite) }
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(300.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = "${hero.thumbnail.path}.${hero.thumbnail.extension}",
                    contentDescription = "hola",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Card(
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .align(TopEnd)
                        .padding(12.dp)
                ) {
                    IconButton(
                        onClick = {
                            hero.isFavorite = !hero.isFavorite
                            vmIsFavorite = hero.isFavorite
                            viewModel.changeHeroFavoriteStatus(hero)
                        }
                    ) {
                        val color by animateColorAsState(
                            targetValue = if (vmIsFavorite) Color.Red else Color.Black,
                            animationSpec = tween(durationMillis = 350)
                        )
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "hero_favorite",
                            modifier = Modifier.padding(6.dp),
                            tint = color
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = hero.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 1.dp)
            )
            Text(
                text = hero.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 4.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HeroTest() {
    val heroViewModel = hiltViewModel<HeroViewModel>()
    Column {
        HeroItem(
            heroViewModel,
            hero = Hero(
                123456,
                "Iron Man",
                "Estae es un ejemplo de descripcion para iron man bla bla bla,",
                HeroThumbnail("", ""),
                isFavorite = false
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}