package me.melkopisi.nasaroverphotos.features.photoslist.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.melkopisi.domain.exceptions.RoverException
import me.melkopisi.domain.models.RoverPhotosDomainModel
import me.melkopisi.domain.models.RoverPhotosDomainModel.Camera
import me.melkopisi.domain.models.RoverPhotosDomainModel.Rover
import me.melkopisi.domain.useCases.GetRoverPhotosUseCase
import me.melkopisi.nasaroverphotos.features.photoslist.models.mappers.toUiModel
import me.melkopisi.nasaroverphotos.features.photoslist.utils.MainCoroutineRule
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/*
 * Authored by Kopisi on 15 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class RoverPhotosListViewModelTest {
  @OptIn(ExperimentalCoroutinesApi::class)
  @get:Rule var mainCoroutineRule = MainCoroutineRule()

  private lateinit var viewModel: RoverPhotosListViewModel
  @Mock private lateinit var getRoverPhotosUseCase: GetRoverPhotosUseCase
  private val testResults: MutableList<ScreenState> = mutableListOf()

  @OptIn(ExperimentalCoroutinesApi::class)
  @Before
  fun setup() {
    viewModel = RoverPhotosListViewModel(mainCoroutineRule.testDispatcher, getRoverPhotosUseCase)
  }

  @After
  fun teardown() {
    testResults.clear()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when getRoverPhotos() then return loading then success state`() = runTest {
    // Arrange
    val roverPhotos = RoverPhotosDomainModel(
      1,
      1000,
      Camera(1, "camera", 1, "full_name"),
      "imgSrc",
      "1-1-1",
      Rover(1, "rover", "1-1-1", "1-1-1", "status")
    )

    Mockito.lenient().`when`(getRoverPhotosUseCase(1))
      .thenReturn(flowOf(listOf(roverPhotos)))

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.getRoverPhotos(true)
    advanceUntilIdle()

    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(ScreenState.Init::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(ScreenState.Loading::class.java))
    MatcherAssert.assertThat(testResults[2], IsInstanceOf.instanceOf(ScreenState.Success::class.java))
    Assert.assertEquals(listOf(roverPhotos.toUiModel()), (testResults[2] as ScreenState.Success).photos)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when getRoverPhotos() then return Init then fail state and return NetworkNotAvailable Exception`() = runTest {
    // Arrange
    val roverPhotos = RoverPhotosDomainModel(
      1,
      1000,
      Camera(1, "camera", 1, "full_name"),
      "imgSrc",
      "1-1-1",
      Rover(1, "rover", "1-1-1", "1-1-1", "status")
    )

    Mockito.lenient().`when`(getRoverPhotosUseCase(1))
      .thenReturn(flowOf(listOf(roverPhotos))
        .map {
          throw RoverException.NetworkNotAvailable()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.getRoverPhotos(true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(ScreenState.Init::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(ScreenState.Fail::class.java))
    Assert.assertEquals("Network is not available.", (testResults[1] as ScreenState.Fail).msg)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when getRoverPhotos() then return Init then fail state and return NoData Exception`() = runTest {
    // Arrange
    val roverPhotos = RoverPhotosDomainModel(
      1,
      1000,
      Camera(1, "camera", 1, "full_name"),
      "imgSrc",
      "1-1-1",
      Rover(1, "rover", "1-1-1", "1-1-1", "status")
    )

    Mockito.lenient().`when`(getRoverPhotosUseCase(1))
      .thenReturn(flowOf(listOf(roverPhotos))
        .map {
          throw RoverException.NoData()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.getRoverPhotos(true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(ScreenState.Init::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(ScreenState.Fail::class.java))
    Assert.assertEquals("No data.", (testResults[1] as ScreenState.Fail).msg)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when getRoverPhotos() then return Init then fail state and return DataRetrievingFail Exception`() = runTest {
    // Arrange
    val roverPhotos = RoverPhotosDomainModel(
      1,
      1000,
      Camera(1, "camera", 1, "full_name"),
      "imgSrc",
      "1-1-1",
      Rover(1, "rover", "1-1-1", "1-1-1", "status")
    )

    Mockito.lenient().`when`(getRoverPhotosUseCase(1))
      .thenReturn(flowOf(listOf(roverPhotos))
        .map {
          throw RoverException.DataRetrievingFail()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.getRoverPhotos(true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(ScreenState.Init::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(ScreenState.Fail::class.java))
    Assert.assertEquals("Error getting data from server.", (testResults[1] as ScreenState.Fail).msg)
    job.cancel()
  }
}