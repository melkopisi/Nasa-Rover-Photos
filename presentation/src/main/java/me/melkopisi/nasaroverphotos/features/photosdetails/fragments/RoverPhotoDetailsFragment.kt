package me.melkopisi.nasaroverphotos.features.photosdetails.fragments

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.nasaroverphotos.MainActivity
import me.melkopisi.nasaroverphotos.R
import me.melkopisi.nasaroverphotos.databinding.FragmentRoverPhotoDetailsBinding
import me.melkopisi.nasaroverphotos.features.photoslist.models.RoverPhotosUiModel
import me.melkopisi.nasaroverphotos.general.extensions.extractFileName
import me.melkopisi.nasaroverphotos.general.extensions.loadImage
import me.melkopisi.nasaroverphotos.general.extensions.toPixel
import me.melkopisi.nasaroverphotos.general.utils.AppBarStateChangedListener
import me.melkopisi.nasaroverphotos.general.utils.AppBarStateChangedListener.State.COLLAPSED

/*
 * Authored by Kopisi on 14 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@AndroidEntryPoint
class RoverPhotoDetailsFragment : Fragment() {

  private var _binding: FragmentRoverPhotoDetailsBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentRoverPhotoDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args = arguments?.getParcelable<RoverPhotosUiModel>(ROVER_DETAILS)
    setupToolbar()
    setupViews(args)
  }

  private fun setupToolbar() {
    (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
    (requireActivity() as MainActivity).supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(true)
    }
    binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    binding.appbarLayout.addOnOffsetChangedListener(object : AppBarStateChangedListener() {
      override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
        binding.roverDetailsToolbarContentLinear.apply {
          animate().scaleX(if (state == COLLAPSED) 1.0F else 0.97F)
            .scaleY(if (state == COLLAPSED) 1.0F else 0.97F)
            .translationY(if (state == COLLAPSED) 0.0F else -20.0F)
            .alpha(if (state == COLLAPSED) 1.0F else 0.0F)
            .setDuration(if (state == COLLAPSED) 200L else 50L)
            .start()
        }
        binding.roverDetailsContentCardView.apply {
          ObjectAnimator.ofFloat(this, "radius", (if (state == COLLAPSED) 0 else 30).toPixel(appBarLayout.context).toFloat())
            .setDuration(200L)
            .start()
        }
      }
    })
  }

  private fun setupViews(photoModel: RoverPhotosUiModel?) {
    with(photoModel!!) {
      val imageName = Uri.parse(imgSrc)?.extractFileName().orEmpty()
      binding.roverPhoto.loadImage(imgSrc)
      binding.roverDetailsToolbarTitle.text = imageName
      binding.inclRoverDetailsMain.apply {
        tvRoverPhotoName.text = imageName
        tvRoverDetailsEarthDateValue.text = earthDate

        tvRoverDetailsCameraShortNameValue.text = getString(R.string.rover_camera_short_name, camera.name)
        tvRoverDetailsCameraFullNameValue.text = getString(R.string.rover_camera_full_name, camera.fullName)

        tvRoverNameValue.text = getString(R.string.rover_name,rover.name)
        tvRoverStatusValue.text = getString(R.string.rover_status,rover.status)
        tvRoverLaunchDateValue.text = getString(R.string.rover_launch_date,rover.launchDate)
        tvRoverLandingDateValue.text = getString(R.string.rover_landing_date,rover.landingDate)
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  companion object {
    const val ROVER_DETAILS = "rover_details"
  }
}