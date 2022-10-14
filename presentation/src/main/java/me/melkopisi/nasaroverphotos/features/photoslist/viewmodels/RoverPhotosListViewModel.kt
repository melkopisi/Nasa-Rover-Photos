package me.melkopisi.nasaroverphotos.features.photoslist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.melkopisi.data.di.MainDispatcher
import me.melkopisi.domain.useCases.GetRoverPhotosUseCase
import me.melkopisi.nasaroverphotos.features.photoslist.models.RoverPhotosUiModel
import me.melkopisi.nasaroverphotos.features.photoslist.models.mappers.toUiModel
import javax.inject.Inject

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@HiltViewModel
class RoverPhotosListViewModel @Inject constructor(
  @MainDispatcher private val mainDispatcher: MainCoroutineDispatcher,
  private val getRoverPhotosUseCase: GetRoverPhotosUseCase
) : ViewModel() {

  private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Init)
  val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()

  private fun setLoading() {
    _screenState.value = ScreenState.Loading
  }

  private fun setError(msg: String?) {
    _screenState.value = ScreenState.Fail(msg = msg)
  }

  private fun setSuccess(photos: List<RoverPhotosUiModel>) {
    _screenState.value = ScreenState.Success(photos = photos)
  }

  private var currentOffset: Int = 1
  private var cachedList: MutableList<RoverPhotosUiModel> = mutableListOf()

  private lateinit var getRoverPhotosJob: Job

  fun getRoverPhotos(isFirstTime: Boolean = false) {
    if (isFirstTime) {
      cachedList.clear()
      currentOffset = 1
    }
    getRoverPhotosJob = viewModelScope.launch {
      getRoverPhotosUseCase(page = currentOffset)
        .onStart { setLoading() }
        .catch { throwable ->
          throwable.printStackTrace()
          setError(throwable.message)
        }
        .flowOn(mainDispatcher)
        .collect { roverPhotosList ->
          currentOffset++
          roverPhotosList.toUiModel().addToCache()
          setSuccess(cachedList)
        }
    }
  }

  private fun List<RoverPhotosUiModel>.addToCache() {
    cachedList.addAll(this@addToCache)
  }

  override fun onCleared() {
    super.onCleared()
    if (this::getRoverPhotosJob.isInitialized) getRoverPhotosJob.cancel()
  }
}

sealed interface ScreenState {
  object Init : ScreenState
  object Loading : ScreenState
  data class Success(val photos: List<RoverPhotosUiModel>) : ScreenState
  data class Fail(val msg: String?) : ScreenState
}