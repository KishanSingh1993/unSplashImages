package `in`.eduforyou.unsplashimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.livedata.observeAsState


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            ImageRepository(
                Retrofit.Builder()
                    .baseUrl("https://api.unsplash.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UnsplashService::class.java)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ImageGridList(viewModel)
        }
        viewModel.fetchRandomImages("wmBweE8cBqQR-XmyjU93JwXJdkzGRDqdtuszdXBElJc", 10000)
    }
}


@Composable
fun ImageList(viewModel: MainViewModel) {
    val images by viewModel.images.observeAsState(emptyList())

    LazyColumn {
        items(images) { image ->
            Image(
                painter = rememberImagePainter(data = image.urls.regular, builder = {
                    crossfade(true)
                }),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ImageGridList(viewModel: MainViewModel) {
    val images by viewModel.images.observeAsState()

    images?.let { imageList ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(imageList) { image ->
                Image(
                    painter = rememberImagePainter(data = image.urls.regular, builder = {
                        crossfade(true)
                    }),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(4.dp)
                )
            }
        }
    }
}





