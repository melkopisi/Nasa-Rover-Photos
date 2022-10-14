package me.melkopisi.nasaroverphotos.features.photoslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.melkopisi.nasaroverphotos.databinding.ItemPhotoBinding
import me.melkopisi.nasaroverphotos.features.photoslist.adapters.RoverPhotosAdapter.RoverPhotoViewHolder
import me.melkopisi.nasaroverphotos.features.photoslist.models.RoverPhotosUiModel
import me.melkopisi.nasaroverphotos.general.extensions.loadImage

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class RoverPhotosAdapter: RecyclerView.Adapter<RoverPhotoViewHolder>() {

  private var items: List<RoverPhotosUiModel> = listOf()
  private lateinit var itemCallback: (RoverPhotosUiModel) -> Unit
  
  inner class RoverPhotoViewHolder(private val binding: ItemPhotoBinding, private val itemCallback: (RoverPhotosUiModel) -> Unit) :
    ViewHolder(binding.root) {

    fun bind(item: RoverPhotosUiModel) {
      with(binding) {
        roverName.text = item.rover.name
        photoDate.text = item.earthDate
        cameraName.text = item.camera.name
        roverPhoto.loadImage(item.imgSrc)

        root.setOnClickListener { itemCallback(item) }
      }
    }
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverPhotoViewHolder {
    return RoverPhotoViewHolder(
      ItemPhotoBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      ), itemCallback
    )
  }

  override fun onBindViewHolder(holder: RoverPhotoViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int = items.size

  fun setData(booksList: List<RoverPhotosUiModel>) {
    items = booksList
    notifyItemRangeChanged(0, booksList.size)
  }

  fun onItemClick(callback: (RoverPhotosUiModel) -> Unit) {
    this.itemCallback = callback
  }
}