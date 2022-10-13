package me.melkopisi.data.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.melkopisi.domain.exceptions.RoverException
import retrofit2.Response

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
fun <T> Flow<Response<T>>.parseResponse(): Flow<T> {
  return map { response ->
    if (response.isSuccessful) {
      if (response.body() != null) response.body()!! else throw RoverException.NoData()
    } else {
      throw RoverException.DataRetrievingFail()
    }
  }
}