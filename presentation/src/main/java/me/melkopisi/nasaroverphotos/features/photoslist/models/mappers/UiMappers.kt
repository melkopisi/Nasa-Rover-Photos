package me.melkopisi.nasaroverphotos.features.photoslist.models.mappers

import me.melkopisi.domain.models.RoverPhotosDomainModel
import me.melkopisi.nasaroverphotos.features.photoslist.models.RoverPhotosUiModel

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun RoverPhotosDomainModel.Camera.toUiModel(): RoverPhotosUiModel.Camera =
  RoverPhotosUiModel.Camera(id = id, name = name, roverId = roverId, fullName = fullName)

fun RoverPhotosDomainModel.Rover.toUiModel(): RoverPhotosUiModel.Rover =
  RoverPhotosUiModel.Rover(id = id, name = name, landingDate = landingDate, launchDate = launchDate, status = status)

fun RoverPhotosDomainModel.toUiModel(): RoverPhotosUiModel = RoverPhotosUiModel(
  id = id,
  sol = sol,
  camera = camera.toUiModel(),
  imgSrc = imgSrc,
  earthDate = earthDate,
  rover = rover.toUiModel()
)

fun List<RoverPhotosDomainModel>.toUiModel(): List<RoverPhotosUiModel> = map { it.toUiModel() }
