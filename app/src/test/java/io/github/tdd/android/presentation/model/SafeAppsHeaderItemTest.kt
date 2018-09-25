package io.github.tdd.android.presentation.model

import io.github.tdd.android.R
import org.junit.Assert.*
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

    @Test
    fun testToString() {
        assertTrue(!item.toString().isEmpty())
    }

    @Test
    fun testEquals() {
        val copy = item!!.copy(title = R.string.safe_risk_apps)
        assertFalse(item!!.equals(copy))
    }

    @Test
    fun testComponentsNotNull() {
        assertTrue(item?.component1() is Int)
    }
}