package io.github.tdd.android.presentation.model

import android.content.pm.PermissionInfo
import android.graphics.drawable.Drawable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class ScannedAppItemTest {

    private var item: ScannedAppItem? = null

    @Before
    fun setUp() {
        item = ScannedAppItem(mock(Drawable::class.java), "Any",
                listOf<PermissionInfo>(mock(PermissionInfo::class.java))
                        .toMutableList())
    }

    @Test
    fun testModelReceivesDrawable() {
        val drawable = mock(Drawable::class.java)
        val copy = item!!.copy(icon = drawable)
        assertEquals(drawable, copy.icon)
    }

    @Test
    fun testModelReceivesTitle() {
        val title = "Sample app"
        val copy = item!!.copy(title = title)
        assertEquals(title, copy.title)
    }

    @Test
    fun testModelReceivesPermissionsList() {
        val pi = PermissionInfo()
        val list = listOf(pi).toMutableList()
        val copy = item!!.copy(permissions = list)
        assertEquals(list, copy.permissions)
    }

    @Test
    fun testToString() {
        assertTrue(!item.toString().isEmpty())
    }

    @Test
    fun testEquals() {
        val copy = item!!.copy(title = "")
        assertFalse(item!!.equals(copy))
    }

    @Test
    fun testComponentsNotNull() {
        assertTrue(item?.component1() is Drawable)
        assertTrue(item?.component2() is String)
        assertTrue(item?.component3() is MutableList<PermissionInfo>)
    }
}