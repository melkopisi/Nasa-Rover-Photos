package me.melkopisi.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.data.remote.RoverPhotosRemoteDataSourceImpl
import me.melkopisi.domain.dataSources.RoverPhotosRemoteDataSource

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
  @Binds
  abstract fun bindGithubReposRepository(githubReposRepositoryImpl: RoverPhotosRemoteDataSourceImpl): RoverPhotosRemoteDataSource
}