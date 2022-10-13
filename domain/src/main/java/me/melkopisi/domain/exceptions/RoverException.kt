package me.melkopisi.domain.exceptions

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
sealed class RoverException(msg:String):IllegalStateException(msg){
  class NetworkNotAvailable : RoverException("Network is not available.")
  class DataRetrievingFail : RoverException("Error getting data from server.")
  class NoData : RoverException("No data.")
}
