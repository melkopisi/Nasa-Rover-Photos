package me.melkopisi.nasaroverphotos.general.utils

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import me.melkopisi.nasaroverphotos.general.utils.AppBarStateChangedListener.State.COLLAPSED
import me.melkopisi.nasaroverphotos.general.utils.AppBarStateChangedListener.State.EXPANDED
import me.melkopisi.nasaroverphotos.general.utils.AppBarStateChangedListener.State.IDLE
import kotlin.math.abs

abstract class AppBarStateChangedListener : OnOffsetChangedListener {

  enum class State {
    EXPANDED, COLLAPSED, IDLE
  }

  private var mCurrentState = IDLE

  override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
    when {
      verticalOffset == 0 -> setCurrentStateAndNotify(appBarLayout, EXPANDED)
     abs(verticalOffset) >= appBarLayout.totalScrollRange -> setCurrentStateAndNotify(appBarLayout, COLLAPSED)
      else -> setCurrentStateAndNotify(appBarLayout, IDLE)
    }
  }

  private fun setCurrentStateAndNotify(appBarLayout: AppBarLayout, state: State) {
    if (mCurrentState != state) onStateChanged(appBarLayout, state)
    mCurrentState = state
  }

  abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}