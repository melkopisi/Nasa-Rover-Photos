package me.melkopisi.domain.dataSources

import kotlinx.coroutines.flow.Flow
import me.melkopisi.domain.models.RoverPhotosDomainModel

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface RoverPhotosRemoteDataSource {
  suspend fun getRoverPhotos(page: Int): Flow<List<RoverPhotosDomainModel>>
}