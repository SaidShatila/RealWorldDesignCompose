package com.example.composetestlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetestlab.ui.theme.ComposeTestlabTheme

val dummyListAlign = listOf(
    "Align your body",
    "Align your mind",
    "Align your life",
    "Align your body",
)

val dummyListGrid = listOf(
    "Short mantras",
    "Stress and anxiety",
    "Overwhelmed",
    "Nature meditations",
    "Self-massage",
    "Nightly wind down",
)

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestlabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSizeClass = calculateWindowSizeClass(activity = this@MainActivity)
                    MySootheApp(windowSizeClass = windowSizeClass)
                }
            }
        }
    }


    @Composable
    private fun MySootheApp(windowSizeClass: WindowSizeClass) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                BottomSheetNavWithHome(Modifier.padding(bottom = 56.dp))
            }

            WindowWidthSizeClass.Expanded -> {
                SootheNavigationRailWithHome()
            }
        }
    }

    @Composable
    private fun SootheNavigationRailWithHome(modifier: Modifier = Modifier) {
        Row {
            SootheNavigationRail(modifier)
            MainHomeScreen(modifier)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun BottomSheetNavWithHome(modifier: Modifier) {
        Scaffold(bottomBar = { SootheBottomNavigation(modifier = modifier) }) { padding ->
            MainHomeScreen(Modifier.padding(padding))
        }
    }


    @Composable
    fun MainHomeScreen(modifier: Modifier) {
        Column(modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(Modifier.padding(horizontal = 16.dp))
            HomeSection(title = R.string.title_align_your_body) {
                LazyRowAlignYourBody()
            }
            HomeSection(title = R.string.title_favorite_collections) {
                LazyFavoriteCollectionGrid()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    @Composable
    fun HomeSection(
        @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(selected = true, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
        },
            label = { Text(text = "Home") })

        NavigationBarItem(selected = false, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
            label = { Text(text = "Profile") })
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                selected = true,
                onClick = {},
                icon = { Icon(imageVector = Icons.Default.Home, null) },
                label = { Text(text = "Home") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = false,
                onClick = {},
                icon = { Icon(imageVector = Icons.Default.Person, null) },
                label = { Text(text = "Profile") }
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(value = "", onValueChange = {}, leadingIcon = {
        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
    }, colors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
        focusedBorderColor = MaterialTheme.colorScheme.surface
    ), placeholder = {
        Text(text = stringResource(id = R.string.placeholder_search))
    }, modifier = modifier
        .fillMaxWidth()
        .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier.padding(8.dp), itemValue: String = "Align your body"
) {
    Column(
        modifier = modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = itemValue, modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            ), style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier.padding(8.dp), textItem: String = "Short mantras"
) {
    Surface(
        shape = MaterialTheme.shapes.medium, modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = textItem,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun LazyRowAlignYourBody(modifier: Modifier = Modifier) {
    LazyRow(
        content = {
            items(dummyListAlign.size) { index ->
                AlignYourBodyElement(itemValue = dummyListAlign[index])
            }
        },
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    )
}

@Composable
fun LazyFavoriteCollectionGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(dummyListGrid.size) { item ->
            FavoriteCollectionCard(
                modifier = Modifier.height(80.dp), textItem = dummyListGrid[item]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTestlabTheme {
        SearchBar()
        LazyRowAlignYourBody()
        FavoriteCollectionCard()
        LazyFavoriteCollectionGrid()
    }
}