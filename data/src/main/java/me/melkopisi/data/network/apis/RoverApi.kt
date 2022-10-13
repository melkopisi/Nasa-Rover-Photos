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

  //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY&page=3
  @GET(ROVER_PHOTOS)
  suspend fun getRoverPhotos(
    @Query("api_key") apiKey: String = "DEMO_KEY",
    @Query("sol") sol: Int = 1000,
    @Query("page") page: Int
  ): Response<RoverResponse>
}