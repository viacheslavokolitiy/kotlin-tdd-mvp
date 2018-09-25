package io.github.tdd.android.presentation.model

import io.github.tdd.android.R
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ModerateAppsRiskHeaderItemTest {
    private var item: ModerateAppsRiskHeaderItem? = null

    @Before
    fun setUp() {
        item = ModerateAppsRiskHeaderItem(0)
    }

    @Test
    fun testModelReceivesStringRes() {
        val copy = item!!.copy(R.string.moderate_risk_apps)
        assertEquals(copy.title, R.string.moderate_risk_apps)
    }

    @Test
    fun testToString() {
        Assert.assertTrue(!item.toString().isEmpty())
    }

    @Test
    fun testEquals() {
        val copy = item!!.copy(title = R.string.moderate_risk_apps)
        Assert.assertFalse(item!!.equals(copy))
    }

    @Test
    fun testComponentsNotNull() {
        Assert.assertTrue(item?.component1() is Int)
    }
}