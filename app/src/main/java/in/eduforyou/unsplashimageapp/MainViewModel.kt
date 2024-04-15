package `in`.eduforyou.unsplashimageapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ImageRepository) : ViewModel() {
    private val _images = MutableLiveData<List<UnsplashImage>>()
    val images: LiveData<List<UnsplashImage>> get() = _images

    fun fetchRandomImages(clientId: String, count: Int) {
        viewModelScope.launch {
            val fetchedImages = repository.getRandomImages(clientId, count)
            Log.d("MainViewModel", "Fetched ${fetchedImages.size} images")
            _images.postValue(fetchedImages)
        }
    }
}