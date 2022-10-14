package me.melkopisi.nasaroverphotos.features.photoslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.nasaroverphotos.MainActivity
import me.melkopisi.nasaroverphotos.R
import me.melkopisi.nasaroverphotos.databinding.FragmentRoverPhotosListBinding
import me.melkopisi.nasaroverphotos.features.photosdetails.fragments.RoverPhotoDetailsFragment
import me.melkopisi.nasaroverphotos.features.photoslist.adapters.RoverPhotosAdapter
import me.melkopisi.nasaroverphotos.features.photoslist.viewmodels.RoverPhotosListViewModel
import me.melkopisi.nasaroverphotos.features.photoslist.viewmodels.ScreenState
import me.melkopisi.nasaroverphotos.general.extensions.collectLifecycleFlow
import me.melkopisi.nasaroverphotos.general.utils.EndlessRecyclerViewScrollListener

/*
 * Authored by Kopisi on 13 Oct, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

@AndroidEntryPoint
class RoverPhotosListFragment : Fragment() {
  private var _binding: FragmentRoverPhotosListBinding? = null
  private val binding get() = _binding!!

  private val viewModel: RoverPhotosListViewModel by viewModels()

  private val photosAdapter by lazy { RoverPhotosAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) viewModel.getRoverPhotos(isFirstTime = true)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentRoverPhotosListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupToolbar()
    setupSwipeRefresh()
    setupRecyclerView()
    collectRoverPhotos()
    onPhotoClick()
  }

  private fun setupToolbar() = (requireActivity() as MainActivity).setSupportActionBar(binding.appBar.toolbar)

  private fun onPhotoClick() = photosAdapter.onItemClick {
    findNavController().navigate(
      R.id.action_roverPhotosListFragment_to_roverPhotoDetailsFragment,
      bundleOf(RoverPhotoDetailsFragment.ROVER_DETAILS to it)
    )
  }

  private fun setupSwipeRefresh() = binding.srRoverPhotos.setOnRefreshListener { viewModel.getRoverPhotos(isFirstTime = true) }

  private fun setupRecyclerView() = with(binding.rvRoverPhotos) {
    itemAnimator = null
    addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as GridLayoutManager) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
        viewModel.getRoverPhotos(isFirstTime = false)
      }
    })
    adapter = photosAdapter
  }

  private fun initLoading(isLoading: Boolean) {
    binding.srRoverPhotos.isRefreshing = isLoading
  }

  private fun collectRoverPhotos() {
    collectLifecycleFlow(viewModel.screenState) { state ->
      initLoading(state is ScreenState.Loading)

      when (state) {
        is ScreenState.Success -> {
          binding.inclEmptyState.llEmptyList.isVisible = false
          photosAdapter.setData(state.photos)
        }
        is ScreenState.Fail -> {
          binding.inclEmptyState.apply {
            llEmptyList.isVisible = true
            tvEmptyState.text = state.msg
          }
        }
        else -> Unit
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}