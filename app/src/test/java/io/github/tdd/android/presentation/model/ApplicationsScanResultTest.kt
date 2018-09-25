package io.github.tdd.android.presentation.model

import android.content.pm.PackageInfo
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.*

class ApplicationsScanResultTest {

    private var result: ApplicationsScanResult? = null

    @Before
    fun setUp() {
        result = ApplicationsScanResult(Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList())
    }

    @Test
    fun testModelReceivesDangerousAppsResult() {
        val pi = mock(PackageInfo::class.java)
        val newInstance = result!!.copy(listOf(pi))
        assertTrue(!newInstance.dangerousPackages.isEmpty())
    }

    @Test
    fun testModelReceivesModerateAppsResult() {
        val pi = mock(PackageInfo::class.java)
        val newInstance = result!!.copy(moderatePackages = listOf(pi))
        assertTrue(!newInstance.moderatePackages.isEmpty())
    }

    @Test
    fun testModelReceivesSafeRiskAppsResult() {
        val pi = mock(PackageInfo::class.java)
        val newInstance = result!!.copy(safePackages = listOf(pi))
        assertTrue(!newInstance.safePackages.isEmpty())
    }

    @Test
    fun testToString() {
        Assert.assertTrue(!result.toString().isEmpty())
    }

    @Test
    fun testEquals() {
        val pi = mock(PackageInfo::class.java)
        val copy = result!!.copy(moderatePackages = listOf(pi))
        Assert.assertFalse(result!!.equals(copy))
    }

    @Test
    fun testComponentsNotNull() {
        Assert.assertTrue(result?.component1() is List<PackageInfo>)
        Assert.assertTrue(result?.component2() is List<PackageInfo>)
        Assert.assertTrue(result?.component3() is List<PackageInfo>)
    }
}