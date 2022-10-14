package me.melkopisi.data.network.apis

import me.melkopisi.data.remote.models.RoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface RoverApi {
  companion object {
    const val ROVER_PHOTOS = "mars-photos/api/v1/rovers/curiosity/photos"
  }

  @GET(ROVER_PHOTOS)
  suspend fun getRoverPhotos(
    @Query("api_key") apiKey: String = "DEMO_KEY",
    @Query("sol") sol: Int = 1000,
    @Query("page") page: Int
  ): Response<RoverResponse>
}