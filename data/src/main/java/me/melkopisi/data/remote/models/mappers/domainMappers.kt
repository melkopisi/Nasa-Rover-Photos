package me.melkopisi.data.remote.models.mappers

import me.melkopisi.data.remote.models.RoverResponse
import me.melkopisi.domain.models.RoverPhotosDomainModel

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun RoverResponse.Photo.Camera.toDomainModel(): RoverPhotosDomainModel.Camera =
  RoverPhotosDomainModel.Camera(id = id, name = name, roverId = roverId, fullName = fullName)

fun RoverResponse.Photo.Rover.toDomainModel(): RoverPhotosDomainModel.Rover =
  RoverPhotosDomainModel.Rover(id = id, name = name, landingDate = landingDate, launchDate = launchDate, status = status)

fun RoverResponse.Photo.toDomainModel(): RoverPhotosDomainModel = RoverPhotosDomainModel(
  id = id,
  sol = sol,
  camera = camera.toDomainModel(),
  imgSrc = imgSrc,
  earthDate = earthDate,
  rover = rover.toDomainModel()
)

fun List<RoverResponse.Photo>.toDomainModel(): List<RoverPhotosDomainModel> = map { it.toDomainModel() }
