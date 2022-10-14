package me.melkopisi.nasaroverphotos.general.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import me.melkopisi.nasaroverphotos.R

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun ImageView.loadImage(photoUrl: String?) {
  Glide.with(this).load(photoUrl).placeholder(R.drawable.ic_photo_placeholder).error(R.drawable.ic_broken_image).into(this)
}
