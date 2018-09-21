package io.github.tdd.android

import android.os.Build
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP],
        packageName = "io.github.tdd.android")
class HomeActivityTest {

    private lateinit var activity: HomeActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(HomeActivity::class.java)
    }

    @Test
    fun testGetLayoutResId() {
        assertEquals(R.layout.activity_home, activity.getLayoutResId())
    }

    @Test
    fun testPlaceholderVisibility() {
        val findViewById = activity.findViewById<TextView>(R.id.placeholder)
        assertEquals(View.GONE, findViewById.visibility)
    }

    @Test
    fun testShowProgress() {
        activity.showProgress()
        val progressBar = activity.findViewById<ProgressBar>(R.id.pbProgress)
        val placeholder = activity.findViewById<TextView>(R.id.placeholder)
        assertEquals(View.VISIBLE, progressBar.visibility)
        assertEquals(View.GONE, placeholder.visibility)
    }

    @Test
    fun testHideProgress() {
        activity.hideProgress()
        val progressBar = activity.findViewById<ProgressBar>(R.id.pbProgress)
        assertEquals(View.GONE, progressBar.visibility)
    }
}