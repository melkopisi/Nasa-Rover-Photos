package me.melkopisi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Singleton

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

  @Singleton
  @MainDispatcher
  @Provides fun provideMainDispatcher(): MainCoroutineDispatcher = Dispatchers.Main

  @Singleton
  @IoDispatcher
  @Provides fun provideIDispatcher(): CoroutineDispatcher = Dispatchers.IO
}