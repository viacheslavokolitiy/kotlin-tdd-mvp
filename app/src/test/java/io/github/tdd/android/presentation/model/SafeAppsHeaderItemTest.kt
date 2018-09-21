package io.github.tdd.android.presentation.model

import io.github.tdd.android.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SafeAppsHeaderItemTest {
    private var item: SafeAppsHeaderItem? = null

    @Before
    fun setUp() {
        item = SafeAppsHeaderItem(0)
    }

    @Test
    fun testModelReceivesStringRes() {
        val copy = item!!.copy(R.string.safe_risk_apps)
        assertEquals(copy.title, R.string.safe_risk_apps)
    }
}