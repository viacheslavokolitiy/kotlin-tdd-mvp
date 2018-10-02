package io.github.tdd.android

import android.content.pm.PackageInfo
import android.os.Build
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import io.github.tdd.android.adapter.delegate.DangerousAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ModerateRiskAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.SafeAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ScannedAppDelegate
import io.github.tdd.android.presentation.home.HomePresenter
import io.github.tdd.android.presentation.model.ApplicationsScanResult
import io.github.tdd.android.util.provider.InstalledApplicationsProvider
import kotlinx.android.synthetic.main.activity_home.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP],
        packageName = "io.github.tdd.android")
class HomeActivityTest {

    private lateinit var activity: HomeActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(HomeActivity::class.java)
    }

    @Test
    fun testGetLayoutResId() {
        assertEquals(R.layout.activity_home, activity.getLayoutResId())
    }

    @Test
    fun testPlaceholderVisibility() {
        val findViewById = activity.findViewById<TextView>(R.id.placeholder)
        assertEquals(View.GONE, findViewById.visibility)
    }

    @Test
    fun testShowProgress() {
        activity.showProgress()
        val progressBar = activity.findViewById<ProgressBar>(R.id.pbProgress)
        val placeholder = activity.findViewById<TextView>(R.id.placeholder)
        assertEquals(View.VISIBLE, progressBar.visibility)
        assertEquals(View.GONE, placeholder.visibility)
    }

    @Test
    fun testHideProgress() {
        activity.hideProgress()
        val progressBar = activity.findViewById<ProgressBar>(R.id.pbProgress)
        assertEquals(View.GONE, progressBar.visibility)
    }

    @Test
    fun testSetPresenter() {
        val homePresenter = HomePresenter(InstalledApplicationsProvider(RuntimeEnvironment.application))
        activity.presenter = homePresenter
        assertTrue(activity.presenter.equals(homePresenter))
    }

    @Test
    fun testSetDangerousAppHeaderDelegate() {
        val dangerousAppsHeaderDelegate = DangerousAppsHeaderDelegate(RuntimeEnvironment.systemContext)
        activity.dangerousAppsHeaderDelegate = dangerousAppsHeaderDelegate
        assertTrue(activity.dangerousAppsHeaderDelegate.equals(dangerousAppsHeaderDelegate))
    }

    @Test
    fun testSetModerateAppsHeaderDelegate() {
        val moderateRiskAppsHeaderDelegate = ModerateRiskAppsHeaderDelegate(RuntimeEnvironment.systemContext)
        activity.moderateRiskAppsHeaderDelegate = moderateRiskAppsHeaderDelegate
        assertTrue(activity.moderateRiskAppsHeaderDelegate.equals(moderateRiskAppsHeaderDelegate))
    }

    @Test
    fun testSetLowRiskAppsHeaderDelegate() {
        val safeAppsHeaderDelegate = SafeAppsHeaderDelegate(RuntimeEnvironment.systemContext)
        activity.safeAppsHeaderDelegate = safeAppsHeaderDelegate
        assertTrue(activity.safeAppsHeaderDelegate.equals(safeAppsHeaderDelegate))
    }

    @Test
    fun testSetScannedAppDelegate() {
        val scannedAppDelegate = ScannedAppDelegate(RuntimeEnvironment.systemContext)
        activity.scannedAppDelegate = scannedAppDelegate
        assertTrue(activity.scannedAppDelegate.equals(scannedAppDelegate))
    }

    @Test
    fun testActivityShowsCorrectTitle() {
        activity.showScreenTitle(R.string.installed_applications)
        assertEquals("Installed applications", activity.supportActionBar?.title)
    }

    @Test
    fun testActivityShowsDefaultTitle() {
        activity.showScreenTitle(R.string.installed_applications)
        val message = if (activity.supportActionBar == null) "kotlin-tdd-mvp" else "Installed applications"
        assertEquals(message, activity.supportActionBar?.title)
    }

    @Test
    fun testShowModerateRiskPackages() {
        val packageInfo = PackageInfo()
        packageInfo.packageName = "io.github.tdd.android"
        val appScanResult = ApplicationsScanResult(listOf(), listOf(packageInfo), listOf())
        val rcAppList = activity.rcAppList
        activity.showScanResult(appScanResult)
        val adapter = rcAppList.adapter
        assertTrue(adapter!!.itemCount > 0)
    }

    @Test
    fun testShowLowRiskPackages() {
        val packageInfo = PackageInfo()
        packageInfo.packageName = "io.github.tdd.android"
        val appScanResult = ApplicationsScanResult(listOf(), listOf(), listOf(packageInfo))
        val rcAppList = activity.rcAppList
        activity.showScanResult(appScanResult)
        val adapter = rcAppList.adapter
        assertTrue(adapter!!.itemCount > 0)
    }
}