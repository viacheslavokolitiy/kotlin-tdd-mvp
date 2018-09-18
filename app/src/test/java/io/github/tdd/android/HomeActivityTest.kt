package io.github.tdd.android

import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
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
}