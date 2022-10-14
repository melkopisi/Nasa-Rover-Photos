package me.melkopisi.nasaroverphotos.features.photoslist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverPhotosUiModel(
  val id: Int,
  val sol: Int,
  val camera: Camera,
  val imgSrc: String,
  val earthDate: String,
  val rover: Rover
) : Parcelable {

  @Parcelize
  data class Camera(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String
  ) : Parcelable

  @Parcelize
  data class Rover(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
  ) : Parcelable
}