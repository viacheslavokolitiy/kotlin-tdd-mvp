package io.github.tdd.android.adapter

import io.github.tdd.android.adapter.delegate.SafeAppsHeaderDelegate
import io.github.tdd.android.presentation.model.SafeAppsHeaderItem
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(SafeAppsHeaderItem::class)
class ApplicationsAdapterTest {

    private var adapter: ApplicationsAdapter? = null

    @Mock
    private lateinit var delegate: SafeAppsHeaderDelegate

    @Mock
    private lateinit var item: SafeAppsHeaderItem

    @Before
    fun setUp() {
        adapter = ApplicationsAdapter(listOf(item), listOf(delegate))
    }

    @Test
    fun testGetItemsShouldNotBeEmpty() {
        assertTrue(!adapter!!.items.isEmpty())
    }
}