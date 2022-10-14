package me.melkopisi.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import me.melkopisi.data.di.IoDispatcher
import me.melkopisi.data.extensions.parseResponse
import me.melkopisi.data.network.apis.RoverApi
import me.melkopisi.data.remote.models.mappers.toDomainModel
import me.melkopisi.domain.dataSources.RoverPhotosRemoteDataSource
import me.melkopisi.domain.exceptions.RoverException
import me.melkopisi.domain.models.RoverPhotosDomainModel
import javax.inject.Inject

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class RoverPhotosRemoteDataSourceImpl @Inject constructor(
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
  private val roverApi: RoverApi
) : RoverPhotosRemoteDataSource {
  override suspend fun getRoverPhotos(page: Int): Flow<List<RoverPhotosDomainModel>> =
    flow { emit(roverApi.getRoverPhotos(page = page)) }
      .parseResponse()
      .map { it.photos.toDomainModel() }
      .map { it.ifEmpty { throw RoverException.NoData() } }
      .flowOn(ioDispatcher)
}