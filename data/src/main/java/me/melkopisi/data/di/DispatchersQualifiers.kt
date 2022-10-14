package me.melkopisi.data.di

import javax.inject.Qualifier

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher