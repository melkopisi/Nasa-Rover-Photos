package me.melkopisi.data.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun Context.isInternetAvailable(): Boolean {
  val connectivityManager =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val networkCapabilities = connectivityManager.activeNetwork ?: return false
  val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
  return when {
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
    else -> false
  }
}