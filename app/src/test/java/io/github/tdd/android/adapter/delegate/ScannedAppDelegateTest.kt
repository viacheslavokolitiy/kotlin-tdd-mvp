package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.content.pm.PermissionInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.tdd.android.R
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
import io.github.tdd.android.presentation.model.ScannedAppItem
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [LayoutInflater::class])
class ScannedAppDelegateTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var logo: ImageView

    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var appLabel: TextView

    @Mock
    private lateinit var listener: BaseListItemAdapterDelegate.OnItemClickListener<ScannedAppItem>

    private var delegate: ScannedAppDelegate? = null

    @Before
    fun setUp() {
        PowerMockito.mockStatic(LayoutInflater::class.java)
        `when`(LayoutInflater.from(context)).thenReturn(inflater)
        `when`(view.findViewById<ImageView>(R.id.ivAppLogo)).thenReturn(logo)
        `when`(view.findViewById<TextView>(R.id.tvAppLabel)).thenReturn(appLabel)
        delegate = ScannedAppDelegate(context)
    }

    @Test
    fun testGetItemResId() {
        Assert.assertEquals(R.layout.scanned_app_item, delegate!!.getLayoutResId())
    }

    @Test
    fun testOnBindViewHolder() {
        val holder = ScannedAppDelegate.ViewHolder(view)
        val icon = mock(Drawable::class.java)
        val scannedAppItem = ScannedAppItem(icon, "App",
                listOf<PermissionInfo>(mock(PermissionInfo::class.java)).toMutableList())
        delegate!!.bindViewHolder(holder, scannedAppItem, listener)
        val appLabel = holder.itemView.findViewById<TextView>(R.id.tvAppLabel)
        val logo = holder.itemView.findViewById<ImageView>(R.id.ivAppLogo)
        verify(appLabel).setText("App")
        verify(logo).setImageDrawable(icon)
    }

    @Test
    fun testCreateViewHolder() {
        assertNotNull(delegate!!.createViewHolder(view))
    }

    @Test
    fun testIsViewForTypeValid() {
        val icon = mock(Drawable::class.java)
        val item = ScannedAppItem(icon, "App",
                listOf<PermissionInfo>(mock(PermissionInfo::class.java)).toMutableList())
        assertTrue(delegate!!.isForViewType(item,
                listOf(item).toMutableList(), 0))
    }
}