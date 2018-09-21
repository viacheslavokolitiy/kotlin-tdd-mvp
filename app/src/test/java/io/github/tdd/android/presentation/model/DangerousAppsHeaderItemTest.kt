package io.github.tdd.android.presentation.model

import io.github.tdd.android.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DangerousAppsHeaderItemTest {

    private var item: DangerousAppsHeaderItem? = null

    @Before
    fun setUp() {
        item = DangerousAppsHeaderItem(0)
    }

    @Test
    fun testModelReceivesStringRes() {
        val copy = item!!.copy(R.string.dangerous_apps)
        assertEquals(copy.title, R.string.dangerous_apps)
    }
}