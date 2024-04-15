package `in`.eduforyou.unsplashimageapp

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("photos/random")
    suspend fun getRandomImage(
        @Query("client_id") clientId: String,
        @Query("count") count: Int
    ): List<UnsplashImage>
}