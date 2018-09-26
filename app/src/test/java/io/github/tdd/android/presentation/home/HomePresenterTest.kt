package io.github.tdd.android.presentation.home

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PermissionInfo

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

import java.util.ArrayList
import java.util.Collections

import io.github.tdd.android.presentation.model.ApplicationsScanResult
import io.github.tdd.android.util.provider.InstalledApplicationsProvider

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@RunWith(PowerMockRunner::class)
@PrepareForTest(InstalledApplicationsProvider::class)
class HomePresenterTest {

    @Mock
    private lateinit var mView: HomeContract.View
    private var mProvider: InstalledApplicationsProvider? = null

    private var mPresenter: HomePresenter? = null

    @Before
    fun setUp() {
        mProvider = mock(InstalledApplicationsProvider::class.java)
        `when`(mProvider!!.getInstalledApplications()).thenReturn(emptyList())
        mPresenter = HomePresenter(mProvider!!)
        mPresenter!!.onCreate(mView)
    }

    @Test
    fun testScanIfNoAppsInstalled() {
        mPresenter!!.scanApplications()
        verify(mView)!!.hideProgress()
        verify(mView)!!.showScanResult(any(ApplicationsScanResult::class.java))
    }

    @Test
    fun testScanApps() {
        `when`(mProvider!!.getInstalledApplications()).thenReturn(mockInstalledApps())
        mPresenter!!.scanApplications()
        verify(mView)!!.hideProgress()
        verify(mView)!!.showScanResult(any(ApplicationsScanResult::class.java))
    }

    private fun mockInstalledApps(): List<PackageInfo> {
        val packageInfos = ArrayList<PackageInfo>()
        val packageInfo = PackageInfo()
        val applicationInfo = ApplicationInfo()
        val permissionInfo = PermissionInfo()
        permissionInfo.protectionLevel = PermissionInfo.PROTECTION_DANGEROUS

        val safe = PermissionInfo()
        safe.protectionLevel = PermissionInfo.PROTECTION_NORMAL

        val moderate = PermissionInfo()
        moderate.protectionLevel = 65000

        packageInfo.packageName = "TEST_APP"
        packageInfo.applicationInfo = applicationInfo
        packageInfo.permissions = arrayOf(permissionInfo, safe, moderate)
        packageInfos.add(packageInfo)

        return packageInfos
    }
}
