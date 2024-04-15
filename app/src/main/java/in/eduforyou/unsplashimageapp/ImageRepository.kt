package `in`.eduforyou.unsplashimageapp

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ImageRepository(private val service: UnsplashService) {

    suspend fun getRandomImages(clientId: String, count: Int): List<UnsplashImage> {

        return service.getRandomImage(clientId, count)

    }

}

