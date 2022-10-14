package me.melkopisi.nasaroverphotos.general.extensions

import android.content.Context
import android.util.TypedValue

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun Int.toPixel(context: Context) =
  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics).toInt()