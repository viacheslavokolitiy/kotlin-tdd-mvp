package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import io.github.tdd.android.R
import io.github.tdd.android.presentation.model.DangerousAppsHeaderItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.util.*

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [LayoutInflater::class])
class DangerousAppsHeaderDelegateTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var label: TextView

    @Mock
    private lateinit var inflater: LayoutInflater

    private var delegate: DangerousAppsHeaderDelegate? = null

    @Before
    fun setUp() {
        mockStatic(LayoutInflater::class.java)
        `when`(LayoutInflater.from(context)).thenReturn(inflater)
        `when`(view.findViewById<TextView>(R.id.tvDangerousAppsLabel)).thenReturn(label)
        delegate = DangerousAppsHeaderDelegate(context)
    }

    @Test
    fun testGetItemResId() {
        assertEquals(R.layout.dangerous_apps_header, delegate!!.getLayoutResId())
    }

    @Test
    fun testOnBindViewHolder() {
        val viewHolder = DangerousAppsHeaderDelegate.ViewHolder(view)
        val item = DangerousAppsHeaderItem(R.string.dangerous_apps)
        delegate!!.bindViewHolder(viewHolder, item)
        val findViewById = viewHolder.itemView.findViewById<TextView>(R.id.tvDangerousAppsLabel)
        verify(findViewById).setText(R.string.dangerous_apps)
    }

    @Test
    fun testIsViewForType() {
        assertTrue(delegate!!.isForViewType(DangerousAppsHeaderItem(R.string.dangerous_apps),
                Collections.emptyList(), 0))
    }

    @Test
    fun testCreateViewHolder() {
        assertNotNull(delegate!!.createViewHolder(view))
    }
}