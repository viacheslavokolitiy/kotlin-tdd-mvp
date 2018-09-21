package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import io.github.tdd.android.R
import io.github.tdd.android.presentation.model.ModerateAppsRiskHeaderItem
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
class ModerateRiskAppsHeaderDelegateTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var label: TextView

    @Mock
    private lateinit var inflater: LayoutInflater

    private var delegate: ModerateRiskAppsHeaderDelegate? = null

    @Before
    fun setUp() {
        mockStatic(LayoutInflater::class.java)
        `when`(LayoutInflater.from(context)).thenReturn(inflater)
        `when`(view.findViewById<TextView>(R.id.tvModerateAppsRiskLabel)).thenReturn(label)
        delegate = ModerateRiskAppsHeaderDelegate(context)
    }

    @Test
    fun testGetItemResId() {
        assertEquals(R.layout.moderate_apps_header, delegate!!.getLayoutResId())
    }

    @Test
    fun testOnBindViewHolder() {
        val viewHolder = ModerateRiskAppsHeaderDelegate.ViewHolder(view)
        val item = ModerateAppsRiskHeaderItem(R.string.dangerous_apps)
        delegate!!.bindViewHolder(viewHolder, item)
        val findViewById = viewHolder.itemView.findViewById<TextView>(R.id.tvModerateAppsRiskLabel)
        verify(findViewById).setText(R.string.dangerous_apps)
    }

    @Test
    fun testIsViewForType() {
        assertTrue(delegate!!.isForViewType(ModerateAppsRiskHeaderItem(R.string.moderate_risk_apps),
                Collections.emptyList(), 0))
    }

    @Test
    fun testCreateViewHolder() {
        assertNotNull(delegate!!.createViewHolder(view))
    }
}