package `in`.eduforyou.unsplashimageapp

class ImageRepository(private val service: UnsplashService) {
    suspend fun getRandomImages(clientId: String, count: Int): List<UnsplashImage> {
        return service.getRandomImage(clientId, count)
    }
}