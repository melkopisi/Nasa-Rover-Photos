package me.melkopisi.domain.useCases

import kotlinx.coroutines.flow.Flow
import me.melkopisi.domain.dataSources.RoverPhotosRemoteDataSource
import me.melkopisi.domain.models.RoverPhotosDomainModel
import javax.inject.Inject

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class GetRoverPhotosUseCase @Inject constructor(
  private val roverPhotosRemoteDataSource: RoverPhotosRemoteDataSource
) {
  suspend operator fun invoke(page: Int): Flow<List<RoverPhotosDomainModel>> = roverPhotosRemoteDataSource.getRoverPhotos(page = page)
}