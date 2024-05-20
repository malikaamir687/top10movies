package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.model.MovieClass
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme{
                Surface (modifier = Modifier.fillMaxSize()){
                   MovieApp()
                }
            }

        }
    }
}

@Composable
fun MovieCard(details: MovieClass){
    Card(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 400.dp, max = 600.dp)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally  ,modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium,
                    dampingRatio = Spring.DampingRatioLowBouncy

                )
            )
        ) {
            Text(text = "movie:${details.number}", style = MaterialTheme.typography.labelMedium)
            Text(text = stringResource(id = details.nameRes), style = MaterialTheme.typography.titleMedium, color = Color(
                0xFF022833
            )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(painter = painterResource(id = details.imageRes), contentDescription = null, contentScale = ContentScale.Crop ,modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.medium))
var expand by remember {
    mutableStateOf(false)
}
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if (expand) "less" else "more..", color = if (expand)  Color.Gray else Color(
                0xFF246CE9
            ),style = MaterialTheme.typography.labelSmall, modifier = Modifier
                .clickable { expand = !expand }
                .align(Alignment.Start))


            if (expand) {

                Lower(details = details)
            
        }

        }
    }
}
@Composable
fun Lower(details: MovieClass){


    val scrollstate = remember {
        ScrollState(0)
    }
    Text(text = stringResource(id = details.moreRes) , style = MaterialTheme.typography.bodyMedium, modifier = Modifier.verticalScroll(scrollstate))

}
@Composable
@Preview
fun MovieApp(){
    val movieList: List<MovieClass> = listOf(

        MovieClass(1, R.string.name1,R.drawable.forest_gmp,R.string.mname1),
        MovieClass(2, R.string.name2,R.drawable.the_godfather,R.string.mname2),
        MovieClass(3, R.string.name3,R.drawable.the_dark_knight,R.string.mname3),
        MovieClass(4, R.string.name4,R.drawable.the_shawshank_redemption,R.string.mname4),
        MovieClass(5, R.string.name5,R.drawable.star_wars_5,R.string.mname5),
        MovieClass(6, R.string.name6,R.drawable.pulp_fiction,R.string.mname6),
        MovieClass(7, R.string.name7,R.drawable.the_lord_of_the_rings,R.string.mname7),
        MovieClass(8, R.string.name8,R.drawable.back_to_the_future,R.string.mname8),
        MovieClass(9, R.string.name9,R.drawable.star_wars_4,R.string.mname9),
        MovieClass(10, R.string.name10,R.drawable.avengersinfinitywar,R.string.mname10)


    )

Scaffold (topBar = { Text(text = "Top 10 Movies", style = MaterialTheme.typography.titleLarge, color = Color.White ,modifier = Modifier
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.secondary), textAlign = TextAlign.Center)} ){


    LazyColumn(contentPadding = it ) {

        items(movieList) { movieList ->
            MovieCard(details = movieList)

        }
    }
    }




}